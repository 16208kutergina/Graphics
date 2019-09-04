package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static ru.nsu.fit.g16202.kutergina.Utils.clearImage;
import static ru.nsu.fit.g16202.kutergina.Utils.findLength;

public class SplineScene extends JPanel {
    private Settings settings;
    private SettingsView settingsView;
    private Communicator communicator;

    private BufferedImage background;
    private BufferedImage sceneSpline;

    private Graphics2D graphicsSpline;

    private Spline spline;
    private Figure activeFigure;
    private boolean isOldFigure = false;

    private Color lastUsableColor = Color.BLACK;

    public void setLastUsableColor(Color lastUsableColor) {
        this.lastUsableColor = lastUsableColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(sceneSpline, 0, 0, null);
    }

    public SplineScene(Settings settings, SettingsView settingsView, Communicator communicator) {

        this.settings = settings;
        this.settingsView = settingsView;
        this.communicator = communicator;

        setNewScene();
        MyMouse myMouse = new MyMouse();
        addMouseMotionListener(myMouse);
        addMouseListener(myMouse);
    }

    public void setNewSplineAndFigure(Figure figure) {
        spline = figure.getSpline();
        activeFigure = figure;
        communicator.getFigurePanel().addFigure(figure);
        isOldFigure = true;
        repaintScene();
    }

    public void setActiveFigure(Figure figure) {
        if (figure != null) {
            activeFigure = figure;
            spline = figure.getSpline();
            isOldFigure = true;
        } else {
            setNewScene();
        }
        repaintScene();
        communicator.getFigurePanel().updateFigure();
    }

    public void setNewScene() {
        int sizeSideImageSpline = settingsView.getSizeSideImageSpline();
        background = new BufferedImage(sizeSideImageSpline, sizeSideImageSpline, BufferedImage.TYPE_INT_ARGB);
        sceneSpline = new BufferedImage(sizeSideImageSpline, sizeSideImageSpline, BufferedImage.TYPE_INT_ARGB);
        graphicsSpline = sceneSpline.createGraphics();
        spline = new Spline();
        isOldFigure = false;
        setPreferredSize(new Dimension(sizeSideImageSpline, sizeSideImageSpline));
        drawBackground(settingsView.getColorBackgroundSpline());
        repaint();
    }

    public void repaintScene() {
        spline.calculateSpline(settings.getA(), settings.getB());
        clearImage(graphicsSpline, sceneSpline.getWidth(), sceneSpline.getHeight());
        drawPoints();
        drawSpline();
        repaint();
        repaintFigure();
    }

    private void repaintFigure() {
        if (spline.getGeneratePoints().size() > 3) {
            if (isOldFigure) {
                communicator.getFigurePanel().updateFigure();
            } else {
                activeFigure = new Figure(spline, lastUsableColor, new Matrix(), new Matrix());
                communicator.getFigurePanel().addFigure(activeFigure);

                isOldFigure = true;
            }
        }
    }

    public Figure getActiveFigure() {
        return activeFigure;
    }

    public void drawSpline() {
        double onePercentLine = spline.getLength() / 100.;
        double printedLength = 0;
        LinkedList<DoublePoint> functionPoints = spline.getFunctionPoints();
        for (int i = 0; i < functionPoints.size() - 1; i++) {
            double printedPercent = printedLength / onePercentLine / 100.;
            if (printedPercent < settings.getA()
                    || printedPercent > settings.getB()) {
                graphicsSpline.setColor(Color.DARK_GRAY);
            } else {
                graphicsSpline.setColor(settingsView.getColorLineSpline());
            }
            DoublePoint pointLeft = functionPoints.get(i);
            DoublePoint pointRight = functionPoints.get(i + 1);
            printedLength += findLength(pointLeft, pointRight);
            graphicsSpline.drawLine((int) (pointLeft.getX() + settingsView.getSizeSideImageSpline() / 2.), (int) (pointLeft.getY() + settingsView.getSizeSideImageSpline() / 2.), (int) (pointRight.getX() + settingsView.getSizeSideImageSpline() / 2.), (int) (pointRight.getY() + settingsView.getSizeSideImageSpline() / 2.));
        }
    }

    private void drawPoints() {
        clearImage(graphicsSpline, sceneSpline.getWidth(), sceneSpline.getHeight());
        graphicsSpline.setColor(settingsView.getColorPointSpline());
        LinkedList<DoublePoint> generatePoints = spline.getGeneratePoints();
        int radiusPointSpline = settingsView.getRadiusPointSpline();
        DoublePoint v;
        DoublePoint v1;
        for (int i = 0; i < generatePoints.size() - 1; i++) {
            v = generatePoints.get(i);
            v1 = generatePoints.get(i + 1);
            graphicsSpline.drawOval((int) (v.getX() + settingsView.getSizeSideImageSpline() / 2. - radiusPointSpline), (int) (v.getY() + settingsView.getSizeSideImageSpline() / 2. - radiusPointSpline), 2 * radiusPointSpline, 2 * radiusPointSpline);
            graphicsSpline.drawLine((int) (v.getX() + settingsView.getSizeSideImageSpline() / 2.), (int) (v.getY() + settingsView.getSizeSideImageSpline() / 2.), (int) (v1.getX() + settingsView.getSizeSideImageSpline() / 2.), (int) (v1.getY() + settingsView.getSizeSideImageSpline() / 2.));
        }
        if (generatePoints.size() > 0) {
            v = generatePoints.get(generatePoints.size() - 1);
            graphicsSpline.drawOval((int) (v.getX() + settingsView.getSizeSideImageSpline() / 2. - radiusPointSpline), (int) (v.getY() + settingsView.getSizeSideImageSpline() / 2. - radiusPointSpline), 2 * radiusPointSpline, 2 * radiusPointSpline);
        }
        repaint();
    }

    private class MyMouse extends MouseAdapter {
        DoublePoint workingPoint = null;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                DoublePoint point = findPoint(e.getX() - settingsView.getSizeSideImageSpline() / 2., e.getY() - settingsView.getSizeSideImageSpline() / 2.);
                if (point != null) {
                    deletePoint(point);
                }
            } else {
                addNewPoint(e.getX() - settingsView.getSizeSideImageSpline() / 2, e.getY() - settingsView.getSizeSideImageSpline() / 2);
            }
            repaintScene();
        }

        private void addNewPoint(int x, int y) {
            spline.getGeneratePoints().add(new DoublePoint(x, y));
        }


        @Override
        public void mousePressed(MouseEvent e) {
            DoublePoint point = findPoint(e.getX() - settingsView.getSizeSideImageSpline() / 2., e.getY() - settingsView.getSizeSideImageSpline() / 2.);
            if (point != null) {
                workingPoint = point;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            workingPoint = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (workingPoint != null) {
                workingPoint.setLocation(e.getX() - settingsView.getSizeSideImageSpline() / 2., e.getY() - settingsView.getSizeSideImageSpline() / 2.);
            }
            repaintScene();
        }



        private void deletePoint(DoublePoint point) {
            LinkedList<DoublePoint> generatePoints = spline.getGeneratePoints();
            generatePoints.remove(point);
        }


        private DoublePoint findPoint(double x, double y) {
            LinkedList<DoublePoint> generatePoints = spline.getGeneratePoints();
            for (DoublePoint p : generatePoints) {
                double xp = p.getX();
                double yp = p.getY();
                double radiusPoints = settingsView.getRadiusPointSpline();
                if (x < (xp + radiusPoints)
                        && x > (xp - radiusPoints)
                        && y < yp + radiusPoints
                        && y > yp - radiusPoints) {
                    return p;
                }
            }
            return null;
        }
    }

    private void drawBackground(Color colorBackground) {
        Graphics2D graphics = background.createGraphics();
        graphics.setColor(colorBackground);
        int width = background.getWidth();
        int height = background.getHeight();
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.white);
        graphics.drawLine(0, height / 2, width, height / 2);
        graphics.drawLine(width / 2, 0, width / 2, height);
    }


}
