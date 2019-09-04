package ru.nsu.fit.g16202.kutergina.actions;

import ru.nsu.fit.g16202.kutergina.Panel;

import java.awt.event.ActionEvent;

import static ru.nsu.fit.g16202.kutergina.Frame.*;

public class New extends JButtonFunctional {

    public New(){
        super("New", "New", "image/new.png");
    }

    @Override
    protected void go(ActionEvent e) {
        select.setEnabled(false);
        ((JToggleButtonFunctional)select).setSelected(false);
        copyFromCToB.setEnabled(false);
        Panel.setAllEnabled(false);
        zoneA.setNewImage();
        zoneB.setNewImage();
        zoneC.setNewImage();
        zoneA.repaint();
        zoneB.repaint();
        zoneC.repaint();
    }

}
