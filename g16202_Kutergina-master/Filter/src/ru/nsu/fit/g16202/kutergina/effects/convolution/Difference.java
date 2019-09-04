package ru.nsu.fit.g16202.kutergina.effects.convolution;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.sqrt;

public abstract class Difference {
    protected float[] Gx;
    protected float[] Gy;

    public Difference(float[] gx, float[] gy) {
        Gx = gx;
        Gy = gy;
    }

    public void toDiff(BufferedImage image, BufferedImage dst, int limit) {

        BufferedImage convGx = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Convolution.convolution(image, convGx, Gx, 3, 3);
        BufferedImage convGy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Convolution.convolution(image, convGy, Gy, 3, 3);

        for (int h = 0; h < image.getHeight(); h++) {
            for (int w = 0; w < image.getWidth(); w++) {
                Color colorX = new Color(convGx.getRGB(w, h));
                int redX = colorX.getRed();
                int greenX = colorX.getGreen();
                int blueX = colorX.getBlue();

                Color colorY = new Color(convGy.getRGB(w, h));
                int redY = colorY.getRed();
                int greenY = colorY.getGreen();
                int blueY = colorY.getBlue();

                int sqrtRed = (int)( sqrt(redX * redX + redY * redY)/ sqrt(2.) );
                int sqrtGreen = (int) (sqrt(greenX * greenX + greenY * greenY)/ sqrt(2.));
                int sqrtBlue = (int) (sqrt(blueX * blueX + blueY * blueY)/ sqrt(2.));

                int greyScale = (sqrtBlue+sqrtGreen+sqrtRed)/3;
                if(greyScale >= limit){
                    dst.setRGB(w, h, Color.BLACK.getRGB());
                }else {
                    dst.setRGB(w, h, Color.white.getRGB());
                }
            }
        }
    }
}
