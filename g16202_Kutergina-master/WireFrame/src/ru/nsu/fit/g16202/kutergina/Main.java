package ru.nsu.fit.g16202.kutergina;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        SettingsView settingsView = new SettingsView();
        Communicator communicator = new Communicator();
        FigureFrame figureFrame = new FigureFrame(settings, settingsView, communicator);
        SplineFrame splineFrame = new SplineFrame(settings, settingsView, communicator);


        communicator.setPanels(
                figureFrame.getFigurePanel(),
                splineFrame.getSplineScene());

    }
}
