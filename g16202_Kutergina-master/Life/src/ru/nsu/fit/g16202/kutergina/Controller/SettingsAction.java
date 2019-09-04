package ru.nsu.fit.g16202.kutergina.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsAction implements ActionListener {
    private Communicator communicator;

    public SettingsAction(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        communicator.getSettings().setOldValues();
        communicator.getSettings().setVisible(true);
    }
}
