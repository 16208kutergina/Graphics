package ru.nsu.fit.g16202.kutergina;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.PI;

public class ParseFile {
    private Settings settings;
    private SettingsView settingsView;
    private Communicator communicator;

    public ParseFile(File file, Settings settings, SettingsView settingsView, Communicator communicator) throws IOException {
        this.settings = settings;
        this.communicator = communicator;
        this.settingsView = settingsView;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        readCommonParameters(bufferedReader);
        readPyramidParameters(bufferedReader);
        readMatrixScene(bufferedReader);
        Color color = readColor(bufferedReader);
        communicator.getFigurePanel().drawBackgroundImage(color);
        String s = getStringWithoutComment(bufferedReader.readLine()).replaceAll("\\s", "");
        ;
        int count = (int) Double.parseDouble(s);
        for (int i = 0; i < count; i++) {
            readFigure(bufferedReader);
        }

    }

    private void readFigure(BufferedReader bufferedReader) throws IOException {
        try {
            Color color = readColor(bufferedReader);
            Matrix matrix = readTranslate(bufferedReader);
            Matrix transformMatrix = readMatrix(bufferedReader);
            String s = getStringWithoutComment(bufferedReader.readLine()).replaceAll("\\s", "");
            ;
            int count = (int) Double.parseDouble(s);
            LinkedList<DoublePoint> arrayList = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                DoublePoint doublePoint = readCoordinates(bufferedReader);
                arrayList.add(doublePoint);
            }
            Spline spline = new Spline();
            spline.setGeneratePoints(arrayList);
            Figure figure = new Figure(spline, color, transformMatrix, matrix);
            communicator.getSplineScene().setNewSplineAndFigure(figure);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public DoublePoint readCoordinates(BufferedReader bufferedReader) throws IOException {
        try {
            String firstLine = getStringWithoutComment(bufferedReader.readLine());
            String[] fL = firstLine.split(" ");
            int sizeSide = settingsView.getSizeSideImageSpline() / 2;
            return new DoublePoint(Double.parseDouble(fL[0]) * sizeSide,
                    Double.parseDouble(fL[1]) * sizeSide);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private Matrix readTranslate(BufferedReader bufferedReader) throws IOException {
        try {
            String firstLine = getStringWithoutComment(bufferedReader.readLine());
            String[] fL = firstLine.split(" ");
            int sizeSide = settingsView.getSizeSideImageSpline() / 2;
            Matrix matrix = new Matrix(new double[]{
                    1, 0, 0, Double.parseDouble(fL[0]) * sizeSide,
                    0, 1, 0, Double.parseDouble(fL[1]) * sizeSide,
                    0, 0, 1, Double.parseDouble(fL[2]) * sizeSide,
                    0, 0, 0, 1
            });
            return matrix;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private Color readColor(BufferedReader bufferedReader) throws IOException {
        try {
            String s = getStringWithoutComment(bufferedReader.readLine());
            String[] split = s.split(" ");
            System.out.println(Arrays.toString(split));
            return new Color((int) Double.parseDouble(split[0]), (int) Double.parseDouble(split[1]), (int) Double.parseDouble(split[2]));
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private void readMatrixScene(BufferedReader bufferedReader) throws IOException {
        try {
            Matrix matrix = readMatrix(bufferedReader);
            communicator.getFigurePanel().setSceneTransformMatrix(matrix);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private Matrix readMatrix(BufferedReader bufferedReader) throws IOException {
        try {
            String firstLine = getStringWithoutComment(bufferedReader.readLine());
            String[] fL = firstLine.split(" ");
            String secondLine = getStringWithoutComment(bufferedReader.readLine());
            String[] sL = secondLine.split(" ");
            String thirdLine = getStringWithoutComment(bufferedReader.readLine());
            String[] tL = thirdLine.split(" ");
            Matrix matrix = new Matrix(new double[]{
                    Double.parseDouble(fL[0]), Double.parseDouble(fL[1]), Double.parseDouble(fL[2]), 0,
                    Double.parseDouble(sL[0]), Double.parseDouble(sL[1]), Double.parseDouble(sL[2]), 0,
                    Double.parseDouble(tL[0]), Double.parseDouble(tL[1]), Double.parseDouble(tL[2]), 0,
                    0, 0, 0, 1
            });

            return matrix;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private void readPyramidParameters(BufferedReader bufferedReader) throws IOException {
        try {
            String s = getStringWithoutComment(bufferedReader.readLine());
            String[] split = s.split(" ");
            settings.setZf(Double.parseDouble(split[0]));
            settings.setZb(Double.parseDouble(split[1]));
            settings.setSw(Double.parseDouble(split[2]));
            settings.setSh(Double.parseDouble(split[3]));
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private void readCommonParameters(BufferedReader bufferedReader) throws IOException {
        String s = getStringWithoutComment(bufferedReader.readLine());
        String[] split = s.split(" ");
        try {
            settings.setN((int) Double.parseDouble(split[0]));
            settings.setM((int) Double.parseDouble(split[1]));
            settings.setK((int) Double.parseDouble(split[2]));
            settings.setA(Double.parseDouble(split[3]));
            settings.setB(Double.parseDouble(split[4]));
            settings.setC(Double.parseDouble(split[5]) * PI / 180.);
            settings.setD(Double.parseDouble(split[6]) * PI / 180.);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private String getStringWithoutComment(String s) {
        return s.split("//")[0];
    }

}
