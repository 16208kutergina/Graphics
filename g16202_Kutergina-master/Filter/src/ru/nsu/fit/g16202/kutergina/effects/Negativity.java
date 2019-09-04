package ru.nsu.fit.g16202.kutergina.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;
import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;

public class Negativity implements Filter{
    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        int height = min(zoneA.getBaseImage().getHeight(), sizeSideImage);
        int width = min(zoneA.getBaseImage().getWidth(), sizeSideImage);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Color color = new Color(src.getRGB(i, j));
                int red = 255 - color.getRed();
                int green = 255 - color.getGreen();
                int blue =255 - color.getBlue();
                Color newColor = new Color(red, green, blue);
                dst.setRGB(i, j, newColor.getRGB());
            }
        }
    }
}
