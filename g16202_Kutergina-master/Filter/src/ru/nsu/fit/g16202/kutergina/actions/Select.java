package ru.nsu.fit.g16202.kutergina.actions;

import java.awt.event.ActionEvent;

import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;

public class Select extends JToggleButtonFunctional {

    public Select(){
        super("Select", "Select", "image/select.png");
        setEnabled(false);
    }
    @Override
    protected void go(ActionEvent e) {
        isSelected = ! isSelected;
            if (isSelected) {
                zoneA.setPaintRect(true);
               setSelected(true);
            } else {
                zoneA.setPaintRect(false);
               setSelected(false);
            }
    }


}
