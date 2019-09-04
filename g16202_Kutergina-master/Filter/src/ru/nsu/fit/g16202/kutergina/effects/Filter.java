package ru.nsu.fit.g16202.kutergina.effects;

import java.awt.image.BufferedImage;

public interface Filter {
    void apply(BufferedImage src, BufferedImage dst);
}
