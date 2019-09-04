package ru.nsu.fit.g16202.kutergina.volumeRendering;

public class Emission {
    public Emission(float point, int red, int green, int blue) {
        this.point = point;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    float point;
    int red;
    int green;
    int blue;
}
