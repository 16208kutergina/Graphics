package ru.nsu.fit.g16202.kutergina.effects.convolution.frames;

import ru.nsu.fit.g16202.kutergina.effects.SlidersFrame;
import ru.nsu.fit.g16202.kutergina.effects.convolution.Sobel;

import java.awt.image.BufferedImage;


public class SobelFrame extends SlidersFrame {

    @Override
    protected void doMethod(BufferedImage baseImage, BufferedImage baseImageC, int value) {
new Sobel().toDiff(baseImage, baseImageC,value);
    }
}
