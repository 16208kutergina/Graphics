package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog() {
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(350, 150));
        setTitle("About FIT_16202_Kutergina_Init");
        Label what = new Label("    Init Version 1.0");
        what.setFont(new Font("Serif", Font.PLAIN, 14));
        Label who = new Label("    FIT Kutergina 16202");
        who.setFont(new Font("Serif", Font.PLAIN, 14));
        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> dispose());
        add(what, BorderLayout.PAGE_START);
        add(who, BorderLayout.CENTER);
        add(ok, BorderLayout.AFTER_LAST_LINE);
        pack();
        setVisible(true);
    }
}