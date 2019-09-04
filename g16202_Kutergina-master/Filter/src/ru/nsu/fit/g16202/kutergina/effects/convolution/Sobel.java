package ru.nsu.fit.g16202.kutergina.effects.convolution;

public class Sobel extends Difference{

    public Sobel() {
        super(new float[]{-1, 0, +1,
                -2, 0, +2,
                -1, 0, +1},
                new float[]{-1, -2, -1,
                0, 0, 0,
                +1, +2, +1});
            }
}
