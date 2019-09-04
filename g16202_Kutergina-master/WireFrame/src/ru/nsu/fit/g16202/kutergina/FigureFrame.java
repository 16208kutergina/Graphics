package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;

public class FigureFrame extends JFrame {
    private FigurePanel figurePanel;

    public FigurePanel getFigurePanel() {
        return figurePanel;
    }

    FigureFrame(Settings settings, SettingsView settingsView, Communicator communicator) {
        super("Scene");
        setResizable(false);
        setPreferredSize(new Dimension(settingsView.getSizeSideImageFigure(), settingsView.getSizeSideImageFigure()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        figurePanel = new FigurePanel(settings, settingsView, communicator);
        add(figurePanel);
        pack();
        setVisible(true);
    }


}
