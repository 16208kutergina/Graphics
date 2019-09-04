package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.PI;
import static ru.nsu.fit.g16202.kutergina.Utils.clearImage;

public class FigurePanel extends JPanel {
    private Settings settings;
    private SettingsView settingsView;
    private Communicator communicator;

    private BufferedImage scene;
    private Graphics2D graphics;

    private LinkedList<Figure> figures = new LinkedList<>();
    private ArrayList<Section> os = new ArrayList<>(3);
    private ArrayList<Section> square = new ArrayList<>(12);

    private Matrix projectionMatrix;
    private Matrix sceneTransformMatrix = new Matrix();
    private Matrix camera = new Matrix(new double[]{
            1., 0, 0, 0,
            0, 1., 0, 0,
            0, 0, 1., 10.,
            0, 0, 0, 1.});

    private double maxOnAllScene = Double.MIN_VALUE;
    private int numberActiveFigure = -1;
    private BufferedImage backgroundImage;

    public void drawBackgroundImage(Color color) {
        Graphics2D graphics = backgroundImage.createGraphics();
        graphics.setColor(color);
        graphics.fillRect(0, 0, settingsView.getSizeSideImageFigure(), settingsView.getSizeSideImageFigure());
    }

    public void setSceneTransformMatrix(Matrix sceneTransformMatrix) {
        this.sceneTransformMatrix = sceneTransformMatrix;
    }

    public FigurePanel(Settings settings, SettingsView settingsView, Communicator communicator) {
        this.settings = settings;
        this.settingsView = settingsView;
        this.communicator = communicator;

        scene = new BufferedImage(settingsView.getSizeSideImageFigure(), settingsView.getSizeSideImageFigure(), BufferedImage.TYPE_INT_ARGB);
        graphics = scene.createGraphics();

        projectionMatrix = Matrix.generateProjectionMatrix(settings.getSw(),
                settings.getSh(),
                settings.getZf(),
                settings.getZb());
        initOs();
        initSquare();
        drawScene();

        MyMouseListener mouseListener = new MyMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double preciseWheelRotation = e.getPreciseWheelRotation();
                if (settings.getZf() + preciseWheelRotation > 0) {
                    settings.setZf((settings.getZf() + preciseWheelRotation));
                    communicator.getSettingsPanel().updateZF();
                    updateFigures();
                }
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "previous");
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
        getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
        getActionMap().put("previous", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberActiveFigure = (numberActiveFigure + 1) % figures.size();
//                drawScene();
                communicator.getSplineScene().setActiveFigure(figures.get(numberActiveFigure));
            }
        });
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!figures.isEmpty()) {
                    figures.get(numberActiveFigure).moveLeft(0.1 * settings.getMaxCoord());
                    updateFigure();
                }
            }
        });
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!figures.isEmpty()) {
                    figures.get(numberActiveFigure).moveRight(0.1 * settings.getMaxCoord());
                    updateFigure();
                }
            }
        });
        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!figures.isEmpty()) {
                    figures.get(numberActiveFigure).moveUp(0.1 * settings.getMaxCoord());
                    updateFigure();
                }
            }
        });
        getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!figures.isEmpty()) {
                    figures.get(numberActiveFigure).moveDown(0.1 * settings.getMaxCoord());
                    updateFigure();
                }
            }
        });
        backgroundImage = new BufferedImage(settingsView.getSizeSideImageFigure(), settingsView.getSizeSideImageFigure(), BufferedImage.TYPE_INT_ARGB);
    }

    public void init() {
        for (Figure f : figures) {
            f.init();
        }
        sceneTransformMatrix = new Matrix();
        drawScene();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(scene, 0, 0, null);
    }

    public void deleteFigure() {
        if (!figures.isEmpty()) {
            figures.remove(numberActiveFigure);
            numberActiveFigure = figures.size() - 1;
            Figure activeFigure = null;
            if (!figures.isEmpty()) {
                activeFigure = figures.get(numberActiveFigure);
            }
            communicator.getSplineScene().setActiveFigure(activeFigure);
        }
    }

    private void initOs() {
        this.os.add(new Section(new Vector3D1(0, 0, 0), new Vector3D1(1, 0, 0)));
        this.os.add(new Section(new Vector3D1(0, 0, 0), new Vector3D1(0, 1, 0)));
        this.os.add(new Section(new Vector3D1(0, 0, 0), new Vector3D1(0, 0, 1)));
    }

    private void drawOs() {
        Matrix matrix = new Matrix()
                .multMatrix(camera)
                .multMatrix(sceneTransformMatrix);
        double zf = settings.getZf();
        graphics.setColor(Color.RED);
        Section x = os.get(0).createTransformCopy(matrix).createTransformCopy(Matrix.yetOneMatrix(zf)).createTransformCopy(projectionMatrix);
        drawSection(x);
        graphics.setColor(Color.GREEN);
        Section y = os.get(1).createTransformCopy(matrix).createTransformCopy(Matrix.yetOneMatrix(zf)).createTransformCopy(projectionMatrix);
        drawSection(y);
        graphics.setColor(Color.blue);
        Section z = os.get(2).createTransformCopy(matrix).createTransformCopy(Matrix.yetOneMatrix(zf)).createTransformCopy(projectionMatrix);
        drawSection(z);

    }

    private void drawSection(Section section) {
        Vector3D1 start = section.getStart();
        Vector3D1 end = section.getEnd();
        int halfSizeScene = settingsView.getSizeSideImageFigure() / 2;
        graphics.drawLine((int) (start.getX() * 30) + halfSizeScene,
                (int) (start.getY() * 30) + halfSizeScene,
                (int) (end.getX() * 30) + halfSizeScene,
                (int) (end.getY() * 30) + halfSizeScene);
    }


    private void initSquare() {
        square.add(new Section(new Vector3D1(/*0*/-1, -1, -1), new Vector3D1(/*0*/-1, -1, 1)));
        square.add(new Section(new Vector3D1(/*0*/-1, -1, -1), new Vector3D1(1, -1, -1)));
        square.add(new Section(new Vector3D1(/*0*/-1, -1, -1), new Vector3D1(/*0*/-1, 1, -1)));

        square.add(new Section(new Vector3D1(/*0*/-1, 1, -1), new Vector3D1(1, 1, -1)));
        square.add(new Section(new Vector3D1(/*0*/-1, 1, -1), new Vector3D1(/*0*/-1, 1, 1)));

        square.add(new Section(new Vector3D1(1, -1, -1), new Vector3D1(1, 1, -1)));
        square.add(new Section(new Vector3D1(/*0*/-1, 1, 1), new Vector3D1(/*0*/-1, -1, 1)));

        square.add(new Section(new Vector3D1(1, -1, -1), new Vector3D1(1, -1, 1)));
        square.add(new Section(new Vector3D1(1, -1, 1), new Vector3D1(/*0*/-1, -1, 1)));

        square.add(new Section(new Vector3D1(1, -1, 1), new Vector3D1(1, 1, 1)));

        square.add(new Section(new Vector3D1(1, 1, 1), new Vector3D1(1, 1, -1)));
        square.add(new Section(new Vector3D1(1, 1, 1), new Vector3D1(/*0*/-1, 1, 1)));
    }

    private void drawScene() {
        clearImage(graphics, scene.getWidth(), scene.getHeight());
        drawOs();
        drawSquare();
        for (int i = 0; i < figures.size(); i++) {
            Figure f = figures.get(i);
            if (i == numberActiveFigure) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(f.getColor());
            }
            drawFigures(f);
        }
        repaint();
    }

    private void drawSquare() {
        graphics.setColor(Color.BLUE);
        Matrix resultMatrix = new Matrix()
                .multMatrix(camera)
                .multMatrix(sceneTransformMatrix);
        double zf = settings.getZf();
        square.stream()
                .map(section -> section.createTransformCopy(resultMatrix))
                .map(section -> section.createTransformCopy(Matrix.yetOneMatrix(zf)))
                .map(section -> section.createTransformCopy(projectionMatrix))
                .peek(this::drawSection)
                .count();
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
        numberActiveFigure++;
        figure.updateFigure(settings.getC(),
                settings.getD(),
                settings.getN(),
                settings.getM(),
                settings.getK(),
                settings.getHeightDetailCell()
        );
        findNewMax();
        drawScene();
    }

    private void findNewMax() {
        for (int i = 0; i < figures.size(); i++) {
            Figure f = figures.get(i);
            if (f.getMaxCoord() > maxOnAllScene) {
                maxOnAllScene = f.getMaxCoord();
            }
        }
        settings.setMaxCoord(maxOnAllScene);
    }


    public void updateFigures() {
        projectionMatrix = Matrix.generateProjectionMatrix(settings.getSw(),
                settings.getSh(),
                settings.getZf(),
                settings.getZb());
        if (!figures.isEmpty()) {
            for (Figure f : figures)
                f.updateFigure(
                        settings.getC(),
                        settings.getD(),
                        settings.getN(),
                        settings.getM(),
                        settings.getK(),
                        settings.getHeightDetailCell()
                );
            findNewMax();
        }
        drawScene();
    }

    public void updateFigure() {
        projectionMatrix = Matrix.generateProjectionMatrix(settings.getSw(),
                settings.getSh(),
                settings.getZf(),
                settings.getZb());
        if (!figures.isEmpty()) {
            figures.get(numberActiveFigure).updateFigure(
                    settings.getC(),
                    settings.getD(),
                    settings.getN(),
                    settings.getM(),
                    settings.getK(),
                    settings.getHeightDetailCell()
            );
            findNewMax();
        }
        drawScene();
    }

    private class MyMouseListener extends MouseAdapter {
        DoublePoint lastState = null;
        @Override
        public void mouseDragged(MouseEvent e) {


            if (lastState == null) {
                lastState = new DoublePoint(e.getX(), e.getY());
                return;
            }
            DoublePoint newState = new DoublePoint(e.getX(), e.getY());
            double angleX = findAngle(newState.getX(), lastState.getX());
            double angleY = findAngle(newState.getY(), lastState.getY());
            lastState = new DoublePoint(e.getX(), e.getY());
            Matrix matrix = doTurnMatrix(angleX, angleY);
            if (e.isControlDown()) {
                sceneTransformMatrix.multMatrix(matrix);
            } else {
                if (!figures.isEmpty()) {
                    figures.get(numberActiveFigure).getTransformMatrix().multMatrix(matrix);
                }
            }
            drawScene();
        }

        private Matrix doTurnMatrix(double angleX, double angleY) {
            Matrix matrix = new Matrix();
            Matrix matrixX = Matrix.generateMatrixX(angleX);
            Matrix matrixY = Matrix.generateMatrixY(angleY);
            matrix.multMatrix(matrixX);
            matrix.multMatrix(matrixY);
            return matrix;
        }

        private double findAngle(double y, double y2) {
            double difY = y2 - y;
            return difY * PI / 360;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            lastState = null;
        }
    }

    private void drawFigures(Figure f) {
        LinkedList<Section> figure = f.getSections();
        Matrix transformMatrix = f.getTransformMatrix();
        Matrix translateMatrix = f.getTranslateMatrix();
        Matrix resultMatrix = new Matrix()
                .multMatrix(camera)
                .multMatrix(transformMatrix);
        figure.stream()
                .map(section -> section.createTransformCopy(resultMatrix))
                .map(section -> section.createTransformCopy(translateMatrix))
                .map(section -> section.createTransformCopy(sceneTransformMatrix))
                .map(section -> section.checkOnBorder(settings))
                .map(section -> section.normToOneMOne(settings))
                .map(section -> section.createTransformCopy(Matrix.yetOneMatrix(settings.getZf())))
                .map(section -> section.createTransformCopy(projectionMatrix))
                .peek(this::drawSection)
                .count();
    }

}
