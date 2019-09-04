package ru.nsu.fit.g16202.kutergina.zones;

import ru.nsu.fit.g16202.kutergina.ImageContainer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageZone extends JPanel {
    ImageContainer imageContainer;
    BufferedImage baseImage;
    boolean isBaseImage = false;

    public BufferedImage getBaseImage() {
        return baseImage;
    }

    public ImageZone(int width, int height){
        setPreferredSize(new Dimension(width + 2, height + 2));
        setMaximumSize(new Dimension(width + 2, height + 2));
        imageContainer = new ImageContainer(width + 2, height + 2);
        imageContainer.setNewImage();
        baseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageContainer.getImage(), 0, 0, null);
    }

    public void setImage(BufferedImage image){
        baseImage.createGraphics().drawImage(image,0,0,image.getWidth(), image.getHeight(), null);
        imageContainer.setImage(baseImage);
        repaint();
    }
    public void setNewImage() {
        imageContainer.setNewImage();
    }
}
