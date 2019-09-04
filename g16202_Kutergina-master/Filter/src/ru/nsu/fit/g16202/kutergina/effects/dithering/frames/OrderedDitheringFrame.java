package ru.nsu.fit.g16202.kutergina.effects.dithering.frames;

import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.effects.dithering.filter.OrderedDithering;

import java.awt.image.BufferedImage;

public class OrderedDitheringFrame extends DitheringFrame {
//    @Override
//    protected void doMethod(BufferedImage baseImageC, int red, int green, int blue) {
//        toOrderedDithering(baseImageC, red, green, blue);
//    }

    @Override
    protected void doMethod(BufferedImage src, BufferedImage dst, int red, int green, int blue) {
        OrderedDithering filter = new OrderedDithering(red,green, blue);
        filter.apply(src, dst);
        Frame.zoneC.setImage(dst);
    }
}
