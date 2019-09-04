package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.View.ModeField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XorModeAction implements ActionListener {
    private Communicator communicator;

    public XorModeAction(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        communicator.getHexagonField().setMode(ModeField.XOR);
        communicator.getLifeMenuBar().getXor().setSelected(true);
        communicator.getLifeToolBar().getXorButton().setSelected(true);
        communicator.getLifeToolBar().getReplaceButton().setSelected(false);
        communicator.getSettings().getReplace().setSelected(false);
        communicator.getSettings().getXor().setSelected(true);
    }
}
