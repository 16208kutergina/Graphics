package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LifeMenuBar {
    private Communicator communicator;
    private JMenuItem about;
    private JMenuItem settings;
    private JCheckBoxMenuItem toolbar;
    private JMenuItem clear;
    private JRadioButtonMenuItem replace;
    private JRadioButtonMenuItem xor;
    private JCheckBoxMenuItem run;
    private JMenuItem next;
    private JCheckBoxMenuItem impact;
    private JMenuItem newItem;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem saveAs;
    private JMenuItem exit;

    public JRadioButtonMenuItem getReplace() {
        return replace;
    }

    public JCheckBoxMenuItem getToolbar() {
        return toolbar;
    }

    public JCheckBoxMenuItem getStatusBar() {
        return statusBar;
    }

    private JCheckBoxMenuItem statusBar;

    public JRadioButtonMenuItem getXor() {
        return xor;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;

        next.addActionListener(new NextAction(communicator));
        run.addActionListener(new RunAction(communicator));
        clear.addActionListener(new ClearAction(communicator));
        replace.addActionListener(new ReplaceModeAction(communicator));
        xor.addActionListener(new XorModeAction(communicator));
        settings.addActionListener(new SettingsAction(communicator));
        open.addActionListener(new OpenAction(communicator));
        save.addActionListener(new SaveAction(communicator));
        saveAs.addActionListener(new SaveAction(communicator));
        newItem.addActionListener(new NewAction(communicator));
        about.addActionListener(new AboutAction());
        exit.addActionListener(new ExitActions(communicator));

        impact.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Impact");
            }
        });
        next.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Next");
            }
        });
        clear.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Clear");
            }
        });
        replace.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Replace");
            }
        });
        xor.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Xor");
            }
        });
        settings.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Settings");
            }
        });
        open.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Open");
            }
        });
        save.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Save");
            }
        });
        newItem.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("New");
            }
        });
        about.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("About");
            }
        });
        exit.addMouseListener(new StatusMouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StatusBar.getInstance().setMessage("Exit");
            }
        });
    }

    public JCheckBoxMenuItem getImpact(){
        return impact;
    }

    public void setMenuBar(JMenuBar menuBar){
        JMenu file = setJMenuFile();
        JMenu edit = setJMenuEdit();
        JMenu view = setJMenuView();
        JMenu properties = setJMenuProperties();
        JMenu help = setJMenuHelp();
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(properties);
        menuBar.add(help);
    }

    private JMenu setJMenuHelp() {
        JMenu help = new JMenu("Help");
        about = new JMenuItem("About game");
        help.add(about);
        return help;
    }

    private JMenu setJMenuProperties() {
        JMenu properties = new JMenu("Properties");
        settings = new JMenuItem("Settings", KeyEvent.VK_S);
        properties.add(settings);
        return properties;
    }

    private JMenu setJMenuView() {
        JMenu view = new JMenu("View");
        toolbar = new JCheckBoxMenuItem("Toolbar");
        statusBar = new JCheckBoxMenuItem("Statusbar");
        toolbar.setState(true);
        statusBar.setState(true);
        view.add(toolbar);
        view.add(statusBar);
        return view;
    }

    private JMenu setJMenuEdit() {
        JMenu edit = new JMenu("Edit");
         clear = addMenuItemWithAccelerator( "Clear", KeyEvent.VK_C, null);
        JMenu mode = new JMenu("Mode");
        replace = new JRadioButtonMenuItem("Replace");
        xor = new JRadioButtonMenuItem("xor");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(replace);
        buttonGroup.add(xor);
        replace.setSelected(true);
        run = new JCheckBoxMenuItem("Run");
        next = addMenuItemWithAccelerator("Next", KeyEvent.VK_N, null);
        impact = new JCheckBoxMenuItem("Impact");
        edit.add(clear);
        edit.addSeparator();

        mode.add(replace);
        mode.add(xor);

        edit.add(mode);
        edit.add(run);
        edit.add(next);
        edit.add(impact);

        return edit;
    }

    private JMenu setJMenuFile() {
        JMenu file = new JMenu("File");
        newItem = addMenuItemWithAccelerator("New", KeyEvent.VK_N, "control N");
        open = addMenuItemWithAccelerator("Open..", KeyEvent.VK_O, "control O");
        save = addMenuItemWithAccelerator("Save", KeyEvent.VK_S, "control S");
        saveAs = addMenuItemWithAccelerator("Save as..", KeyEvent.VK_S, null);
        exit = addMenuItemWithAccelerator("Exit", KeyEvent.VK_E, null);
        file.add(newItem);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);
        return file;
    }

    private JMenuItem addMenuItemWithAccelerator(String aNew, int vkN, String s) {
        JMenuItem newItem = new JMenuItem(aNew, vkN);
        if(s != null) {
            KeyStroke ctrlN = KeyStroke.getKeyStroke(s);
            newItem.setAccelerator(ctrlN);
        }
        return newItem;
    }

    public JCheckBoxMenuItem getRun() {
        return run;
    }
}
