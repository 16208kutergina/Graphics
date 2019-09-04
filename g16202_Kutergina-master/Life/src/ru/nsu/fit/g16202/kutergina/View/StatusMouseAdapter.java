package ru.nsu.fit.g16202.kutergina.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StatusMouseAdapter extends MouseAdapter {

    @Override
    public void mouseExited(MouseEvent e) {
        StatusBar.getInstance().setMessage("Ready");
    }
}
