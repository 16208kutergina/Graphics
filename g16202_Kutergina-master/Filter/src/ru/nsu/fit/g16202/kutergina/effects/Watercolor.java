package ru.nsu.fit.g16202.kutergina.effects;

import ru.nsu.fit.g16202.kutergina.effects.convolution.Sharpness;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Integer.min;
import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;

public class Watercolor implements Filter {
    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        BufferedImage dsc = new BufferedImage(dst.getWidth(), dst.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int height = min(zoneA.getBaseImage().getHeight(), sizeSideImage);
        int width = min(zoneA.getBaseImage().getWidth(), sizeSideImage);
        ArrayList<Integer> arrayRed = new ArrayList<>();
        ArrayList<Integer> arrayGreen = new ArrayList<>();
        ArrayList<Integer> arrayBlue = new ArrayList<>();

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {

                arrayRed.clear();
                arrayGreen.clear();
                arrayBlue.clear();

                for (int hK = -2; hK <= 2; hK++) {
                    for (int wK = -2; wK <= 2; wK++) {
                        int x = w + wK;
                        int y = h + hK;
                        if (x < 0 || x >= width || y < 0 || y >= height) {
                            continue;
                        }
                        Color color = new Color(src.getRGB(x, y));
                        arrayRed.add(color.getRed());
                        arrayGreen.add(color.getGreen());
                        arrayBlue.add(color.getBlue());
                    }
                }
                arrayRed.sort(null);
                arrayGreen.sort(null);
                arrayBlue.sort(null);
                dsc.setRGB(w, h, new Color(arrayRed.get(arrayRed.size()/2), arrayGreen.get(arrayGreen.size()/2), arrayBlue.get(arrayBlue.size()/2)).getRGB());
            }
        }
        new Sharpness().apply(dsc, dst);
    }
}
