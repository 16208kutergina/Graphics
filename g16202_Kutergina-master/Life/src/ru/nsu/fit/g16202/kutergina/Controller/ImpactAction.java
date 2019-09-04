package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.Model.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImpactAction implements ActionListener {
    private Communicator communicator;

    public ImpactAction(Communicator communicator){
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Constants.getSizeCell() >= 5) {
            FlagsButton.setImpact();
            if (!FlagsButton.isImpact()) {
                communicator.getHexagonField().clearImpact();
                communicator.getLifeToolBar().getImpactsButton().setSelected(false);
                communicator.getLifeMenuBar().getImpact().setState(false);
            } else {
                communicator.fieldToModel();
                double[][] impacts = communicator.getHexagonLifeAction().getImpacts();
                communicator.getHexagonField().printImpact(impacts);
                communicator.getLifeToolBar().getImpactsButton().setSelected(true);
                communicator.getLifeMenuBar().getImpact().setState(true);
            }
        }
    }
}
