package ru.nsu.fit.g16202.kutergina.effects.turn;

import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;
import static java.lang.Math.*;
import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;
import static ru.nsu.fit.g16202.kutergina.ImageContainer.clearImage;

public class Turn implements Filter {
    private int angle;

    public void setAngle(int angle){
        this.angle = angle;
    }

    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        double rad = (float)angle * PI/180.;
        int height = min(zoneA.getBaseImage().getHeight(), sizeSideImage);
        int width = min(zoneA.getBaseImage().getWidth(), sizeSideImage);
        clearImage(dst.createGraphics(), dst.getWidth(), dst.getHeight());
        for (int h = 0; h < sizeSideImage; h++) {
            for (int w = 0; w < sizeSideImage; w++) {
                int x = (int) (cos(rad)*(w - width/2.) + sin(rad)*(h - height/2.)+ (width/2.));
                int y = (int) (-sin(rad)*(w - width/2.) + cos(rad)*(h - height/2.) + (height/2.));
               if(x >= sizeSideImage || y >= sizeSideImage || x < 0 || y < 0){
                  dst.setRGB(w,h, Color.white.getRGB());
               }else {
                   int rgb = src.getRGB(x, y);
                   dst.setRGB(w, h, rgb);
               }
            }
        }
    }
}
