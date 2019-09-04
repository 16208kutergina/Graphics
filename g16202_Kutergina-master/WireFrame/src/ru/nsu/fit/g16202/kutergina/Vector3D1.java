package ru.nsu.fit.g16202.kutergina;

import java.util.Arrays;

import static java.lang.Math.signum;
import static ru.nsu.fit.g16202.kutergina.Utils.multMatrix;

public class Vector3D1 {
    private double[] point = new double[4];

    public Vector3D1(double[] point) {
        this.point = point;
    }

    public void setPoint(double[] point) {
        if (point.length != 3) {
            throw new RuntimeException();
        }
        this.point = point;
    }

    @Override
    public String toString() {
        return "Vector3D1{" +
                "point=" + Arrays.toString(point) +
                '}';
    }

    public double[] getPoint() {
        return point;
    }

    public Vector3D1 normVector() {
        for (int i = 0; i < 4; i++) {
            point[i] = point[i] / signum(point[3]);
        }
        return this;
    }

    public Vector3D1(double x, double y, double z) {
        point[0] = x;
        point[1] = y;
        point[2] = z;
        point[3] = 1;
    }

    public double getX() {
        return point[0];
    }

    public void setX(double x) {
        point[0] = x;
    }

    public double getY() {
        return point[1];
    }

    public void setY(double y) {
        point[1] = y;
    }

    public double getZ() {
        return point[2];
    }

    public void setZ(double z) {
        point[2] = z;
    }

    public Vector3D1 multMatrixV(Matrix matrix) {
        point = multMatrix(matrix.getMatrix(), 4, 4, point, 4, 1);
        return this;
    }
}
