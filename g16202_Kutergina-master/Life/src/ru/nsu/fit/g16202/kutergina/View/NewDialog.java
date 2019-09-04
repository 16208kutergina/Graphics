package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;
import ru.nsu.fit.g16202.kutergina.Model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.nsu.fit.g16202.kutergina.View.Settings.setRightSize;

public class NewDialog extends JFrame {
    Communicator communicator;
    private JTextField n;
    private JTextField m;
    private JButton ok;
    private JButton cancel;

    public NewDialog(Communicator communicator) {
        super("Settings");
        setLocationRelativeTo(null);
        this.communicator = communicator;
        setResizable(false);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        n = new JTextField();
        n.setText(String.valueOf(Constants.getX()));
        n.addActionListener(e -> {
            setRightSize(n, Constants.minX, Constants.maxX);
        });
        m = new JTextField();
        m.setText(String.valueOf(Constants.getY()));
        m.addActionListener(e -> {
            setRightSize(m, Constants.minY, Constants.maxY);

        });

        ok = new JButton("OK");
        //if (FlagsButton.isImpact()) {
          if(communicator.getLifeToolBar().getImpactsButton().isSelected()){
            double[][] impacts = communicator.getHexagonLifeAction().getImpacts();
            communicator.getHexagonField().printImpact(impacts);
        }
        communicator.getHexagonField().repaint();
        sizeSettings(panel);
        add(panel, BorderLayout.WEST);
        add(ok, BorderLayout.AFTER_LAST_LINE);
        ok.addActionListener(e -> {
            try {
                Constants.setX(Integer.parseInt(n.getText()));
                Constants.setY(Integer.parseInt(m.getText()));
            } catch (Exception e1) {
                System.out.println("Incorrect data");
            }
            communicator.getHexagonLifeAction().setHexagonLifeAction();
            communicator.getHexagonField().setField();
            dispose();
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pack();
        setVisible(true);
    }


    private void sizeSettings(JPanel jPanel) {
        JPanel sizeField = new JPanel();
        sizeField.setPreferredSize(new Dimension(150, 50));
        Settings.sizeSettings(sizeField, n, m);
        jPanel.add(sizeField, BorderLayout.CENTER);
    }

}
