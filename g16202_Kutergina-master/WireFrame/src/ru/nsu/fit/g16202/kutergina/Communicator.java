package ru.nsu.fit.g16202.kutergina;

public class Communicator {
    private FigurePanel figurePanel;
    private SplineScene splineScene;
    private SettingsPanel settingsPanel;

    public FigurePanel getFigurePanel() {
        return figurePanel;
    }

    public SplineScene getSplineScene() {
        return splineScene;
    }

    public void setPanels(FigurePanel figurePanel, SplineScene splineScene) {
        this.figurePanel = figurePanel;
        this.splineScene = splineScene;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public void setSettingsPanel(SettingsPanel settingsPanel) {
        this.settingsPanel = settingsPanel;
    }
}
