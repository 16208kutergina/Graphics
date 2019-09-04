package ru.nsu.fit.g16202.kutergina.effects;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.nsu.fit.g16202.kutergina.Frame.zoneB;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneC;

public abstract class SlidersFrame extends JFrame {
    private int minValue = 0;
    private int maxValue = 360;
    private int defaultValue = 100;
    private JTextField text;
    private JSlider slider;
    private JButton ok;
    private JButton cancel;

    public SlidersFrame(){
        super("Parameters");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(150, 50));
        JPanel panel = new JPanel();
        slider = new JSlider(minValue, maxValue, defaultValue);
        slider.addChangeListener(e -> text.setText(String.valueOf(slider.getValue())));
        text = new JTextField();
        text.setPreferredSize(new Dimension(40, 20));
        text.setText(String.valueOf(defaultValue));
        text.addActionListener(e -> {
            int value = defaultValue;
            try {
                value = Integer.parseInt(text.getText());
            }catch (NumberFormatException ignored){
            }
            if(value < minValue){
                value = minValue;
            }
            if(value > maxValue){
                value = maxValue;
            }
            slider.setValue(value);
            text.setText(String.valueOf(value));
        });
        panel.add(slider, BorderLayout.EAST);
        panel.add(text, BorderLayout.WEST);

        JPanel panelButtons = new JPanel();
        ok = new JButton("OK");
        ok.addActionListener(e -> {
            try {
                int value = Integer.parseInt(text.getText());
                doMethod(zoneB.getBaseImage(), zoneC.getBaseImage(), value);
                zoneC.setImage(zoneC.getBaseImage());
                dispose();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Incorrect data");
            }
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(e -> dispose());
        panelButtons.add(ok);
        panelButtons.add(cancel);

        add(panel, BorderLayout.PAGE_START);
        add(panelButtons, BorderLayout.PAGE_END);
        pack();
        setVisible(true);
    }


    protected abstract void doMethod(BufferedImage baseImage, BufferedImage baseImageC, int value) ;

}
