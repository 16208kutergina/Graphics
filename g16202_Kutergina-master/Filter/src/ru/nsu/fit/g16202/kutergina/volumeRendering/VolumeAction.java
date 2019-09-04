package ru.nsu.fit.g16202.kutergina.volumeRendering;

import ru.nsu.fit.g16202.kutergina.actions.JButtonFunctional;

import java.awt.event.ActionEvent;

public class VolumeAction extends JButtonFunctional {
    private ParseFileGraph pg;

    public VolumeAction() {
        super("Play", "Play", "image/play.png");
    setEnabled(false);
    }


    @Override
    protected void go(ActionEvent e) {
new VolumeFrame(pg);
    }

    public void setData(ParseFileGraph pg) {
        this.pg = pg;
    }
}
