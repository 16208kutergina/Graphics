package ru.nsu.fit.g16202.kutergina.effects.gammaCorrection;

import ru.nsu.fit.g16202.kutergina.Constants;
import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;
import static java.lang.Math.pow;

public class GammaCorrection implements Filter {
    private float gamma;

    public void setGamma(float gamma){
        this.gamma = gamma;
    }
    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        int height = min(Frame.zoneA.getBaseImage().getHeight(), Constants.sizeSideImage);
        int width = min(Frame.zoneA.getBaseImage().getWidth(), Constants.sizeSideImage);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color color = new Color(src.getRGB(w, h));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                red = (int) (255 * pow(red/256., gamma));
                green = (int) (255 * pow(green/256., gamma));
                blue = (int) (255 * pow(blue/256., gamma));

                dst.setRGB(w, h, new Color(red, green, blue).getRGB());
            }
        }
    }
}
