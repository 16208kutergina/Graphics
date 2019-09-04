package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.Negativity;

public class ToNegativity extends EffectsFunctional {
    public ToNegativity(){
        super(new Negativity(),"Negativity", "Negativity", "image/negativity.png");
        setEnabled(false);
    }

//    @Override
//    protected void go(ActionEvent e) {
//        BufferedImage baseImageC = takeImageFromB();
//        toNegativity(baseImageC);
//        zoneC.setImage(baseImageC);
//    }
}
