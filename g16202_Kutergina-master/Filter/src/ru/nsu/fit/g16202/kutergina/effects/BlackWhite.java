package ru.nsu.fit.g16202.kutergina.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;
import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;

public class BlackWhite implements Filter {


    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        int height = min(zoneA.getBaseImage().getHeight(), sizeSideImage);
        int width = min(zoneA.getBaseImage().getWidth(), sizeSideImage);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Color color = new Color(src.getRGB(i, j));
                int red = (int) (color.getRed()*0.299);
                int green = (int) (color.getGreen()*0.587);
                int blue = (int) (color.getBlue()*0.114);
                int grey = (red + green + blue);
                Color newColor = new Color(grey, grey, grey);
                dst.setRGB(i, j, newColor.getRGB());
            }
        }
    }
}
