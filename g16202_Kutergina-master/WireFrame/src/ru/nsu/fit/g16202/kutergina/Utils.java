package ru.nsu.fit.g16202.kutergina;

import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utils {
    public static double[] multMatrix(double[] left, int rowLeft, int colLeft,
                                      double[] right, int rowRight, int colRight) {
        if (colLeft != rowRight) {
            throw new RuntimeException("IncorrectSizeMatrix");
        }
        double[] result = new double[rowLeft * colRight];
        for (int i = 0; i < rowLeft; i++) {
            for (int j = 0; j < colRight; j++) {
                for (int z = 0; z < colLeft; z++) {
                    result[i * colRight + j] += left[i * colLeft + z] * right[z * colRight + j];
                }
            }
        }
        return result;
    }

    public static double findLength(DoublePoint pointLast, DoublePoint pointPrev) {
        return sqrt(pow(pointLast.getX() - pointPrev.getX(), 2) + pow(pointLast.getY() - pointPrev.getY(), 2));
    }

    public static void clearImage(Graphics2D g, int width, int height) {
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, width, height);
        g.setComposite(AlphaComposite.DstOver);
    }

}
