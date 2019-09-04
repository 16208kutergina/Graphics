package ru.nsu.fit.g16202.kutergina.volumeRendering;

import ru.nsu.fit.g16202.kutergina.Frame;

import javax.swing.*;
import java.awt.*;


public class VolumeFrame extends JFrame {
    protected JTextField xText = new JTextField();
    protected JTextField zText = new JTextField();
    protected JTextField yText = new JTextField();
    protected JButton ok = new JButton("Ok");
    protected JButton cancel = new JButton("Cancel");
    ParseFileGraph pg;



    public VolumeFrame(ParseFileGraph pg){
        super("Parameters");
        this.pg = pg;
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(150, 100));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JPanel redPanel = getjPanel("X", xText);
        JPanel greenPanel = getjPanel("Y", zText);
        JPanel bluePanel = getjPanel("Z", yText);
        xText.setText("50");
        yText.setText("50");
        zText.setText("50");
        panel.add(redPanel);
        panel.add(greenPanel);
        panel.add(bluePanel);



        JPanel panelButtons = new JPanel();
        ok.addActionListener(e -> {
            try {
                int x = Integer.parseInt(xText.getText());
                int z = Integer.parseInt(yText.getText());
                int y = Integer.parseInt(zText.getText());
                if(x > 350 || x < 1 || y > 350 || y < 1 || z > 350 || z < 1){
                    throw new NumberFormatException();
                }
                VolumeFilter volumeFilter = new VolumeFilter();
                volumeFilter.setMesh(x, y, z);
                volumeFilter.setParameters(pg.getCharges(), pg.getAbsorptionPoints(), pg.getEmissionPoints());
                volumeFilter.apply(Frame.zoneB.getBaseImage(), Frame.zoneC.getBaseImage());
                Frame.zoneC.setImage(Frame.zoneC.getBaseImage());
                dispose();
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Incorrect data");
            }

        });
        cancel.addActionListener(e -> dispose());
        panelButtons.add(ok);
        panelButtons.add(cancel);

        add(panel, BorderLayout.PAGE_START);
        add(panelButtons, BorderLayout.LINE_END);

        pack();
        setVisible(true);
    }

    private JPanel getjPanel(String s, JTextField text) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(s);
        text.setPreferredSize(new Dimension(50, 20));
        panel.add(label, BorderLayout.WEST);
        panel.add(text, BorderLayout.EAST);
        return panel;
    }
}
