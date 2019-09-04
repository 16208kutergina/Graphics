package ru.nsu.fit.g16202.kutergina;

import static java.lang.Math.abs;
import static ru.nsu.fit.g16202.kutergina.Utils.multMatrix;

public class Section {
    private Vector3D1 start;
    private Vector3D1 end;
    private boolean isPrint = true;

    @Override
    public String toString() {
        return "Section{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }


    public Vector3D1 getStart() {
        return start;
    }

    public Vector3D1 getEnd() {
        return end;
    }

    public void setEnd(Vector3D1 end) {
        this.end = end;
    }

    public Section(double xStart, double yStart, double zStart, double xEnd, double yEnd, double zEnd) {
        this.start = new Vector3D1(xStart, yStart, zStart);
        this.end = new Vector3D1(xEnd, yEnd, zEnd);
    }

    public Section(Vector3D1 start, Vector3D1 end) {
        this.start = start;
        this.end = end;
    }

    public Section normalise() {
        start.normVector();
        end.normVector();
        return this;
    }



    public boolean isPrint() {
        return isPrint;
    }

    public Section checkOnBorder(Settings settings) {
        double maxCoord = settings.getMaxCoord();
        if (abs(start.getX()) >= maxCoord
                || abs(start.getY()) >= maxCoord
                || abs(start.getZ()) >= maxCoord
                || abs(end.getX()) >= maxCoord
                || abs(end.getY()) >= maxCoord
                || abs(end.getZ()) >= maxCoord) {
            isPrint = false;
        }
        return this;
    }

    public Section normToOneMOne(Settings settings) {
        double maxCoord = settings.getMaxCoord();
        double[] s = start.getPoint();
        double[] e = end.getPoint();
        for (int i = 0; i < 3; i++) {
            s[i] /= maxCoord;
            e[i] /= maxCoord;
        }

        return this;
    }

    public void setPrint(boolean print) {
        isPrint = print;
    }

    public Section createTransformCopy(Matrix transformMatrix) {
        Section section;
        double[] newStart = multMatrix(transformMatrix.getMatrix(), 4, 4, this.getStart().getPoint(), 4, 1);
        double[] newEnd = multMatrix(transformMatrix.getMatrix(), 4, 4, this.getEnd().getPoint(), 4, 1);
        section = new Section(new Vector3D1(newStart), new Vector3D1(newEnd));
        section.setPrint(this.isPrint);
        return section;
    }
}
