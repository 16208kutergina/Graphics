package ru.nsu.fit.g16202.kutergina;

import java.util.LinkedList;

import static ru.nsu.fit.g16202.kutergina.Utils.findLength;
import static ru.nsu.fit.g16202.kutergina.Utils.multMatrix;

public class Spline {
    private double[] matrix = {
            -1. / 6., 3. / 6., -3. / 6, 1. / 6.,
            3. / 6., -6. / 6, 3. / 6., 0.,
            -3. / 6., 0, 3. / 6., 0,
            1. / 6., 4. / 6., 1. / 6., 0};

    private LinkedList<DoublePoint> generatePoints = new LinkedList<>();
    private LinkedList<DoublePoint> functionPoints = new LinkedList<>();
    private LinkedList<DoublePoint> cutFunctionPoints = new LinkedList<>();
    private double length = 0;


    public LinkedList<DoublePoint> getGeneratePoints() {
        return generatePoints;
    }

    public void setGeneratePoints(LinkedList<DoublePoint> generatePoints) {
        this.generatePoints = generatePoints;
    }

    public LinkedList<DoublePoint> getFunctionPoints() {
        return functionPoints;
    }

    public void setFunctionPoints(LinkedList<DoublePoint> functionPoints) {
        this.functionPoints = functionPoints;
    }

    public LinkedList<DoublePoint> getCutFunctionPoints() {
        return cutFunctionPoints;
    }

    public void setCutFunctionPoints(LinkedList<DoublePoint> cutFunctionPoint) {
        this.cutFunctionPoints = cutFunctionPoint;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }


    private DoublePoint calculateOneValueSpline(double t, int i) {
        double[] pointsY = new double[4];
        double[] pointsX = new double[4];
        double x = 0;
        double y = 0;
        double[] T = new double[4];
        T[0] = t * t * t;
        T[1] = t * t;
        T[2] = t;
        T[3] = 1;
        for (int j = -1; j < 3; j++) {
            pointsX[j + 1] = generatePoints.get(j + i).getX();
            pointsY[j + 1] = generatePoints.get(j + i).getY();
        }
        double[] TM = multMatrix(T, 1, 4, matrix, 4, 4);
        x = multMatrix(TM, 1, 4, pointsX, 4, 1)[0];
        y = multMatrix(TM, 1, 4, pointsY, 4, 1)[0];
        return new DoublePoint(x, y);
    }


    public void calculateSpline(double a, double b) {
        functionPoints.clear();
        length = 0;
        if (generatePoints.size() > 3) {
            for (int i = 1; i < generatePoints.size() - 2; i++) {
                for (double t = 0; t < 1.; t += 0.01) {
                    DoublePoint point = calculateOneValueSpline(t, i);
                    functionPoints.add(point);
                    int size = functionPoints.size();
                    if (size > 2) {
                        length += findLength(functionPoints.get(size - 1),
                                functionPoints.get(size - 2));
                    }
                }
            }

            calculateCutSpline(a, b);
        }
    }

    private void calculateCutSpline(double a, double b) {
        cutFunctionPoints.clear();
        double onePercentLine = length / 100.;
        double printedLength = 0;
        for (int i = 0; i < functionPoints.size() - 1; i++) {
            double printedPercent = printedLength / onePercentLine / 100.;
            if (printedPercent > a
                    && printedPercent < b) {
                cutFunctionPoints.add(functionPoints.get(i));

            }
            printedLength += findLength(functionPoints.get(i), functionPoints.get(i + 1));
        }
    }
}


