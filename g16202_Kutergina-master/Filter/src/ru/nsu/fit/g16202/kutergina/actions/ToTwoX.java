package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.TwoX;

public class ToTwoX extends EffectsFunctional {
    public ToTwoX(){
        super(new TwoX(),"Zoom", "Zoom", "image/zoom.png");
        setEnabled(false);
    }

}
