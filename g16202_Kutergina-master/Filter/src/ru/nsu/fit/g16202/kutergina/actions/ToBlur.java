package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.convolution.Blur;

public class ToBlur extends EffectsFunctional {

    public ToBlur() {
        super( new Blur(), "Blur", "Blur", "image/blur.png");
        setEnabled(false);
    }
}
