package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;

public class ColorChooser extends JFrame {

    public ColorChooser(Communicator communicator) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JColorChooser colorChooser = new JColorChooser();

        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> {
            Color c = colorChooser.getColor();
            if (communicator.getSplineScene().getActiveFigure() != null) {
                communicator.getSplineScene().getActiveFigure().setColor(c);
            }
            communicator.getSplineScene().setLastUsableColor(c);
        });
        add(colorChooser, BorderLayout.CENTER);
        add(ok, BorderLayout.PAGE_END);
        pack();
        setVisible(true);
    }
}
