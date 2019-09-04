package ru.nsu.fit.g16202.kutergina.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusBar extends JLabel {
    private static StatusBar statusBar = new StatusBar();

    private StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 20));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        setMessage("Ready");
    }

    public static StatusBar getInstance() {
        return statusBar;
    }

    public void setMessage(String message) {
        setText(" "+message);
    }
}
