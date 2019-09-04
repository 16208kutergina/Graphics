package ru.nsu.fit.g16202.kutergina.effects.dithering.filter;

import ru.nsu.fit.g16202.kutergina.Constants;
import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;

public class FloydSteinberg implements Filter {
    int red;
    int green;
    int blue;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }


    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        dst.createGraphics().drawImage(src,0,0, dst.getWidth(), dst.getHeight(), null);
        toFloydSteinberg(dst, dst);
    }

    public void toFloydSteinberg(BufferedImage src, BufferedImage dst) {
        int height = min(Frame.zoneA.getBaseImage().getHeight(), Constants.sizeSideImage);
        int width = min(Frame.zoneA.getBaseImage().getWidth(), Constants.sizeSideImage);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Color color = new Color(src.getRGB(i, j));
                int redStart = color.getRed();
                int greenStart = color.getGreen();
                int blueStart = color.getBlue();

                int closingColorRed = getClosingColor(red, redStart);
                int errRed = redStart - closingColorRed;

                int closingColorGreen = getClosingColor(green, greenStart);
                int errGreen = greenStart - closingColorGreen;

                int closingColorBlue = getClosingColor(blue, blueStart);
                int errBlue = blueStart - closingColorBlue;

                    src.setRGB(i, j, new Color(closingColorRed, closingColorGreen, closingColorBlue).getRGB());
                if(i < src.getWidth() - 1) {
                    addError(src, dst, j, errRed, errGreen, errBlue, i + 1, 7);
                }
                if(i - 1 >= 0 && j + 1 < src.getHeight() - 1) {
                    addError(src, dst,j + 1, errRed, errGreen, errBlue, i - 1, 1);
                }
                if(j + 1 < src.getHeight() - 1) {
                    addError(src, dst,j + 1, errRed, errGreen, errBlue, i, 5);
                }
                if(i < src.getWidth() - 1 && j + 1 < src.getHeight() - 1) {
                    addError(src, dst, j + 1, errRed, errGreen, errBlue, i +1, 3);
                }

            }
        }
    }

    private static void addError(BufferedImage image, BufferedImage dst,int j, int errRed, int errGreen, int errBlue, int i2, int i3) {
        Color color1 = new Color(image.getRGB(i2, j));
        int newRed = color1.getRed() + i3 * errRed / 16;
        int newGreen = color1.getGreen() + i3 * errGreen / 16;
        int newBlue = color1.getBlue() + i3 * errBlue / 16;
        newRed = checkValueColor(newRed);
        newGreen = checkValueColor(newGreen);
        newBlue = checkValueColor(newBlue);

        dst.setRGB(i2, j, new Color(newRed, newGreen, newBlue).getRGB());
        image.setRGB(i2, j, new Color(newRed, newGreen, newBlue).getRGB());
    }

    private static int checkValueColor(int newRed) {
        if(newRed > 255) newRed = 255;
        if(newRed < 0) newRed = 0;
        return newRed;
    }


    public static int getClosingColor(int newCountColor, int currentColor){
        double step = 255. /(newCountColor-1.);
        int close = (int) ((int)(currentColor / step + 0.5) * step);
        if(close > 255){
            close = 255;
        }
        if(close < 0){
            close = 0;
        }
        return close;
    }
}
