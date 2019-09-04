package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public abstract class JButtonFunctional extends Functional{

    protected abstract void go(ActionEvent e);

    public JButtonFunctional(String text, String tooltip, String filePath){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go(e);
            }
        };
        button = new JButton(action);
        menuItem = new JMenuItem(action);
        button.setToolTipText(tooltip);
        menuItem.setText(text);
        URL nextURL = Frame.class.getResource(filePath);
        ImageIcon nextIcon = new ImageIcon(nextURL);
        button.setIcon(nextIcon);
    }
}
