package ru.nsu.fit.g16202.kutergina.effects.convolution;

public class Roberts extends Difference {
    public Roberts() {
        super(new float[]{0, 0, 0,
                        0, +1,0 ,
                        0, 0, -1},
                new float[]{0, 0, 0,
                        0, 0, 1,
                        0, -1,0 });
    }
}
