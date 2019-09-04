package ru.nsu.fit.g16202.kutergina.effects.convolution.frames;

import ru.nsu.fit.g16202.kutergina.effects.SlidersFrame;
import ru.nsu.fit.g16202.kutergina.effects.convolution.Roberts;

import java.awt.image.BufferedImage;

public class RobertsFrame extends SlidersFrame {
    @Override
    protected void doMethod(BufferedImage baseImage, BufferedImage baseImageC, int value) {
        new Roberts().toDiff(baseImage, baseImageC,value);
    }
}
