package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static ru.nsu.fit.g16202.kutergina.Constants.sizSpace;
import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;
import static ru.nsu.fit.g16202.kutergina.ImageContainer.clearImage;

public class Panel extends JPanel {


    public Panel(){
        setPreferredSize(new Dimension(3*sizeSideImage + 5 * sizSpace, sizeSideImage + 300));
        Border empty = BorderFactory.createEmptyBorder(10,10,10,10);
        setBorder(empty);
        JPanel panel1 = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 3, 10, 0);
        panel1.setLayout(gridLayout);

        panel1.add(Frame.zoneA);
        panel1.add(Frame.zoneB);
        panel1.add(Frame.zoneC);

        JPanel panel = new JPanel();
        panel.add(Frame.absorZone);
        panel.add(Frame.emissZone);

        add(panel1, BorderLayout.PAGE_START);
        add(panel, BorderLayout.PAGE_END);


        Frame.zoneA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                moveRect(e);
                Frame.zoneA.setPaintRect(false);
                Frame.zoneA.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                Frame.zoneA.setPaintRect(false);
                Frame.zoneA.repaint();
            }
        });
        Frame.zoneA.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moveRect(e);
            }
        });


    }

    private void moveRect(MouseEvent e) {
        if (Frame.select.getButton().isSelected()) {
            Frame.zoneA.setPaintRect(true);
            Frame.zoneA.setRect(e.getX(), e.getY());
            setSubImageAToZoneB(e);
            Frame.zoneA.repaint();
        }
    }

    private void setSubImageAToZoneB(MouseEvent e) {
        BufferedImage baseImage = Frame.zoneA.getBaseImage();
        BufferedImage imageA = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        imageA.createGraphics().drawImage(baseImage, 0, 0, imageA.getWidth(), imageA.getHeight(), null);
        int width = sizeSideImage;
        int height = sizeSideImage;
        if(imageA.getWidth()< sizeSideImage){
            width = imageA.getWidth();
        }
        if(imageA.getHeight() < sizeSideImage){
            height = imageA.getHeight();
        }
        int leftBorder = (int) ((e.getX() / Frame.zoneA.getAspectRatio()) - width / 2);
        int topBorder = (int) ((e.getY() / Frame.zoneA.getAspectRatio()) - height / 2);
        int rightBorder = (int) (e.getX() / Frame.zoneA.getAspectRatio() + width / 2);
        int bottomBorder = (int) (e.getY() / Frame.zoneA.getAspectRatio() + height / 2);
        if(leftBorder < 0){
            leftBorder = 0;
            rightBorder = width;
        }
        if(rightBorder > imageA.getWidth()){
            leftBorder = imageA.getWidth() - width;
        }
        if(topBorder < 0){
            topBorder = 0;
            bottomBorder = height;
        }
        if(bottomBorder > imageA.getHeight()){
            topBorder = imageA.getHeight() - height;
        }
        try {
            BufferedImage subImage = imageA.getSubimage(leftBorder, topBorder, width, height);
            clearImage(Frame.zoneB.getBaseImage().createGraphics(), Frame.zoneB.getBaseImage().getWidth(), Frame.zoneB.getBaseImage().getHeight());
            Frame.zoneB.setImage(subImage);
        }catch (Throwable  th){
            th.getMessage();
        }
        setAllEnabled(true);

        Frame.zoneB.repaint();
    }

    public static void setAllEnabled(boolean state){
        Frame.toBlackWhite.setEnabled(state);
        Frame.toNegativity.setEnabled(state);
        Frame.toFloydSteinberg.setEnabled(state);
        Frame.toOrderedDithering.setEnabled(state);
        Frame.toTwoX.setEnabled(state);
        Frame.copyFromBToC.setEnabled(state);
        Frame.toSobel.setEnabled(state);
        Frame.toBlur.setEnabled(state);
        Frame.toSharpness.setEnabled(state);
        Frame.toWatercolor.setEnabled(state);
        Frame.toEmboss.setEnabled(state);
        Frame.toRoberts.setEnabled(state);
        Frame.toGammaCorrection.setEnabled(state);
        Frame.toTurn.setEnabled(state);
        Frame.save.setEnabled(state);
        Frame.volumeOpen.setEnabled(state);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }



}
