package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.convolution.frames.SobelFrame;

import java.awt.event.ActionEvent;

public class ToSobel extends JButtonFunctional {
    public ToSobel(){
        super("Sobel", "Sobel", "image/sobel.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        new SobelFrame();
    }
}
