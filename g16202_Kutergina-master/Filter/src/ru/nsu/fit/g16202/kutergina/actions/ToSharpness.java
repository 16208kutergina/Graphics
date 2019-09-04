package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.convolution.Sharpness;

public class ToSharpness extends EffectsFunctional {
    public ToSharpness() {
        super(new Sharpness(),"Sharpness", "Sharpness", "image/sharpness.png");
        setEnabled(false);
    }
}
