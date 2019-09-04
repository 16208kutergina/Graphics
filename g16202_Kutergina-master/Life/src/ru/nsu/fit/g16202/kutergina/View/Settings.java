package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;
import ru.nsu.fit.g16202.kutergina.Controller.FlagsButton;
import ru.nsu.fit.g16202.kutergina.Controller.ReplaceModeAction;
import ru.nsu.fit.g16202.kutergina.Controller.XorModeAction;
import ru.nsu.fit.g16202.kutergina.Model.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Settings extends JFrame {
    private Communicator communicator;
    private JRadioButton replace;
    private JRadioButton xor;
    private JTextField n;
    private JTextField m;
    private JSlider widthLineSlider;
    private JTextField widthLineText;
    private JSlider sizeCellSlider;
    private JTextField sizeCellText;
    private JTextField birthBegin;
    private JTextField birthEnd;
    private JTextField lifeBegin;
    private JTextField lifeEnd;
    private JTextField firstImpact;
    private JTextField secondImpact;
    private JButton ok;
    private JButton cancel;


    public JRadioButton getReplace() {
        return replace;
    }

    public JRadioButton getXor() {
        return xor;
    }

    public void setCommunicator(Communicator communicator){
        setVisible(false);
        this.communicator = communicator;
        replace.addActionListener(new ReplaceModeAction(communicator));
        xor.addActionListener(new XorModeAction(communicator));
    }

    public void setOldValues() {
        if (communicator.getHexagonField().getMode() == ModeField.XOR) {
            xor.setSelected(true);
            replace.setSelected(false);
        } else {
            xor.setSelected(false);
            replace.setSelected(true);
        }
        n.setText(String.valueOf(Constants.getX()));
        m.setText(String.valueOf(Constants.getY()));
        widthLineSlider.setValue(Constants.getWidthLine());
        widthLineText.setText(String.valueOf(Constants.getWidthLine()));
        sizeCellSlider.setValue(Constants.getSizeCell());
        sizeCellText.setText(String.valueOf(Constants.getSizeCell()));
        birthBegin.setText(String.valueOf(Constants.getBirthBegin()));
        birthEnd.setText(String.valueOf(Constants.getBirthEnd()));
        lifeBegin.setText(String.valueOf(Constants.getLiveBegin()));
        lifeEnd.setText(String.valueOf(Constants.getLiveEnd()));
        firstImpact.setText(String.valueOf(Constants.getFstImpact()));
        secondImpact.setText(String.valueOf(Constants.getSndImpact()));
    }

    public Settings() {
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());

        replace = new JRadioButton("Replace", true);
        xor = new JRadioButton("Xor", false);
        modeSettings(panel);

        n = new JTextField();
        n.setText(String.valueOf(Constants.getX()));
        n.addActionListener(e -> {
            setRightSize(n, Constants.minX, Constants.maxX);

        });
        m = new JTextField();
        m.setText(String.valueOf(Constants.getY()));
        m.addActionListener(e -> {
            setRightSize(m, Constants.minY, Constants.maxY);

        });
        sizeSettings(panel);
        add(panel, BorderLayout.WEST);

        birthBegin = new JTextField();
        birthBegin.setText(String.valueOf(Constants.getBirthBegin()));

        birthEnd = new JTextField();
        birthEnd.setText(String.valueOf(Constants.getBirthEnd()));

        lifeBegin = new JTextField();
        lifeBegin.setText(String.valueOf(Constants.getLiveBegin()));

        lifeEnd = new JTextField();
        lifeEnd.setText(String.valueOf(Constants.getLiveEnd()));

        firstImpact = new JTextField();
        firstImpact.setText(String.valueOf(Constants.getFstImpact()));

        secondImpact = new JTextField();
        secondImpact.setText(String.valueOf(Constants.getSndImpact()));
        setImpactOptions();

        widthLineSlider = new JSlider(Constants.minWidthLine, Constants.maxWidthLine, Constants.getWidthLine());
        widthLineText = new JTextField();
        sizeCellSlider = new JSlider(Constants.minSizeCell, Constants.maxSizeCell, Constants.getSizeCell());
        sizeCellText = new JTextField();
        JPanel jPanel = addSliders(widthLineSlider, widthLineText, sizeCellSlider, sizeCellText);

        ok = new JButton("OK");
        ok.addActionListener(e -> {
            try {
                Constants.setAll(Double.parseDouble(lifeBegin.getText()),
                        Double.parseDouble(lifeEnd.getText()),
                        Double.parseDouble(birthBegin.getText()),
                        Double.parseDouble(birthEnd.getText()),
                        Double.parseDouble(firstImpact.getText()),
                        Double.parseDouble(secondImpact.getText()),
                        Integer.parseInt(n.getText()),
                        Integer.parseInt(m.getText()),
                        widthLineSlider.getValue(),
                        sizeCellSlider.getValue());
            } catch (Exception e1) {
                System.out.println("Incorrect data");
            }
            try {
                communicator.fieldToModel();
            }catch (Exception ignored){}
            communicator.getHexagonLifeAction().setHexagonLifeActionWithOldCells();
            communicator.getHexagonField().setField();
            communicator.fieldToView();
            communicator.getHexagonLifeAction().countImpact();
            if(FlagsButton.isImpact()){
                double[][] impacts = communicator.getHexagonLifeAction().getImpacts();
                communicator.getHexagonField().printImpact(impacts);
            }
            communicator.getHexagonField().repaint();
            setVisible(false);
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(e -> setVisible(false));

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(200, 50));
        BoxLayout buttonsBox = new BoxLayout(buttons, BoxLayout.LINE_AXIS);
        buttons.setLayout(buttonsBox);
        buttons.add(ok);
        buttons.add(cancel);
        jPanel.add(buttons);
        pack();

    }


    static void setRightSize(JTextField n, int minX, int maxX) {
        int sizeX = 0;
        try {
            sizeX = Integer.parseInt(n.getText());
        } catch (NumberFormatException ex) {
            n.setText(String.valueOf(Constants.getX()));
        }
        if (sizeX < 1) {
            n.setText(String.valueOf(minX));
        } else {
            if (sizeX > maxX) {
                n.setText(String.valueOf(maxX));
            } else {
                n.setText(String.valueOf(sizeX));
            }
        }
    }

    private void setImpactOptions() {
        JPanel impactsPanel = new JPanel();
        impactsPanel.setPreferredSize(new Dimension(200, 200));
        BoxLayout impactsBox = new BoxLayout(impactsPanel, BoxLayout.PAGE_AXIS);
        impactsPanel.setLayout(impactsBox);

        addImpactOptionLine(birthBegin,impactsPanel, "Birth begin");
        addImpactOptionLine(birthEnd, impactsPanel,"Birth end");
        addImpactOptionLine(lifeBegin,impactsPanel, "Life begin");
        addImpactOptionLine(lifeEnd,impactsPanel, "Life end");
        addImpactOptionLine(firstImpact, impactsPanel,"First impact");
        addImpactOptionLine(secondImpact,impactsPanel, "Second impact");
        setBorder(impactsPanel, "Option");
        add(impactsPanel, BorderLayout.EAST);
    }

    private void addImpactOptionLine(JTextField birthEndText,JPanel impactsPanel, String s) {
        JPanel birthEndPanel = new JPanel();
        birthEndPanel.setPreferredSize(new Dimension(180, 10));
        BoxLayout birthEndBox = new BoxLayout(birthEndPanel, BoxLayout.LINE_AXIS);
        birthEndPanel.setLayout(birthEndBox);
        Label birthEndLabel = new Label(s);
        birthEndLabel.setPreferredSize(new Dimension(150, 10));
        birthEndPanel.add(birthEndLabel);
        birthEndPanel.add(birthEndText);
        impactsPanel.add(birthEndPanel);
    }

    private JPanel addSliders(JSlider widthLineSlider, JTextField widthLineText, JSlider sizeCellSlider, JTextField sizeCellText) {
        JPanel slidersPanel = new JPanel();
        slidersPanel.setPreferredSize(new Dimension(40, 150));
        BoxLayout slidersBox = new BoxLayout(slidersPanel, BoxLayout.PAGE_AXIS);
        slidersPanel.setLayout(slidersBox);
        addWidthLineSlider(widthLineSlider, widthLineText, slidersPanel, Constants.minWidthLine, Constants.maxWidthLine, "Width Line");
        addWidthLineSlider(sizeCellSlider, sizeCellText, slidersPanel, 15, 100, "Size cells");
        add(slidersPanel, BorderLayout.AFTER_LAST_LINE);
        return slidersPanel;
    }

    private void addWidthLineSlider(JSlider widthLineSlider, JTextField widthLineText, JPanel slidersPanel, int i, int i2, String s3) {
        JPanel widthLinePanel = new JPanel();
        BoxLayout widthLineLayout = new BoxLayout(widthLinePanel, BoxLayout.LINE_AXIS);
        widthLinePanel.setLayout(widthLineLayout);
        widthLineText.setText(String.valueOf(widthLineSlider.getValue()));
        widthLineText.setPreferredSize(new Dimension(40, 10));
        widthLineSlider.addChangeListener(e -> widthLineText.setText(" " + widthLineSlider.getValue()));
        widthLineText.addActionListener(e -> textToSlider(widthLineText, widthLineSlider, i, i2));
        widthLinePanel.add(widthLineSlider);
        widthLinePanel.add(widthLineText);
        setBorder(widthLinePanel, s3);
        slidersPanel.add(widthLinePanel);
    }

    private void textToSlider(JTextField sizeCellText, JSlider sizeCellSlider, int min, int max) {
        int sizeCell = min;
        try {
            sizeCell = Integer.parseInt(sizeCellText.getText());
        } catch (NumberFormatException ex) {
            sizeCellText.setText(String.valueOf(min));
            sizeCellSlider.setValue(min);
        }
        if (sizeCell < min) {
            setSliderAndText(1, sizeCellSlider, sizeCellText, String.valueOf(min));
        } else {
            if (sizeCell > max) {
                setSliderAndText(max, sizeCellSlider, sizeCellText, String.valueOf(max));
            } else {
                setSliderAndText(sizeCell, sizeCellSlider, sizeCellText, String.valueOf(sizeCell));
            }
        }
    }

    public static JPanel addOneLineSize(JTextField n, String n2) {
        JPanel width = new JPanel();
        width.setPreferredSize(new Dimension(50, 10));
        width.setLayout(new BoxLayout(width, BoxLayout.LINE_AXIS));
        Label N = new Label(n2);
        N.setPreferredSize(new Dimension(40, 10));
        width.add(N);
        width.add(n);
        return width;
    }

    private void modeSettings(JPanel jPanel) {
        ButtonGroup group = new ButtonGroup();
        group.add(replace);
        group.add(xor);
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.PAGE_AXIS));
        modePanel.add(replace);
        modePanel.add(xor);
        setBorder(modePanel, "Mode");
        modePanel.setMinimumSize(new Dimension(10, 10));
        jPanel.add(modePanel);
    }

    private void sizeSettings(JPanel jPanel) {
        JPanel sizeField = new JPanel();
        sizeField.setPreferredSize(new Dimension(80, 100));
        sizeSettings(sizeField, n, m);
        setBorder(sizeField, "Size");
        jPanel.add(sizeField, BorderLayout.AFTER_LAST_LINE);
    }

    static void sizeSettings(JPanel sizeField, JTextField n, JTextField m) {
        BoxLayout boxLayout = new BoxLayout(sizeField, BoxLayout.PAGE_AXIS);
        sizeField.setLayout(boxLayout);
        sizeField.add(addOneLineSize(n, "N"));
        sizeField.add(addOneLineSize(m, "M"));
    }

    private void setBorder(JPanel modePanel, String text) {
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, text);
        modePanel.setBorder(titled);
    }


    private void setSliderAndText(int widthLine, JSlider widthLineSlider, JTextField widthLineText, String s) {
        widthLineSlider.setValue(widthLine);
        widthLineText.setText(s);
    }
}
