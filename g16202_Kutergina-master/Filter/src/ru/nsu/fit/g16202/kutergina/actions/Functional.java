package ru.nsu.fit.g16202.kutergina.actions;

import javax.swing.*;

public abstract class Functional {
    AbstractButton button;
    AbstractButton menuItem;

    public void setEnabled(boolean state){
        button.setEnabled(state);
        menuItem.setEnabled(state);
    }


    public AbstractButton getButton() {
        return button;
    }

    public AbstractButton getMenuItem() {
        return menuItem;
    }
}
