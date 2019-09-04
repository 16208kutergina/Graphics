package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;
import ru.nsu.fit.g16202.kutergina.Controller.Initiator;
import ru.nsu.fit.g16202.kutergina.Model.SaveFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SaveDialog extends JDialog {
    private Communicator communicator;
    private JButton yes;
    private JButton no;



    public void setCommunicator(Communicator communicator){
        this.communicator = communicator;
    }

    public SaveDialog(Communicator communicator, Initiator initiator){
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                initiator.onSave();
            }
        });
        setVisible(true);
        Label label = new Label("Do you want save changes?");
        yes = new JButton("Yes");
        no = new JButton("No");

        yes.addActionListener(e -> {
            SaveFile.saveFile(communicator);
            dispose();
            initiator.onSave();
        });

        no.addActionListener(e -> {
            dispose();
            initiator.onSave();
        });

        add(label, BorderLayout.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.add(yes, BorderLayout.EAST);
        jPanel.add(no, BorderLayout.WEST);
        add(jPanel, BorderLayout.PAGE_END);
        pack();
    }

}
