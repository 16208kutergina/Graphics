package ru.nsu.fit.g16202.kutergina.volumeRendering;


import static java.lang.Math.sqrt;

public class Charge {
    float X;
    float Y;
    float Z;
    float power;


    public Charge(float x, float y, float z, float power) {
        X = x;
        Y = y;
        Z = z;
        this.power = power;
    }

    float getInfluence(Charge other){
        return getInfluence(other.X,other.Y,other.Z);
    }

    float getInfluence(float x, float y, float z){
        float distance = (float) sqrt((x - X)*(x - X) + (y - Y)*(y - Y) + (z - Z)*(z - Z));
        if(distance < 0.1){
            distance = 0.1f;
        }
        return power/distance;
    }
}
