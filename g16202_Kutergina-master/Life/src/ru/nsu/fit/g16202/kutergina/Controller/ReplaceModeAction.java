package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.View.ModeField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplaceModeAction implements ActionListener {
    private Communicator communicator;

    public ReplaceModeAction(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        communicator.getHexagonField().setMode(ModeField.REPLACE);
        communicator.getLifeMenuBar().getReplace().setSelected(true);
        communicator.getLifeToolBar().getReplaceButton().setSelected(true);
        communicator.getLifeToolBar().getXorButton().setSelected(false);
        communicator.getSettings().getReplace().setSelected(true);
        communicator.getSettings().getXor().setSelected(false);

    }
}
