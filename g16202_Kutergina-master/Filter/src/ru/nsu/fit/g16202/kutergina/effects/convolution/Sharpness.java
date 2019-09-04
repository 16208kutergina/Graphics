package ru.nsu.fit.g16202.kutergina.effects.convolution;

import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.image.BufferedImage;

public class Sharpness implements Filter {
    float[] matrix = {0, -1, 0,
            -1, 5, -1,
            0, -1, 0};
    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        Convolution.convolution(src, dst, matrix, 3, 3);
    }
}
