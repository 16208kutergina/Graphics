package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.ImageContainer;
import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import static ru.nsu.fit.g16202.kutergina.Frame.*;

public class EffectsFunctional extends JButtonFunctional {
    private Filter filter;

    public EffectsFunctional(Filter filter, String text, String tooltip, String filePath) {
        super(text, tooltip, filePath);
        this.filter = filter;
    }

    @Override
    final protected void go(ActionEvent e) {
        BufferedImage image = zoneB.getBaseImage();
        BufferedImage baseImage = zoneC.getBaseImage();
        ImageContainer.clearImage(baseImage.createGraphics(), baseImage.getWidth(), baseImage.getHeight());
        filter.apply(image, baseImage);
        copyFromCToB.setEnabled(true);
        save.setEnabled(true);
        zoneC.setImage(baseImage);
    }
}
