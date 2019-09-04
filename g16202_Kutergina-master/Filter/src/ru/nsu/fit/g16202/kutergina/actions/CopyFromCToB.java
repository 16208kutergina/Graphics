package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.zones.ImageZoneC;

import java.awt.event.ActionEvent;

import static ru.nsu.fit.g16202.kutergina.Frame.zoneC;

public class CopyFromCToB extends JButtonFunctional{

public CopyFromCToB(){
    super("Copy C to B","Copy C to B","image/CtoB.png");
    setEnabled(false);
}

    @Override
    protected void go(ActionEvent e) {
        ((ImageZoneC)zoneC).copyToB();
//        copyFromCToB.setEnabled(true);
    }
}
