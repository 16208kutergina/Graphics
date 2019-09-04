package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.View.SaveDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActions implements ActionListener {
    Communicator communicator;

    public ExitActions(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Communicator.changeFlag) {
            JDialog dialog = new SaveDialog(communicator, () -> {
                System.exit(0);
            });
        } else {
            System.exit(0);
        }
    }
}
