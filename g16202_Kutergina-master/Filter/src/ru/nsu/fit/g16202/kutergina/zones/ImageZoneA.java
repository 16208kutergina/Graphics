package ru.nsu.fit.g16202.kutergina.zones;

import ru.nsu.fit.g16202.kutergina.ImageContainer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.Frame.select;
import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;
import static ru.nsu.fit.g16202.kutergina.ImageContainer.clearImage;

public class ImageZoneA extends ImageZone {
    private BufferedImage scaleImage;
    private BufferedImage selector = new BufferedImage((sizeSideImage), (sizeSideImage), BufferedImage.TYPE_INT_ARGB);;
    private double aspectRatio = 1;
    private int startRectX;
    private int startRectY;
    private boolean isPaintRect = false;

    public ImageZoneA() {
        super(sizeSideImage, sizeSideImage);
    }

    public void setPaintRect(boolean paintRect) {
        isPaintRect = paintRect;
    }

    public BufferedImage getBaseImage() {
        return baseImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isPaintRect){
            Graphics2D graphics = selector.createGraphics();
            clearImage(graphics, selector.getWidth(), selector.getHeight());
            graphics.drawImage(imageContainer.getImage(),0, 0 , imageContainer.getImage().getWidth(), imageContainer.getImage().getHeight(),null);
            paintSelector(graphics, (int) (sizeSideImage*((ImageZoneA)zoneA).getAspectRatio())-1);
            g.drawImage(selector, 0, 0, null);
        }else{
            g.drawImage(imageContainer.getImage(),0, 0 , null);
        }}

    public void setRect(int centerX, int centerY){
        int rectWidth = (int) (selector.getWidth()*aspectRatio);
        int rectHeight = (int) (selector.getHeight()*aspectRatio);
        startRectX = centerX - rectWidth/2;
        startRectY = centerY - rectHeight/2;
        if(startRectX < 0){
            startRectX = 0;
        }
        if(startRectX > scaleImage.getWidth() - rectWidth){
            startRectX = scaleImage.getWidth() - rectWidth;
        }
        if(startRectY < 0){
            startRectY = 0;
        }
        if(startRectY > scaleImage.getHeight() - rectHeight){
            startRectY = scaleImage.getHeight() - rectHeight;
        }
    }

    @Override
    public void setNewImage() {
        super.setNewImage();
    }

    @Override
    public void setImage(BufferedImage image){
        clearImage(baseImage.createGraphics(), baseImage.getWidth(), baseImage.getHeight());
        clearImage(selector.createGraphics(), selector.getWidth(), selector.getHeight());
        imageContainer = new ImageContainer(sizeSideImage+2, sizeSideImage+2);
        baseImage = image;
        isBaseImage =true;
        setSizScaleImageAndAspectRatio(image);
        Graphics2D g = scaleImage.createGraphics();
        g.drawImage(baseImage, 0, 0, scaleImage.getWidth(), scaleImage.getHeight(), null);
        imageContainer.setImage(scaleImage);
        select.setEnabled(true);
        repaint();
    }

    private void setSizScaleImageAndAspectRatio(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        if(width <= sizeSideImage && height <= sizeSideImage){
            scaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }else{
            if(width > height){
                aspectRatio = (double)sizeSideImage /width;
                scaleImage = new BufferedImage(sizeSideImage-1,(int) (aspectRatio * height)-1, BufferedImage.TYPE_INT_ARGB);
            }
            else{
                aspectRatio = (double)sizeSideImage /height;
                scaleImage = new BufferedImage((int) (aspectRatio * width)-1,sizeSideImage-1, BufferedImage.TYPE_INT_ARGB);
            }
        }
    }


    public double getAspectRatio() {
        return aspectRatio;
    }

    private void paintSelector(Graphics2D g2d, int sizeSide){
        g2d.setXORMode(Color.BLACK);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(startRectX, startRectY, sizeSide, sizeSide);
    }

}
