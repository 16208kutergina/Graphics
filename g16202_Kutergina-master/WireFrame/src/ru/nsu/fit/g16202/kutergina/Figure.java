package ru.nsu.fit.g16202.kutergina;

import java.awt.*;
import java.util.LinkedList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Figure {
    private LinkedList<Section> sections = new LinkedList<>();
    private Spline spline;
    private Color color = Color.RED;
    private Matrix transformMatrix = new Matrix();
    private Matrix translateMatrix = new Matrix();
    private double lengthLine;
    private double maxCoord = Double.MIN_VALUE;

    public Spline getSpline() {
        return spline;
    }

    public Color getColor() {
        return color;
    }

    public double getMaxCoord() {
        return maxCoord;
    }

    public LinkedList<Section> getSections() {
        return sections;
    }

    public Matrix getTransformMatrix() {
        return transformMatrix;
    }

    public Matrix getTranslateMatrix() {
        return translateMatrix;
    }

    public void init() {
        transformMatrix = new Matrix();
    }

    public Figure(Spline spline,
                  Color color,
                  Matrix transformMatrix,
                  Matrix translateMatrix) {
        this.spline = spline;
        this.color = color;
        this.transformMatrix = transformMatrix;
        this.translateMatrix = translateMatrix;
    }

    public void moveLeft(double length) {
        translateMatrix.getMatrix()[3] -= length;
    }

    public void moveRight(double length) {
        translateMatrix.getMatrix()[3] += length;
    }

    public void moveUp(double length) {
        translateMatrix.getMatrix()[11] += length;
    }

    public void moveDown(double length) {
        translateMatrix.getMatrix()[11] -= length;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public void setTransformMatrix(Matrix transformMatrix) {
        this.transformMatrix = transformMatrix;
    }

    public void setTranslateMatrix(Matrix translateMatrix) {
        this.translateMatrix = translateMatrix;
    }

    public void updateFigure(double c, double d, int n, int m, int k, double step) {//вызывается вместе с изменением сплайна
        LinkedList<LinkedList<Vector3D1>> medians = getMedians(c, d, step);
        calculateSections(medians, n, m, k);
        this.color = color;
    }

    private Vector3D1 getCoordinates(DoublePoint pointSpline, double corner) {
        return new Vector3D1(pointSpline.getY() * cos(corner),
                pointSpline.getY() * sin(corner),
                pointSpline.getX());
    }

    private LinkedList<Vector3D1> Array2DTo3D(LinkedList<DoublePoint> points, double corner) {
        LinkedList<Vector3D1> points3D = new LinkedList<>();
        for (DoublePoint dp : points) {
            points3D.add(getCoordinates(dp, corner));
        }
        return points3D;
    }

    private LinkedList<LinkedList<Vector3D1>> getMedians(double c, double d, double step) {
        LinkedList<LinkedList<Vector3D1>> figure = new LinkedList<>();
        for (double angle = c; angle < d + step; angle += step) {
            LinkedList<Vector3D1> vector3D1s = Array2DTo3D(spline.getCutFunctionPoints(), angle);
            figure.add(vector3D1s);
        }
        return figure;
    }

    private void checkOnMax(Vector3D1 vector3D1) {
        double x = vector3D1.getX();
        double y = vector3D1.getY();
        double z = vector3D1.getZ();
        if (x > maxCoord) {
            maxCoord = x;
        }
        if (y > maxCoord) {
            maxCoord = y;
        }
        if (z > maxCoord) {
            maxCoord = z;
        }
    }

    private void calculateSections(LinkedList<LinkedList<Vector3D1>> medians, int n, int m, int k) {
        maxCoord = Double.MIN_VALUE;
        sections.clear();
        if (!spline.getCutFunctionPoints().isEmpty()) {
            for (int nMedian = 0; nMedian < medians.size(); nMedian++) {
                LinkedList<Vector3D1> list = medians.get(nMedian);
                int step = 0;
                try {
                    int part = list.size() % (n * k);
                    step = (list.size() - part) / (n * k);
                } catch (ArithmeticException e) {
                    System.out.println(e);
                }
                if (nMedian % k == 0) {
                    for (int nPoint = 0; nPoint < n * k - 1; nPoint++) {
                        checkOnMax(list.get(nPoint * step));
                        sections.add(new Section(list.get(nPoint * step), list.get((nPoint + 1) * step)));
                    }

                    sections.add(new Section(list.get((n * k - 1) * step), list.get(list.size() - 1)));
                }
            }
            for (int nMedian = 0; nMedian < medians.size() - medians.size() % (m * k); nMedian++) {
                LinkedList<Vector3D1> list1 = medians.get(nMedian);
                LinkedList<Vector3D1> list2 = medians.get(nMedian + 1);
                int step = (list1.size()) / (n * k);
                for (int nPoint = 0; nPoint < n * k; nPoint++) {
                    if (nPoint % k == 0) {
                        checkOnMax(list1.get(nPoint * step));
                        sections.add(new Section(list1.get(nPoint * step), list2.get(nPoint * step)));
                    }

                    sections.add(new Section(list1.get(list1.size() - 1), list2.get(list2.size() - 1)));
                }
            }

        }
    }
}
