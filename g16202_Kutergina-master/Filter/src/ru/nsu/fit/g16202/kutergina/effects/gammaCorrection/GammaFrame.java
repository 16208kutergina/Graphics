package ru.nsu.fit.g16202.kutergina.effects.gammaCorrection;

import ru.nsu.fit.g16202.kutergina.Frame;

import javax.swing.*;
import java.awt.*;

public class GammaFrame extends JFrame {
    private JButton ok;
    private JButton cancel;
    private JTextField textField;
    private float min = 0;
    private float max = 10;

     public GammaFrame(){
         super("Parameters");
         setLocationRelativeTo(null);
         setMinimumSize(new Dimension(100, 60));
         JPanel panel = new JPanel();
         JLabel label = new JLabel("Gamma 0-10");
         textField = new JTextField();
         textField.setPreferredSize(new Dimension(40, 20));
         textField.addActionListener(e -> {
             float gamma = 1;
             try{
                 gamma = Float.valueOf(textField.getText());
             }catch (NumberFormatException ignored){
             }
             if(gamma < min){
                 gamma = min;
             }
             if(gamma > max){
                 gamma = max;
             }
             textField.setText(String.valueOf(gamma));
         });
         panel.add(label, BorderLayout.EAST);
         panel.add(textField, BorderLayout.WEST);



         JPanel panelButtons = new JPanel();
         ok = new JButton("OK");
         ok.addActionListener(e -> {
             float gamma = Float.parseFloat(textField.getText());
             GammaCorrection gammaCorrection = new GammaCorrection();
             gammaCorrection.setGamma(gamma);
             gammaCorrection.apply(Frame.zoneB.getBaseImage(), Frame.zoneC.getBaseImage());
             Frame.zoneC.setImage(Frame.zoneC.getBaseImage());
             dispose();
         });
         cancel = new JButton("Cansel");
         cancel.addActionListener(e -> dispose());
         panelButtons.add(ok);
         panelButtons.add(cancel);

         add(panel, BorderLayout.PAGE_START);
         add(panelButtons, BorderLayout.LINE_END);

         pack();
         setVisible(true);
     }

     public static void main(String[]args){
         new GammaFrame();
     }
}
