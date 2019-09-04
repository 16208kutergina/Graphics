package ru.nsu.fit.g16202.kutergina;

import static java.lang.Math.PI;

public class Settings {
    private double a = 0.0;
    private double b = 1.0;
    private double c = 0.;
    private double d = 2. * PI;

    private double maxCoord = 250.;

    public double getMaxCoord() {
        return maxCoord;
    }

    public void setMaxCoord(double maxCoord) {
        this.maxCoord = maxCoord;
    }

    private double n = 10;
    private double m = 10;

    private double k = 2;

    private double sw = 1;
    private double sh = 1;
    private double zf = 3;
    private double zb = 1.1;

    public void setA(double a) {
        this.a = a;
        updateCountDetailCells();
    }

    public void setB(double b) {
        this.b = b;
        updateCountDetailCells();
    }

    public void setC(double c) {
        this.c = c;
        updateCountDetailCells();
    }

    public void setD(double d) {
        this.d = d;
        updateCountDetailCells();
    }

    public void setN(double n) {
        this.n = n - 1;
        updateCountDetailCells();
    }

    public void setM(double m) {
        this.m = m;
        updateCountDetailCells();
    }

    public double getSw() {
        return sw;
    }

    public void setSw(double sw) {
        this.sw = sw;
    }

    public double getSh() {
        return sh;
    }

    public void setSh(double sh) {
        this.sh = sh;
    }

    public double getZf() {
        return zf;
    }

    public void setZf(double zf) {
        this.zf = zf;
    }

    public double getZb() {
        return zb;
    }

    public void setZb(double zb) {
        this.zb = zb;
    }

    private double countDetailCellsX = n * k;
    private double countDetailCellsY = m * k;

    private void updateCountDetailCells() {
        countDetailCellsX = n * k;
        countDetailCellsY = m * k;
        widthCell = (b - a) / n;
        heightCell = (d - c) / m;

        widthDetailCell = (b - a) / countDetailCellsX;
        heightDetailCell = (d - c) / countDetailCellsY;
    }

    public int getCountDetailCellsX() {
        return (int) countDetailCellsX;
    }

    public int getCountDetailCellsY() {
        return (int) countDetailCellsY;
    }

    public int getK() {
        return (int) k;
    }

    public void setK(double k) {
        this.k = k;
        updateCountDetailCells();
    }

    private double widthCell = (b - a) / (double) n;
    private double heightCell = (d - c) / (double) m;

    private double widthDetailCell = (b - a) / (double) countDetailCellsX;
    private double heightDetailCell = (d - c) / (double) countDetailCellsY;

    public double getWidthDetailCell() {
        return widthDetailCell;
    }

    public void setWidthDetailCell(double widthDetailCell) {
        this.widthDetailCell = widthDetailCell;
    }

    public double getHeightDetailCell() {
        return heightDetailCell;
    }

    public void setHeightDetailCell(double heightDetailCell) {
        this.heightDetailCell = heightDetailCell;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getD() {
        return d;
    }

    public double getC() {
        return c;
    }

    public int getN() {
        return (int) n;
    }

    public int getM() {
        return (int) m;
    }

    public double getWidthCell() {
        return widthCell;
    }

    public double getHeightCell() {
        return heightCell;
    }
}
