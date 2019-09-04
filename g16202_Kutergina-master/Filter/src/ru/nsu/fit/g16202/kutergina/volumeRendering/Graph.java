package ru.nsu.fit.g16202.kutergina.volumeRendering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Graph {
    public BufferedImage getAbsorption() {
        return absorption;
    }

    public BufferedImage getEmission() {
        return emission;
    }

    private BufferedImage absorption;
    private BufferedImage emission;

    public Graph(ArrayList<EmissionPoint> absorptionsPoints, ArrayList<Emission> emissionPoints){
        absorption = new BufferedImage(Const.width, Const.height, BufferedImage.TYPE_INT_ARGB);
        emission = new BufferedImage(Const.width, Const.height, BufferedImage.TYPE_INT_ARGB);
        paintAbsorption(absorptionsPoints);
        paintEmission(emissionPoints);
    }

    private void paintEmission(ArrayList<Emission> emissionPoints) {
        Graphics2D graphics = emission.createGraphics();
        for (int i = 0; i < emissionPoints.size()-1; i++){
            Emission start = emissionPoints.get(i);
            Emission end = emissionPoints.get(i+1);
            graphics.setColor(Color.red);
            graphics.drawLine((int)(start.point * Const.scaleX), (int)(start.red* Const.scaleEmY),(int)(end.point*Const.scaleX),(int)( end.red* Const.scaleEmY) );
            graphics.setColor(Color.green);
            graphics.drawLine((int)(start.point*Const.scaleX), (int)(start.green* Const.scaleEmY),(int)(end.point*Const.scaleX), (int)(end.green* Const.scaleEmY ));
            graphics.setColor(Color.blue);
            graphics.drawLine((int)(start.point*Const.scaleX), (int)(start.blue* Const.scaleEmY),(int)(end.point*Const.scaleX),(int) (end.blue* Const.scaleEmY ));
        }
    }

    private void paintAbsorption(ArrayList<EmissionPoint> absorptionsPoints) {
        Graphics2D graphics = absorption.createGraphics();
        graphics.setColor(Color.BLACK);
        for(int i = 0; i < absorptionsPoints.size() - 1 ; i++){
            EmissionPoint start = absorptionsPoints.get(i);
            EmissionPoint end = absorptionsPoints.get(i+1);
            graphics.drawLine((int)(start.x*Const.scaleX), (int)(start.y* Const.scaleAbsY),(int)(end.x*Const.scaleX), (int)(end.y*Const.scaleAbsY));
        }
    }


}
