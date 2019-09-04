package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.gammaCorrection.GammaFrame;

import java.awt.event.ActionEvent;

public class ToGammaCorrection extends JButtonFunctional {
    public ToGammaCorrection() {
        super("Gamma correction", "Gamma correction", "image/gamma.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        new GammaFrame();
    }
}
