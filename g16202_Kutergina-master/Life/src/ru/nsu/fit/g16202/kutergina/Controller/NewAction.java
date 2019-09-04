package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.Model.Constants;
import ru.nsu.fit.g16202.kutergina.View.NewDialog;
import ru.nsu.fit.g16202.kutergina.View.SaveDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAction implements ActionListener {
    private Communicator communicator;


    public NewAction(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Communicator.changeFlag) {
            JDialog dialog = new SaveDialog(communicator, () -> {
                try {
                    Constants.setWidthLine(1);
                    Constants.setSizeCell(15);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                communicator.getLifeToolBar().getImpactsButton().setSelected(false);
                communicator.getLifeMenuBar().getImpact().setState(false);
                FlagsButton.setImpact();
                Communicator.changeFlag = false;
                setNewField();
            });
        } else {
            communicator.getLifeToolBar().getImpactsButton().setSelected(false);
            communicator.getLifeMenuBar().getImpact().setState(false);
            FlagsButton.setImpact();
            Communicator.changeFlag = false;
            setNewField();
        }

    }

    private void setNewField() {
        new NewDialog(communicator);
    }
}
