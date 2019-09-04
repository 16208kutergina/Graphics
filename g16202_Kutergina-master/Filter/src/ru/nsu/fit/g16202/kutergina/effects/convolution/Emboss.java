package ru.nsu.fit.g16202.kutergina.effects.convolution;

import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Emboss implements Filter {
    float[] matrix = {0, 1, 0,
            -1, 0, 1,
            0, -1, 0};
    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        Convolution.convolution(src, dst, matrix, 3, 3);
        for(int h = 0; h < dst.getWidth(); h++){
        for(int w = 0; w < dst.getHeight(); w++) {
            Color color = new Color(dst.getRGB(w, h));
            int red = Convolution.normValue(color.getRed() + 128);
            int green = Convolution.normValue(color.getGreen() + 128);
            int blue = Convolution.normValue(color.getBlue() + 128);

            dst.setRGB(w, h, new Color(red, green, blue).getRGB());
        }
        }
    }
}
