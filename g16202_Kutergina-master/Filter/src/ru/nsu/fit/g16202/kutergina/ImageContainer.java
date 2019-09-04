package ru.nsu.fit.g16202.kutergina;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageContainer {
    private BufferedImage image;
    private int width;
    private int height;

    public BufferedImage getImage() {
        return image;
    }

    public ImageContainer(int width, int height){
        this.width = width - 2;
        this.height = height - 2;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        paintBorder(image, width, height);
    }

    public void setImage(BufferedImage scaleImage){
        Graphics2D graphics = image.createGraphics();
       setNewImage();
        graphics.drawImage(scaleImage, 1, 1, scaleImage.getWidth()-1, scaleImage.getHeight()-1, null);
    }

    public void setNewImage() {
        clearImage(image.createGraphics(), width, height);
        paintBorder(image, width, height);
    }

    public static void paintBorder(BufferedImage image, int width, int height){
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        float[] dash1 = { 2f, 0f, 2f };
        g.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f));
        g.drawRect(0,0, width,height);
    }
    public static void clearImage(Graphics2D g, int width, int height){
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, width, height);
        g.setComposite(AlphaComposite.DstOver);
    }
}
