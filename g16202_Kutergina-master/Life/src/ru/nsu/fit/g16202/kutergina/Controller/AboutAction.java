package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.View.AboutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new AboutDialog();
    }
}
