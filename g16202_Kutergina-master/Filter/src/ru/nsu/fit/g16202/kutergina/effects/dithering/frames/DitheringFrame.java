package ru.nsu.fit.g16202.kutergina.effects.dithering.frames;

import ru.nsu.fit.g16202.kutergina.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class DitheringFrame extends JFrame {
    protected JTextField redText = new JTextField();
    protected JTextField greenText = new JTextField();
    protected JTextField blueText = new JTextField();
    protected JButton ok = new JButton("Ok");
    protected JButton cancel = new JButton("Cancel");


    protected abstract void doMethod(BufferedImage src, BufferedImage dst, int red, int green, int blue);

    public DitheringFrame(){
        super("Parameters");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(150, 100));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JPanel redPanel = getjPanel("R", redText);
        JPanel greenPanel = getjPanel("G", greenText);
        JPanel bluePanel = getjPanel("B", blueText);
        panel.add(redPanel);
        panel.add(greenPanel);
        panel.add(bluePanel);



        JPanel panelButtons = new JPanel();
        ok.addActionListener(e -> {
            try {
                int red = Integer.parseInt(redText.getText());
                int blue = Integer.parseInt(blueText.getText());
                int green = Integer.parseInt(greenText.getText());
                if(red > 255 || red < 2 || green > 255 || green < 2 || blue > 255 || blue < 2){
                    throw new NumberFormatException();
                }
                doMethod(Frame.zoneB.getBaseImage(), Frame.zoneC.getBaseImage(), red, green, blue);
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
