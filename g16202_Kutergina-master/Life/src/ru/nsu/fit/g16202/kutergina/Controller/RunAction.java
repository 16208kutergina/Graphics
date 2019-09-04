package ru.nsu.fit.g16202.kutergina.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunAction implements ActionListener {
    private static Communicator communicator;
    private static Timer timer = new Timer(1000, e -> {
        communicator.fieldToModel();
        communicator.getHexagonLifeAction().nextStateField();
        communicator.fieldToView();
        Communicator.repaintImpact(communicator);
    });

    public RunAction(Communicator communicator) {
        RunAction.communicator = communicator;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Communicator.changeFlag = true;
        FlagsButton.setRun();
        if (!FlagsButton.isRun()) {
            timer.stop();
            communicator.getLifeToolBar().getRunButton().setSelected(false);
            communicator.getLifeMenuBar().getRun().setState(false);
        } else {
            timer.start();
            communicator.getLifeToolBar().getRunButton().setSelected(true);
            communicator.getLifeMenuBar().getRun().setState(true);
        }
    }}
