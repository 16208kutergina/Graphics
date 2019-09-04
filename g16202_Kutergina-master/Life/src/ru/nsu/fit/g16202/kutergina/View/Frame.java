package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;
import ru.nsu.fit.g16202.kutergina.Model.HexagonLifeAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static ru.nsu.fit.g16202.kutergina.Controller.Communicator.changeFlag;

public class Frame extends JFrame {
    private HexagonLifeAction hexagonLifeAction = new HexagonLifeAction();
    private HexagonField hexagonField = new HexagonField();
    private LifeToolBar lifeToolBar = new LifeToolBar();
    private LifeMenuBar lifeMenuBar = new LifeMenuBar();
    private Settings settings = new Settings();


    public Frame(){
        super("Life");
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        Communicator communicator = new Communicator(hexagonLifeAction,
                hexagonField,
                lifeToolBar,
                lifeMenuBar,
                settings);
        settings.setCommunicator(communicator);
        JMenuBar menuBar = new JMenuBar();
        lifeMenuBar.setMenuBar(menuBar);
        setJMenuBar(menuBar);
        lifeMenuBar.setCommunicator(communicator);

        JToolBar toolBar = lifeToolBar.setToolBar();
        add(toolBar, BorderLayout.PAGE_START);
        lifeToolBar.setCommunicator(communicator);


        getContentPane().add(StatusBar.getInstance(), BorderLayout.SOUTH);

        JScrollPane jScrollPane = new JScrollPane(hexagonField);
        lifeMenuBar.getStatusBar().addActionListener(e -> {

            if (lifeMenuBar.getStatusBar().isSelected()) {
                StatusBar.getInstance().setVisible(true);
            } else {
                StatusBar.getInstance().setVisible(false);
            }
        });

        lifeMenuBar.getToolbar().addActionListener(e -> {
            if (lifeMenuBar.getToolbar().isSelected()) {
                toolBar.setVisible(true);
            } else {
                toolBar.setVisible(false);
            }
        });

        hexagonField.setScroll(jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(hexagonField.getWidth(), hexagonField.getHeight()));
        add(jScrollPane, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (changeFlag) {
                    JDialog dialog = new SaveDialog(communicator, () -> {
                        System.exit(0);
                    });
                } else {
                    System.exit(0);
                }
            }
        });
        pack();
        setVisible(true);
    }
}
