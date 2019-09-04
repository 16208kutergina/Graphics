package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.convolution.frames.RobertsFrame;

import java.awt.event.ActionEvent;

public class ToRoberts extends JButtonFunctional {
    public ToRoberts(){
        super("Roberts", "Roberts", "image/roberts.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        new RobertsFrame();
    }
}
