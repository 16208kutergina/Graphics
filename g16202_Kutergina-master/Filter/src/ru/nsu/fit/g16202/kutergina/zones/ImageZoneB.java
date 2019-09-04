package ru.nsu.fit.g16202.kutergina.zones;

import ru.nsu.fit.g16202.kutergina.ImageContainer;

import java.awt.image.BufferedImage;

import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneC;

public class ImageZoneB extends ImageZone {

    public ImageZoneB(){
        super(sizeSideImage, sizeSideImage);
    }

    public void copyToC() {
        BufferedImage baseImage = zoneC.getBaseImage();
        ImageContainer.clearImage(baseImage.createGraphics(), baseImage.getWidth(), baseImage.getHeight());

        zoneC.setImage(this.baseImage);
    }


}
