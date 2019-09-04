package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.Model.SaveFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAction implements ActionListener {
    Communicator communicator;

    public SaveAction(Communicator communicator){
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveFile.saveFile(communicator);
    }
}

