package ru.nsu.fit.g16202.kutergina.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;

public class TwoX implements Filter {

    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        BufferedImage baseImage = new BufferedImage(sizeSideImage/2, sizeSideImage/2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = baseImage.createGraphics();
        int oldWidth = baseImage.getWidth();
        int oldHeight = baseImage.getHeight();
        graphics.drawImage(src.getSubimage(src.getWidth()/4, src.getHeight()/4, oldWidth, oldHeight),0,0,null);
        double coefWidth = 0.5;
        double coefHeight = 0.5;
        for(int j = 0; j < dst.getHeight(); j++){
            for(int i = 0; i < dst.getWidth(); i++) {
                int w = (int) (coefWidth * (double)i);
                int h = (int) (coefHeight * (double)j);
                dst.setRGB(i, j,baseImage.getRGB(w, h));
            }
        }
    }
}
