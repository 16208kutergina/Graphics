package ru.nsu.fit.g16202.kutergina;

import ru.nsu.fit.g16202.kutergina.actions.*;
import ru.nsu.fit.g16202.kutergina.volumeRendering.Const;
import ru.nsu.fit.g16202.kutergina.volumeRendering.VolumeAction;
import ru.nsu.fit.g16202.kutergina.volumeRendering.VolumeOpen;
import ru.nsu.fit.g16202.kutergina.zones.ImageZone;
import ru.nsu.fit.g16202.kutergina.zones.ImageZoneA;
import ru.nsu.fit.g16202.kutergina.zones.ImageZoneB;
import ru.nsu.fit.g16202.kutergina.zones.ImageZoneC;

import javax.swing.*;
import java.awt.*;

import static ru.nsu.fit.g16202.kutergina.Constants.sizSpace;
import static ru.nsu.fit.g16202.kutergina.Constants.sizeSideImage;

public class Frame extends JFrame {
    public static ImageZoneA zoneA = new ImageZoneA();
    public static ImageZone zoneB = new ImageZoneB();
    public static ImageZone zoneC = new ImageZoneC();
    public static Functional about = new About();
    public static Functional newV = new New();
    public static Functional open = new Open();
    public static Functional select = new Select();
    public static Functional copyFromBToC = new CopyFromBToC();
    public static Functional copyFromCToB = new CopyFromCToB();
    public static Functional toBlackWhite = new ToBlackWhite();
    public static Functional toNegativity = new ToNegativity();
    public static Functional toFloydSteinberg = new ToFloydSteinberg();
    public static Functional toOrderedDithering = new ToOrderedDithering();
    public static Functional toTwoX = new ToTwoX();
    public static Functional toSobel = new ToSobel();
    public static Functional toBlur = new ToBlur();
    public static Functional toSharpness = new ToSharpness();
    public static Functional toWatercolor = new ToWatercolor();
    public static Functional toEmboss = new ToEmboss();
    public static Functional toRoberts = new ToRoberts();
    public static Functional toGammaCorrection = new ToGammaCorrection();
    public static Functional toTurn = new ToTurn();
    public static Functional save = new Save();
    public static ImageZone absorZone = new ImageZone(Const.width, Const.height);
    public static ImageZone emissZone = new ImageZone(Const.width, Const.height);
    public static VolumeOpen volumeOpen = new VolumeOpen();
    public static VolumeAction volumeAction = new VolumeAction();


//коэфф blackwite

    public Frame(){
        super("Filter");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(640, 450));
        setPreferredSize(new Dimension(3 * sizeSideImage + 7 * sizSpace, sizeSideImage + 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        JMenuBar menuBar = new JMenuBar();

        toolBar.add(newV.getButton());
        toolBar.add(open.getButton());
        toolBar.add(save.getButton());
        toolBar.addSeparator();
        toolBar.add(select.getButton());
        toolBar.addSeparator();
        toolBar.add(copyFromBToC.getButton());
        toolBar.add(copyFromCToB.getButton());
        toolBar.addSeparator();
        toolBar.add(toBlackWhite.getButton());
        toolBar.add(toNegativity.getButton());
        toolBar.add(toFloydSteinberg.getButton());
        toolBar.add(toOrderedDithering.getButton());
        toolBar.add(toTwoX.getButton());
        toolBar.add(toSobel.getButton());
        toolBar.add(toRoberts.getButton());
        toolBar.add(toBlur.getButton());
        toolBar.add(toSharpness.getButton());
        toolBar.add(toWatercolor.getButton());
        toolBar.add(toEmboss.getButton());
        toolBar.add(toGammaCorrection.getButton());
        toolBar.add(toTurn.getButton());
        toolBar.addSeparator();
        toolBar.add(about.getButton());
        toolBar.addSeparator();
        toolBar.add(volumeOpen.getButton());
        toolBar.add(volumeAction.getButton());

        JMenu file = new JMenu("File");
        file.add(newV.getMenuItem());
        file.add(open.getMenuItem());


        JMenu edit = new JMenu("Edit");
        edit.add(select.getMenuItem());
        edit.add(copyFromBToC.getMenuItem());
        edit.add(copyFromCToB.getMenuItem());

        JMenu effects = new JMenu("Effects");
        effects.add(toBlackWhite.getMenuItem());
        effects.add(toNegativity.getMenuItem());
        effects.add(toFloydSteinberg.getMenuItem());
        effects.add(toOrderedDithering.getMenuItem());
        effects.add(toTwoX.getMenuItem());
        effects.add(toSobel.getMenuItem());
        effects.add(toRoberts.getMenuItem());
        effects.add(toBlur.getMenuItem());
        effects.add(toSharpness.getMenuItem());
        effects.add(toWatercolor.getMenuItem());
        effects.add(toEmboss.getMenuItem());
        effects.add(toGammaCorrection.getMenuItem());
        effects.add(toTurn.getMenuItem());
        effects.add(save.getMenuItem());



        JMenu info = new JMenu("Info");
        info.add(about.getMenuItem());

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(effects);
        menuBar.add(info);

        ru.nsu.fit.g16202.kutergina.Panel panel = new Panel();
        JScrollPane jScrollPane = new JScrollPane(panel);

        jScrollPane.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));

        setJMenuBar(menuBar);
        menuBar.setVisible(true);
        add(toolBar, BorderLayout.PAGE_START);
        add(jScrollPane, BorderLayout.CENTER);
        pack();
        setVisible(true);



    }
}
