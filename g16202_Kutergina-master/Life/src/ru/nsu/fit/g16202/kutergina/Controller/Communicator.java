package ru.nsu.fit.g16202.kutergina.Controller;

import ru.nsu.fit.g16202.kutergina.Model.Constants;
import ru.nsu.fit.g16202.kutergina.Model.HexagonLifeAction;
import ru.nsu.fit.g16202.kutergina.View.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Communicator {
    private HexagonLifeAction hexagonLifeAction;

    private HexagonField hexagonField;
    private LifeToolBar lifeToolBar;
    private LifeMenuBar lifeMenuBar;
    private Settings settings;
    private Point lastChangeCell = new Point(-1, -1);
    public static boolean changeFlag = false;


    public Settings getSettings() {
        return settings;
    }

    public LifeToolBar getLifeToolBar() {
        return lifeToolBar;
    }

    public LifeMenuBar getLifeMenuBar() {
        return lifeMenuBar;
    }

    public HexagonLifeAction getHexagonLifeAction() {
        return hexagonLifeAction;
    }

    public HexagonField getHexagonField() {
        return hexagonField;
    }


    public Communicator(HexagonLifeAction hexagonLifeAction,
                        HexagonField hexagonField,
                        LifeToolBar lifeToolBar,
                        LifeMenuBar lifeMenuBar,
                        Settings settings) {
        this.hexagonLifeAction = hexagonLifeAction;
        this.hexagonField = hexagonField;
        this.lifeToolBar = lifeToolBar;
        this.lifeMenuBar = lifeMenuBar;
        this.settings = settings;


        hexagonField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hexagonField.reFloodCell(e);
                if(lifeToolBar.getImpactsButton().isSelected()){
                fieldToModel();
                hexagonField.clearImpact();
                hexagonField.printImpact(hexagonLifeAction.getImpacts());
            }
            }

        });

        hexagonField.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(hexagonField.getMode() == ModeField.XOR){
                    Point indexByPixel = hexagonField.getIndexByPixel(e.getX(), e.getY());
                    if(indexByPixel == null || lastChangeCell.equals(indexByPixel)){
                        return;
                    }
                    lastChangeCell = indexByPixel;
                }
                hexagonField.reFloodCell(e);
                if(lifeToolBar.getImpactsButton().isSelected()){
                    fieldToModel();
                    hexagonField.clearImpact();
                    hexagonField.printImpact(hexagonLifeAction.getImpacts());
                }
            }
        });
    }


    public void fieldToView() {
        for(int j = 0; j < Constants.getY(); j++){
            for(int i  = 0 ; i < Constants.getX() - j%2; i++) {
                boolean stateCell = hexagonLifeAction.getField().getStateCell(i, j);
                Point pointByIndex = hexagonField.getPointByIndex(i, j);
                if(stateCell){
                    hexagonField.floodFill(hexagonField.getImage(),hexagonField.getColorDie(),
                            hexagonField.getColorLive(),
                            pointByIndex.x,
                            pointByIndex.y
                    );
                }else{
                    hexagonField.floodFill(hexagonField.getImage(),hexagonField.getColorLive(),
                            hexagonField.getColorDie(),
                            pointByIndex.x,
                            pointByIndex.y
                    );
                }
            }
        }
    }

    public void fieldToModel() {
        for(int j = 0; j < Constants.getY(); j++){
            for(int i  = 0 ; i < Constants.getX() - j%2; i++){
                boolean stateCell = hexagonField.getStateCell(i, j);
                hexagonLifeAction.changeStateFieldCell(i, j, stateCell);
            }
        }

    }

    static void repaintImpact(Communicator communicator) {
        if(communicator.getLifeToolBar().getImpactsButton().isSelected()){
            communicator.fieldToModel();
            communicator.getHexagonField().clearImpact();
            communicator.getHexagonField().printImpact(communicator.getHexagonLifeAction().getImpacts());
        }
    }

}
