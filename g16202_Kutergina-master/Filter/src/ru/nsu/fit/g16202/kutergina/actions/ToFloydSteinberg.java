package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.dithering.frames.FloidSteinbergFrame;

import java.awt.event.ActionEvent;

public class ToFloydSteinberg extends JButtonFunctional{
    public ToFloydSteinberg(){
        super("Floyd-Steinberg", "Floyd-Steinberg", "image/floyd-steinberg.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        new FloidSteinbergFrame();
    }
}
