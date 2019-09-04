package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.BlackWhite;

public class ToBlackWhite extends EffectsFunctional {

    public ToBlackWhite(){
        super(new BlackWhite(), "Black-white", "Black-white", "image/blackWhite.png");
        setEnabled(false);
    }

}
