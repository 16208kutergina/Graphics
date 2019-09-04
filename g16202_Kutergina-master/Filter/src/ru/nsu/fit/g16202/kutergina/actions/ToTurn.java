package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.effects.turn.TurnFrame;

import java.awt.event.ActionEvent;

public class ToTurn extends JButtonFunctional {
    public ToTurn() {
        super("Turn", "Turn", "image/turn.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        new TurnFrame();
    }
}
