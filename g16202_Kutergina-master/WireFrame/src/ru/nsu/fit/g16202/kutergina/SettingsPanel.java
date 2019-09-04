package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static java.lang.Math.PI;

public class SettingsPanel extends JPanel {
    private Settings settings;
    private SettingsView settingsView;
    private Communicator communicator;
    private JSpinner textN = new JSpinner();
    private JSpinner textM = new JSpinner();
    private JSpinner textK = new JSpinner();
    private JSpinner textA = new JSpinner();
    private JSpinner textB = new JSpinner();
    private JSpinner textC = new JSpinner();
    private JSpinner textD = new JSpinner();
    private JSpinner textSW = new JSpinner();
    private JSpinner textSH = new JSpinner();
    private JSpinner textZF = new JSpinner();
    private JSpinner textZB = new JSpinner();
    private JMenuItem open = new JMenuItem("Open");
    private JMenuItem about = new JMenuItem("About");
    private JMenuItem newI = new JMenuItem("New");
    private JMenuItem delete = new JMenuItem("Delete");
    private JMenuItem init = new JMenuItem("Init");
    private JMenuItem color = new JMenuItem("Color");

    public JMenuItem getNewI() {
        return newI;
    }

    public JMenuItem getDelete() {
        return delete;
    }

    public JMenuItem getInit() {
        return init;
    }

    public JMenuItem getColor() {
        return color;
    }

    public JMenuItem getAbout() {
        return about;
    }

    public JMenuItem getOpen() {
        return open;
    }


    public void updateZF() {
        textZF.setValue(settings.getZf());
    }

    public void updateValues() {
        textN.setValue(settings.getN());
        textM.setValue(settings.getM());
        textK.setValue(settings.getK());
        textA.setValue(settings.getA());
        textB.setValue(settings.getB());
        textC.setValue(settings.getC() * 180 / PI);
        textD.setValue(settings.getD() * 180 / PI);
        textSW.setValue(settings.getSw());
        textSH.setValue(settings.getSh());
        textZF.setValue(settings.getZf());
        textZB.setValue(settings.getZb());
    }


    SettingsPanel(Settings settings, SettingsView settingsView, Communicator communicator) {
        super();
        this.settings = settings;
        this.settingsView = settingsView;
        this.communicator = communicator;
        GridLayout layout = new GridLayout(4, 7, 5, 5);
        setLayout(layout);
        LinkedList<JComponent> components = new LinkedList<>();
        components.add(getjPanel("n", textN, settings.getN(), 2, 50, 1));
        components.add(getjPanel("m", textM, settings.getM(), 2, 50, 1));
        components.add(getjPanel("k", textK, settings.getK(), 1, 50, 1));
        components.add(getjPanel("a", textA, settings.getA(), 0, 1, 0.01));
        components.add(getjPanel("b", textB, settings.getB(), 0, 1, 0.01));
        components.add(getjPanel("c", textC, settings.getC() * 180. / PI, 0, 360, 1));
        components.add(getjPanel("d", textD, settings.getD() * 180. / PI, 0, 360, 1));
        components.add(getjPanel("sw", textSW, settings.getSw(), 0, 100, 0.01));
        components.add(getjPanel("sh", textSH, settings.getSh(), 0, 100, 0.01));
        components.add(getjPanel("zf", textZF, settings.getZf(), 0, 100, 0.01));

        for (JComponent c : components) {
            add(c);
        }

        JButton newB = new JButton("New");
        add(newB);
        newB.addActionListener(e -> communicator.getSplineScene().setNewScene());
        this.newI.addActionListener(e -> communicator.getSplineScene().setNewScene());

        JButton delete = new JButton("Delete");
        add(delete);
        delete.addActionListener(e -> communicator.getFigurePanel().deleteFigure());
        this.delete.addActionListener(e -> communicator.getFigurePanel().deleteFigure());

        OpenAction openListener = new OpenAction();
        JButton open = new JButton("Open");
        add(open);
        open.addActionListener(openListener);
        this.open.addActionListener(openListener);
        JButton init = new JButton("Init");
        add(init);
        init.addActionListener(e -> {
            communicator.getFigurePanel().init();
        });
        this.init.addActionListener(e -> {
            communicator.getFigurePanel().init();
        });


        JButton chooseColor = new JButton("Color");
        add(chooseColor);
        chooseColor.addActionListener(e -> {
            new ColorChooser(communicator);
            communicator.getFigurePanel().updateFigure();
        });
        this.color.addActionListener(e -> {
            new ColorChooser(communicator);
            communicator.getFigurePanel().updateFigure();
        });

        this.about.addActionListener(e -> new AboutDialog());
        JButton about = new JButton("About");
        add(about);
        about.addActionListener(e -> new AboutDialog());
        textN.addChangeListener(e -> {
            Class<?> aClass = textN.getValue().getClass();
            if (aClass == Double.class) {
                settings.setN((Double) textN.getValue());
            } else {
                settings.setN((Integer) textN.getValue());
            }
            communicator.getFigurePanel().updateFigures();
                }
        );
        textM.addChangeListener(e -> {
                    Class<?> aClass = textM.getValue().getClass();
                    if (aClass == Double.class) {
                        settings.setM((Double) textM.getValue());

                    } else {
                        settings.setM((Integer) textM.getValue());
                    }
            communicator.getFigurePanel().updateFigure();
            communicator.getFigurePanel().updateFigures();
                }
        );
        textK.addChangeListener(e -> {
                    Class<?> aClass = textK.getValue().getClass();
                    if (aClass == Double.class) {
                        settings.setK((Double) textK.getValue());

                    } else {
                        settings.setK((Integer) textK.getValue());
                    }
            communicator.getFigurePanel().updateFigure();
            communicator.getFigurePanel().updateFigures();
                }
        );
        textA.addChangeListener(e -> {
                    Double value = (Double) textA.getValue();
                    double b = settings.getB();
                    if (value > b) {
                        textA.setValue(b);
                        value = b;
                    }
                    settings.setA(value);
                    communicator.getSplineScene().repaintScene();
            communicator.getFigurePanel().updateFigures();
                }
        );
        textB.addChangeListener(e -> {
                    Double value = (Double) textB.getValue();
                    double a = settings.getA();
                    if (value < a) {
                        textB.setValue(a);
                        value = a;
                    }
                    settings.setB(value);
            communicator.getSplineScene().repaintScene();
            communicator.getFigurePanel().updateFigures();
                }
        );
        textC.addChangeListener(e -> {
                    Double value = (Double) textC.getValue() * PI / 180;
                    double d = settings.getD();
                    if (value > d) {
                        textC.setValue(d * 180. / PI);
                        value = d;
                    }
                    settings.setC(value);
            communicator.getSplineScene().repaintScene();
            communicator.getFigurePanel().updateFigures();
                }
        );
        textD.addChangeListener(e -> {
                    Double value = (Double) textD.getValue() * PI / 180;
                    double c = settings.getC();
                    if (value < c) {
                        textD.setValue(c * 180. / PI);
                        value = c;
                    }
                    settings.setD(value);
            communicator.getSplineScene().repaintScene();
            communicator.getFigurePanel().updateFigures();
                }
        );
        textSW.addChangeListener(e -> {
                    settings.setSw(((Double) textSW.getValue()));
            communicator.getFigurePanel().updateFigures();
                }
        );
        textSH.addChangeListener(e -> {
                    settings.setSh(((Double) textSH.getValue()));
            communicator.getFigurePanel().updateFigures();
                }
        );
        textZF.addChangeListener(e -> {
                    settings.setZf(((Double) textZF.getValue()));
            communicator.getFigurePanel().updateFigures();
                }
        );
    }

    private JPanel getjPanel(String s, JSpinner spinner, double curValue, double minValue, double maxValue, double stepValue) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(s);
        SpinnerModel spinnerModel = new SpinnerNumberModel(curValue, minValue, maxValue, stepValue);
        spinner.setModel(spinnerModel);
        spinner.setPreferredSize(new Dimension(50, 20));
        panel.add(label, BorderLayout.WEST);
        panel.add(spinner, BorderLayout.EAST);

        return panel;
    }

    private class OpenAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.setCurrentDirectory(new File("Data"));
            fileOpen.setFileFilter(new FileNameExtensionFilter("text", "txt"));
            int ret = fileOpen.showDialog(null, "Open file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileOpen.getSelectedFile();
                    ParseFile parseFile = new ParseFile(file, settings, settingsView, communicator);
                    updateValues();

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(communicator.getSplineScene(),
                            "Incorrect file");
                }
            }
        }
    }

}
