package ru.nsu.fit.g16202.kutergina;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix {
    double[] matrix = {
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1};

    public Matrix() {

    }


    public Matrix(double[] matrix) {
        this.matrix = matrix;
    }

    public double[] getMatrix() {
        return matrix;
    }

    public Matrix multMatrix(Matrix matrix) {
        double[] newMatrix = matrix.getMatrix();
        double[] result = new double[4 * 4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int z = 0; z < 4; z++) {
                    result[i * 4 + j] += this.matrix[i * 4 + z] * newMatrix[z * 4 + j];
                }
            }
        }
        this.matrix = result;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                s.append(matrix[4 * i + j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static Matrix generateMatrixX(double angle) {
        return new Matrix(new double[]{
                1, 0, 0, 0,
                0, cos(angle), -sin(angle), 0,
                0, sin(angle), cos(angle), 0,
                0, 0, 0, 1});
    }

    public static Matrix generateMatrixY(double angle) {
        return new Matrix(new double[]{
                cos(angle), 0, sin(angle), 0,
                0, 1, 0, 0,
                -sin(angle), 0, cos(angle), 0,
                0, 0, 0, 1});
    }

    public static Matrix generateMatrixZ(double angle) {
        return new Matrix(new double[]{
                cos(angle), -sin(angle), 0, 0,
                sin(angle), cos(angle), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1});
    }

    public static Matrix generateProjectionMatrix(double sw, double sh, double zf, double zb) {
        return new Matrix(new double[]{
                2. * zf / sw, 0, 0, 0,
                0, 2. * zf / sh, 0, 0,
                0, 0, zb / (zb - zf), -zf * zb / (zb - zf),
                0, 0, 1, 0});
    }

    public static Matrix yetOneMatrix(double zn) {
        return new Matrix(new double[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 1 / zn,
                0, 0, 1, 0});
    }
}
