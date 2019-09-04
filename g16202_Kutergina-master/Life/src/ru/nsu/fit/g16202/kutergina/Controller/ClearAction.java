package ru.nsu.fit.g16202.kutergina.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearAction implements ActionListener {
        private Communicator communicator;


        public ClearAction(Communicator communicator) {
            this.communicator = communicator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            communicator.getHexagonLifeAction().clear();
            communicator.fieldToView();
            Communicator.repaintImpact(communicator);
        }


}


