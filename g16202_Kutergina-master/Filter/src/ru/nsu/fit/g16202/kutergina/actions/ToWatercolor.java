package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.Watercolor;

public class ToWatercolor extends EffectsFunctional {
    public ToWatercolor() {
        super(new Watercolor(), "Watercolor", "Watercolor", "image/watercolor.png");
        setEnabled(false);
    }
}
