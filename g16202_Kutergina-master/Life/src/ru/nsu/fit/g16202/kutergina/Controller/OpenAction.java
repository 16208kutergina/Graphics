package ru.nsu.fit.g16202.kutergina.Controller;


import ru.nsu.fit.g16202.kutergina.Model.ParseFile;
import ru.nsu.fit.g16202.kutergina.View.SaveDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OpenAction implements ActionListener {
    private Communicator communicator;

    public OpenAction(Communicator communicator){
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Communicator.changeFlag) {
            JDialog dialog = new SaveDialog(communicator, this::openFile);
            Communicator.changeFlag = false;
        } else {
            openFile();
            Communicator.changeFlag = false;
        }

    }

    private void openFile() {
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.setCurrentDirectory(new File("Data"));
        fileOpen.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int ret = fileOpen.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
            ParseFile parseFile = new ParseFile(communicator);
            Communicator.changeFlag = false;
            try {
                parseFile.setField(file);
            } catch (IOException e1) {
                System.out.println("Incorrect file");
            }
        }
        if(FlagsButton.isImpact()){
            double[][] impacts = communicator.getHexagonLifeAction().getImpacts();
            communicator.getHexagonField().printImpact(impacts);
        }
    }
}
