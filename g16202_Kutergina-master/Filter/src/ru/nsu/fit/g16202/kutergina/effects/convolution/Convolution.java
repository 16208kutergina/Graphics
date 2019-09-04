package ru.nsu.fit.g16202.kutergina.effects.convolution;

import ru.nsu.fit.g16202.kutergina.Constants;
import ru.nsu.fit.g16202.kutergina.Frame;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.min;

public class Convolution {
    public static void convolution(BufferedImage inputImage, BufferedImage outputImage, float[] matrix, int widthMatrix, int heightMatrix) {
        int height = min(Frame.zoneA.getBaseImage().getHeight(), Constants.sizeSideImage);
        int width = min(Frame.zoneA.getBaseImage().getWidth(), Constants.sizeSideImage);

        for(int h = 0 ; h < height; h++ ){
            for(int w = 0; w < width; w++){
                float sumRed = 0;
                float sumGreen = 0;
                float sumBlue = 0;

                for(int hK = - heightMatrix/2; hK < heightMatrix/2 + 1; hK++){
                    for(int wK = - widthMatrix/2; wK < widthMatrix/2 + 1; wK++) {
                        int x = w + wK;
                        int y = h + hK;
                        if(x >= 0 && y >= 0 && x < width && y < height){
                            float v = matrix[(wK + widthMatrix/2) + (hK + heightMatrix/2)*widthMatrix];
                            Color color = new Color(inputImage.getRGB(x, y));
                            int red = color.getRed();
                            int green = color.getGreen();
                            int blue = color.getBlue();
                            sumRed += v * red;
                            sumGreen+= v * green;
                            sumBlue += v* blue;
                        }
                    }
                }
                sumRed = normValue((int) sumRed);
                sumGreen = normValue((int) sumGreen);
                sumBlue = normValue((int) sumBlue);

                outputImage.setRGB(w, h, new Color((int)sumRed,(int)sumGreen,(int)sumBlue).getRGB());
            }

        }

    }


    public static int normValue(int sum) {
        if(sum > 255 ){
            sum = 255;
        }
        if(sum < 0){
            sum = 0;
        }
        return sum;
    }

}
