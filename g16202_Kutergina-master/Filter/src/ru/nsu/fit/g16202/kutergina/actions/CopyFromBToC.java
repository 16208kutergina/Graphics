package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.zones.ImageZoneB;

import java.awt.event.ActionEvent;

public class CopyFromBToC extends JButtonFunctional {

    public CopyFromBToC(){
        super("Copy B to C","Copy B to C","image/BtoC.png");
        setEnabled(false);
    }
    @Override
    protected void go(ActionEvent e) {
        ((ImageZoneB) Frame.zoneB).copyToC();
    }


}
