package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;

public class LifeToolBar {
    private Communicator communicator;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton clearButton;
    private JToggleButton replaceButton;
    private JToggleButton xorButton;
    private JButton settingsButton;
    private JButton nextButton;
    private JButton aboutButton;
    private JToggleButton runButton;
    private JToggleButton impactsButton;

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
        impactsButton.addActionListener(new ImpactAction(communicator));
        nextButton.addActionListener(new NextAction(communicator));
        clearButton.addActionListener(new ClearAction(communicator));
        runButton.addActionListener(new RunAction(communicator));
        replaceButton.addActionListener(new ReplaceModeAction(communicator));
        replaceButton.setSelected(true);
        xorButton.addActionListener(new XorModeAction(communicator));
        settingsButton.addActionListener(new SettingsAction(communicator));
        openButton.addActionListener(new OpenAction(communicator));
        saveButton.addActionListener(new SaveAction(communicator));
        newButton.addActionListener(new NewAction(communicator));
        aboutButton.addActionListener(new AboutAction());

        impactsButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Impact");
            }
        });
        nextButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Next");
            }
        });
        clearButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Clear");
            }
        });
        replaceButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Replace");
            }
        });
        xorButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Xor");
            }
        });
        settingsButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Settings");
            }
        });
        openButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Open");
            }
        });
        saveButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Save");
            }
        });
        newButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("New");
            }
        });
        aboutButton.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("About");
            }
        });

    }

    public JToggleButton getReplaceButton() {
        return replaceButton;
    }

    public JToggleButton getXorButton() {
        return xorButton;
    }

    public JToolBar setToolBar() {
        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        newButton = getjButtonWithIcon("../image/new.png", "New");
        openButton = getjButtonWithIcon("../image/open.png", "Open");
        saveButton = getjButtonWithIcon("../image/save.png", "Save");
        clearButton = getjButtonWithIcon("../image/clear.png", "Clear");
        replaceButton = new JToggleButton("R");
        replaceButton.setToolTipText("Replace");
        xorButton = new JToggleButton("X");
        xorButton.setToolTipText("Xor");
        settingsButton = getjButtonWithIcon("../image/settings.png", "Settings");
        runButton = new JToggleButton();
        URL runURL = Frame.class.getResource("../image/run.png");
        runButton.setToolTipText("Run");
        ImageIcon runIcon = new ImageIcon(runURL);
        runButton.setIcon(runIcon);
        nextButton = getjButtonWithIcon("../image/next.png", "Next");
        impactsButton = new JToggleButton();
        URL impactURL = Frame.class.getResource("../image/impacts.png");
        impactsButton.setToolTipText("Impacts");
        ImageIcon impactIcon = new ImageIcon(impactURL);
        impactsButton.setIcon(impactIcon);//getjButtonWithIcon("../image/impacts.png", "Impacts");
        aboutButton = getjButtonWithIcon("../image/about.png", "About");


        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(clearButton);
        toolBar.add(impactsButton);
        toolBar.addSeparator();
        toolBar.add(replaceButton);
        toolBar.add(xorButton);
        toolBar.addSeparator();
        toolBar.add(runButton);
        toolBar.add(nextButton);
        toolBar.addSeparator();
        toolBar.add(settingsButton);
        toolBar.add(aboutButton);

        return toolBar;
    }

    private JButton getjButtonWithIcon(String file, String toolTipText) {
        JButton nextButton = new JButton();
        URL nextURL = Frame.class.getResource(file);
        ImageIcon nextIcon = new ImageIcon(nextURL);
        nextButton.setToolTipText(toolTipText);
        nextButton.setIcon(nextIcon);
        return nextButton;
    }

    public JToggleButton getImpactsButton(){
        return impactsButton;
    }

    public JToggleButton getRunButton(){
        return runButton;
    }

}
