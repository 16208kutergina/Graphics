package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.AboutDialog;

import java.awt.event.ActionEvent;

public class About extends JButtonFunctional {
    public About() {
        super("About", "About", "image/about.png");
    }

    @Override
    protected void go(ActionEvent e) {
new AboutDialog();
    }
}
