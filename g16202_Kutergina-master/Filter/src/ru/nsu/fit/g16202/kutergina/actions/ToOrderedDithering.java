package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.dithering.frames.OrderedDitheringFrame;

import java.awt.event.ActionEvent;

public class ToOrderedDithering extends JButtonFunctional {
    public ToOrderedDithering(){
        super("Ordered dithering", "Ordered dithering", "image/orderedDithering.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        new OrderedDitheringFrame();
    }
}
