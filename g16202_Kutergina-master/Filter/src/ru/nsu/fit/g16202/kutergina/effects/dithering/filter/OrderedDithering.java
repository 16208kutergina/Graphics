package ru.nsu.fit.g16202.kutergina.effects.dithering.filter;

import ru.nsu.fit.g16202.kutergina.Constants;
import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;

public class OrderedDithering implements Filter {

    int red;
    int green;
    int blue;

    public OrderedDithering(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void toOrderedDithering(BufferedImage image) {
        int height = min(Frame.zoneA.getBaseImage().getHeight(), Constants.sizeSideImage);
        int width = min(Frame.zoneA.getBaseImage().getWidth(), Constants.sizeSideImage);
        int sizeMatrixRed = 255 / red;
        int sizeMatrixGreen = 255 / green;
        int sizeMatrixBlue = 255 / blue;
        double[][] matrixRed = getMap(sizeMatrixRed);
        double[][] matrixGreen = getMap(sizeMatrixGreen);
        double[][] matrixBlue = getMap(sizeMatrixBlue);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Color color = new Color(image.getRGB(i, j));
                int colorRed = color.getRed();
                int colorGreen = color.getGreen();
                int colorBlue = color.getBlue();
                int nRed = FloydSteinberg.getClosingColor(red, (int) (colorRed + (256. / (red - 1.)) * (matrixRed[i % matrixRed.length][j % matrixRed.length] - 0.5)));
                int nGreen = FloydSteinberg.getClosingColor(green, (int) (colorGreen + (256. / (green - 1.)) * (matrixGreen[i % matrixGreen.length][j % matrixGreen.length] - 0.5)));
                int nBlue = FloydSteinberg.getClosingColor(blue, (int) (colorBlue + (256. / (blue - 1.)) * (matrixBlue[i % matrixBlue.length][j % matrixBlue.length] - 0.5)));
                image.setRGB(i, j, new Color(nRed, nGreen, nBlue).getRGB());
            }
        }
    }


    private static double[][] getMap(int sizeMatrix) {
        double[][] m = new double[1][1];
        double[][] matrix = getMatrix(m, sizeMatrix);
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = matrix[i][j] / (matrix.length * matrix.length - 1);
            }
        }
        return matrix;
    }

    private static double[][] getMatrix(double[][] smallMatrix, int sizeMatrix) {
        if (smallMatrix.length >= sizeMatrix) {
            return smallMatrix;
        }
        double[][] matrixLager = generateMatrixLager(smallMatrix);
        return getMatrix(matrixLager, sizeMatrix);
    }

    private static double[][] generateMatrixLager(double[][] smallMatrix) {
        int length = smallMatrix.length;
        double[][] bigMatrix = new double[2 * length][2 * length];
        insertSubMatrix(smallMatrix, bigMatrix, 0, 0, 0);
        insertSubMatrix(smallMatrix, bigMatrix, 0, length, 3);
        insertSubMatrix(smallMatrix, bigMatrix, length, 0, 2);
        insertSubMatrix(smallMatrix, bigMatrix, length, length, 1);
        return bigMatrix;
    }

    private static void insertSubMatrix(double[][] smallMatrix, double[][] bigMatrix, int startX, int startY, int coeff) {
        int srcSize = smallMatrix.length;
        for (int j = 0; j < srcSize; j++) {
            for (int i = 0; i < srcSize; i++) {
                bigMatrix[i + startX][j + startY] = 4 * smallMatrix[i][j] + coeff;
            }
        }
    }

    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        dst.createGraphics().drawImage(src,0,0, null);
        toOrderedDithering(dst);
    }
}
