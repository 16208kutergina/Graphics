package ru.nsu.fit.g16202.kutergina.effects.dithering.frames;

import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.effects.dithering.filter.FloydSteinberg;

import java.awt.image.BufferedImage;


public class FloidSteinbergFrame extends DitheringFrame {
    FloydSteinberg filter = new FloydSteinberg();
    @Override
    protected void doMethod(BufferedImage src, BufferedImage dst, int red, int green, int blue) {
        filter.setBlue(blue);
        filter.setGreen(green);
        filter.setRed(red);
        filter.apply(src, dst);
        Frame.copyFromCToB.setEnabled(true);
        Frame.zoneC.setImage(Frame.zoneC.getBaseImage());
    }
}
