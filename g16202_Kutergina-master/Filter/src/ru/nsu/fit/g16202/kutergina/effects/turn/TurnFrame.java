package ru.nsu.fit.g16202.kutergina.effects.turn;

import ru.nsu.fit.g16202.kutergina.effects.SlidersFrame;

import java.awt.image.BufferedImage;

public class TurnFrame extends SlidersFrame {
    @Override
    protected void doMethod(BufferedImage baseImage, BufferedImage baseImageC, int value) {
        Turn turn = new Turn();
        turn.setAngle(value);
        turn.apply(baseImage, baseImageC);
    }
}
