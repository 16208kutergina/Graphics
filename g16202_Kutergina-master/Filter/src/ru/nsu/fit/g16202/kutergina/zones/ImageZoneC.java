package ru.nsu.fit.g16202.kutergina.zones;

import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneB;


public class ImageZoneC extends ImageZone {

    public ImageZoneC(){
        super(sizeSideImage, sizeSideImage);
    }

    public void copyToB() {
        zoneB.setImage(baseImage);
    }

}
