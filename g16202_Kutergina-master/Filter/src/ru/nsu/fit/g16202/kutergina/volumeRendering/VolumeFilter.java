package ru.nsu.fit.g16202.kutergina.volumeRendering;

import ru.nsu.fit.g16202.kutergina.effects.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static java.lang.Math.exp;
import static java.lang.Math.round;

public class VolumeFilter implements Filter {
    private int xVok;
    private int yVok;
    private int zVok;
    private java.util.List<Charge> charges;
    private List<EmissionPoint> absorption;
    private List<Emission> emissions;
    private float sizeX;
    private float sizeY;
    private float sizeZ;
    private float min = Float.MAX_VALUE;
    private float max = Float.MIN_NORMAL;
    private float[] graphAbs = new float[101];
    private int[] graphEmissRed = new int[101];
    private int[] graphEmissGreen = new int[101];
    private int[] graphEmissBlue = new int[101];
    private float[][][] allCharges;




    public void setMesh(int xVok, int yVok, int zVok) {
        this.xVok = xVok;
        this.yVok = yVok;
        this.zVok = zVok;
        allCharges = new float[xVok][yVok][zVok];
        sizeX = 1f/xVok;
        sizeY = 1f/yVok;
        sizeZ = 1f/zVok;
    }

    public void setParameters(List<Charge> charges,
                              List<EmissionPoint> absorption,
                              List<Emission> emissions){
        this.charges = charges;
        this.absorption = absorption;
        this.emissions = emissions;
        buildGraphAbsorption();
        buildGraphEmissionColor();
        fillMatrixCharges();
    }

    private void buildGraphAbsorption(){
        for(int i = 0; i < 100; i++){
            for(int a = 0; a < absorption.size() - 1; a++){
                EmissionPoint start = absorption.get(a);
                EmissionPoint end = absorption.get(a + 1);
                if( i > start.x && i <= end.x){
                    graphAbs[i] = (i - start.x)*(end.y - start.y)/(end.x - start.x) + start.y;
                }
            }
        }
    }

    private void buildGraphEmissionColor(){
        for(int i = 0; i < 100; i++){
            for(int a = 0; a < emissions.size() - 1; a++){
                Emission start = emissions.get(a);
                Emission end = emissions.get(a + 1);
                if( i > start.point && i <= end.point){
                    graphEmissRed[i] = (int) ((int) (i - start.point)*(end.red - start.red)/(end.point - start.point) + start.red);
                    graphEmissGreen[i] = (int) ((int) (i - start.point)*(end.green - start.green)/(end.point - start.point) + start.green);
                    graphEmissBlue[i] = (int) ((int) (i - start.point)*(end.blue - start.blue)/(end.point - start.point) + start.blue);
                }
            }
        }
    }

    private float normValue(float valueCharge){
        return (valueCharge - min)/(max - min) * 100;
    }

    private void fillMatrixCharges(){
        for(int x = 0; x < xVok; x++) {
            for(int y = 0; y < yVok; y++) {
                for(int z = 0; z < zVok; z++) {
                    float allChargeOnVok = getAllChargeOnVok(
                            x * sizeX,
                            y * sizeY,
                            z * sizeZ);
                    allCharges[x][y][z] = allChargeOnVok;
                    if(x == 0 && y == 0 && z == 0){
                    }
                    if(allCharges[x][y][z] < min){
                        min = allCharges[x][y][z];
                    }
                    if(allCharges[x][y][z] > max){
                        max = allCharges[x][y][z];
                    }
                }
            }
        }
        for(int x = 0; x < xVok; x++) {
            for(int y = 0; y < yVok; y++) {
                for(int z = 0; z < zVok; z++) {
                    allCharges[x][y][z] = normValue(allCharges[x][y][z]);
                    if(x == 0 && y == 0 && z == 0){
                    }
                }
            }
        }
    }

    private float getAllChargeOnVok(float x, float y, float z){

        return charges.stream()
                .map(it->it.getInfluence(x,y,z))
                .reduce((left, right) -> left+right)
                .orElse(0.f);
    }

    private Color transformPix(int x, int y, Color color){
        float red = color.getRed();
        float green =color.getGreen();
        float blue = color.getBlue();
        for(int z = 0; z < zVok; z++){

            float exp = (float) exp(-graphAbs[(int) allCharges[x][y][z]]*sizeZ);
            red = red * exp + graphEmissRed[(int) allCharges[x][y][z]]*sizeZ;
            green = green * exp + graphEmissGreen[(int) allCharges[x][y][z]]*sizeZ;
            blue = blue * exp + graphEmissBlue[(int) allCharges[x][y][z]]*sizeZ;
        }
        red = checkColor(round(red));
        green = checkColor(round(green));
        blue = checkColor(round(blue));
        return new Color((int)red, (int)green, (int)blue);
    }

    private int checkColor(int color){
        if(color < 0){
            return 0;
        }
        if(color > 255){
            return 255;
        }
        return color;
    }



    @Override
    public void apply(BufferedImage src, BufferedImage dst) {
        int width = src.getWidth();
        int height = src.getHeight();
        int xNorm = width / xVok;
        int yNorm = height / yVok;
        int countMoreX = 0;
        int countLessX = 0;
        int countMoreY = 0;
        int countLessY = 0;
        if(width%xVok == 0){
            countLessX = xVok;
        }else{
            countMoreX = ((xNorm +1)*xVok - width);
            countLessX = xVok - countMoreX;
        }
        if(height%yVok == 0){
            countLessY = yVok;
        }else{
            countMoreY = ((yNorm +1)*yVok - width);
            countLessY = yVok - countMoreY;
        }
        int xS = countLessX * (xNorm);
        int yS = countLessY * (yNorm);
        for(int x = 0; x < countLessX*(xNorm); x++){
            for(int y = 0; y < countLessY*(yNorm); y++){
                dst.setRGB(x, y, transformPix(x/xNorm, y/yNorm, new Color(src.getRGB(x, y))).getRGB());
            }
        }

        for(int x = 0; x < countLessX*(xNorm); x++){
            for(int y = yS; y <height; y++){
                dst.setRGB(x, y, transformPix(x/xNorm, countLessY -1 + (y - yS)/(yNorm+1), new Color(src.getRGB(x, y))).getRGB());
            }
            }
        for(int x = xS; x <width; x++){
            for(int y = 0; y < countLessY*(yNorm); y++){
                dst.setRGB(x, y, transformPix(countLessX - 1 + (x-xS)/(xNorm+1), y/yNorm, new Color(src.getRGB(x, y))).getRGB());
            }
            }
        for(int x = xS; x <width; x++){
            for(int y = yS; y <height; y++){
                dst.setRGB(x, y, transformPix(countLessX - 1+ (x-xS)/(xNorm+1), countLessY - 1 + (y - yS)/(yNorm+1), new Color(src.getRGB(x, y))).getRGB());
            }
        }

    }
}
