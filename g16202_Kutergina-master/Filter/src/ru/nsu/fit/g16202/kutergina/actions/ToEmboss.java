package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.convolution.Emboss;

public class ToEmboss extends EffectsFunctional {
    public ToEmboss() {
        super(new Emboss(), "Emboss", "Emboss", "image/emboss.png");
    setEnabled(false);
    }
}
