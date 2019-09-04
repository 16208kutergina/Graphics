package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;

public class SplineFrame extends JFrame {
    private SplineScene splineScene;

    public SplineScene getSplineScene() {
        return splineScene;
    }

    SplineFrame(Settings settings, SettingsView settingsView, Communicator communicator) {
        super("Spline");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        splineScene = new SplineScene(settings, settingsView, communicator);
        SettingsPanel settingsPanel = new SettingsPanel(settings, settingsView, communicator);
        communicator.setSettingsPanel(settingsPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.add(communicator.getSettingsPanel().getOpen());
        menuBar.add(file);
        JMenu navigate = new JMenu("Navigate");
        navigate.add(communicator.getSettingsPanel().getNewI());
        navigate.add(communicator.getSettingsPanel().getDelete());
        navigate.add(communicator.getSettingsPanel().getInit());
        navigate.add(communicator.getSettingsPanel().getColor());
        menuBar.add(navigate);
        menuBar.add(communicator.getSettingsPanel().getAbout());
        add(menuBar, BorderLayout.PAGE_START);
        add(splineScene, BorderLayout.CENTER);
        add(settingsPanel, BorderLayout.PAGE_END);
        pack();
        setVisible(true);
    }
}
