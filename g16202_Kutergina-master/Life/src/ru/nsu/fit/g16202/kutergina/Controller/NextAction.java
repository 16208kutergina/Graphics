package ru.nsu.fit.g16202.kutergina.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextAction implements ActionListener {
    private Communicator communicator;


    public NextAction(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Communicator.changeFlag = true;
        communicator.fieldToModel();
        communicator.getHexagonLifeAction().nextStateField();
        communicator.fieldToView();
        Communicator.repaintImpact(communicator);
    }
}
