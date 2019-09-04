package ru.nsu.fit.g16202.kutergina.effects.convolution;

import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.image.BufferedImage;

public class Blur implements Filter {
static float[] matrix = {1f/9f, 1f/9f, 1f/9f,
        1f/9f, 1f/9f, 1f/9f,
        1f/9f, 1f/9f, 1f/9f};

    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        Convolution.convolution(src, dst, matrix, 3, 3);
    }
}
