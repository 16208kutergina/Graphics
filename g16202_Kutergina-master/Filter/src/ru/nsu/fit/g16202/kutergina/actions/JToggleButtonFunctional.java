package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public abstract class JToggleButtonFunctional extends Functional{
    boolean isSelected = false;

    protected abstract void go(ActionEvent e);

    public JToggleButtonFunctional(String text, String tooltip, String filePath){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go(e);
            }
        };
        button = new JToggleButton(action);
        menuItem = new JCheckBoxMenuItem(action);
        button.setToolTipText(tooltip);
        menuItem.setText(text);
        URL nextURL = Frame.class.getResource(filePath);
        ImageIcon nextIcon = new ImageIcon(nextURL);
        button.setIcon(nextIcon);
    }

    public void setSelected(boolean state){
        button.setSelected(state);
        menuItem.setSelected(state);
    }
}
