package ru.nsu.fit.g16202.kutergina.Model;

import java.awt.*;
import java.util.ArrayList;

public class HexagonLifeAction {
    private static final int[][] firstNeighborsCoords1 = {{0,-1},{1, -1},{-1, 0},{1, 0},{0, 1},{1, 1}};
    private static final int[][] secondNeighborsCoords1 = {{-1,-1},{0, -2},{2, -1},{2, 1},{0, 2},{-1, 1}};
    private static final int[][] firstNeighborsCoords2 = {{-1, -1},{0, -1},{-1, 0},{1, 0},{-1, 1}, {0, 1}};
    private static final int[][] secondNeighborsCoords2 = {{-2, -1},{0, -2},{1, -1},{1, 1},{0, 2}, {-2, 1}};


    private FieldHexagonLife field;
    private FieldHexagonLife copyField;
    private double[][] impacts;


    public double[][] getImpacts() {
        return impacts;
    }

    public int getCountLifeCells(){
        int countLifeCells = 0;
        for (int j = 0; j < field.getMaxY(); j++) {
            for (int i = 0; i < (field.getMaxX() - j % 2) ; i++) {
                if (field.getStateCell(i, j)) {
                    countLifeCells++;
                }
            }
        }
        return countLifeCells;
    }

    public ArrayList<Point> getLifeCells(){
        ArrayList<Point> lifeCells= new ArrayList<>();
        for (int j = 0; j < field.getMaxY(); j++) {
            for (int i = 0; i < (field.getMaxX() - j % 2) ; i++) {
                if (field.getStateCell(i, j)) {
                    lifeCells.add(new Point(i, j));
                }
            }
        }
        return lifeCells;
    }

    public FieldHexagonLife getField() {
        return field;
    }

    public HexagonLifeAction() {
        setHexagonLifeAction();
    }

    public void setHexagonLifeActionWithOldCells(){
        impacts = new double[Constants.getX()][Constants.getY()];
        FieldHexagonLife newField = new FieldHexagonLife();
        for (int j = 0; j < field.getMaxY(); j++) {
            for (int i = 0; i < (field.getMaxX() - j % 2) ; i++) {
                try {
                    newField.changeStateCell(i, j, field.getStateCell(i, j));
                }catch (Exception ignored){}
                }
        }
        field = newField;
        this.copyField = new FieldHexagonLife();
    }

    public void setHexagonLifeAction() {
        impacts = new double[Constants.getX()][Constants.getY()];
        this.copyField = new FieldHexagonLife();
        field = new FieldHexagonLife();
    }

    public void printImpact(){
        for (int j = 0; j < field.getMaxY(); j++) {
            for (int i = 0; i < (field.getMaxX() - j % 2) ; i++) {
                System.out.print(impacts[i][j] + " ");
            }
            System.out.println();
            if(j%2 == 0){
                System.out.print("   ");
            }
        }
        System.out.println();
    }

    public void changeStateFieldCell(int i, int j, boolean state){
        if(field.getStateCell(i, j) != state) {
            field.changeStateCell(i, j, state);
            countImpact();
        }
    }

    public void nextStateField(){
        for (int j = 0; j <field.getMaxY(); j++) {
            for (int i = 0; i < (field.getMaxX() - j % 2) ; i++) {
                birth(i,j,impacts[i][j], copyField);
                die(i, j,impacts[i][j], copyField);
            }
        }
        field = copyField;
        countImpact();
    }

    public void countImpact() {
        for (int j = 0; j <field.getMaxY(); j++) {
            for (int i = 0; i < (field.getMaxX() - j % 2) ; i++) {
                int countFirst = countFirstLivingNeighbors(i, j);
                int countSecond = countSecondLivingNeighbors(i, j);
                impacts[i][j] = impact(countFirst, countSecond);
            }}
    }


    private void birth(int x, int y, double impact, FieldHexagonLife field){
        if(!field.getStateCell(x,y)
                && impact >= Constants.getBirthBegin()
                && impact <= Constants.getBirthEnd()){
            field.changeStateCell(x, y, true);
        }
    }

    private void die(int x, int y, double impact, FieldHexagonLife field){
        if(field.getStateCell(x, y)
                && (impact < Constants.getLiveBegin()
                || impact > Constants.getLiveEnd())){
            field.changeStateCell(x, y, false);
        }
    }

    public void clear(){
        field.clear();
        countImpact();
    }

    private double impact(int countFirstNeigh, int countSecondNeigh){
        return countFirstNeigh*Constants.getFstImpact()
                +countSecondNeigh*Constants.getSndImpact();
    }

    private ArrayList<Boolean> getStatesNeighbors(int x, int y, int[][] secondNeighborsCoords) {
        ArrayList<Boolean> neighbors = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            int X = x + secondNeighborsCoords[i][0];
            int Y = y + secondNeighborsCoords[i][1];
            neighbors.add(field.getStateCell(X,Y));
        }
        return neighbors;
    }


    private int countLivingNeighbors(ArrayList<Boolean> neighbors){
        int count = 0;
        for(boolean state : neighbors) {
            if (state) {
                count++;
            }
        }
        return count;
    }

    private int countFirstLivingNeighbors(int x, int y){
        return countNeighbors(x, y, firstNeighborsCoords1, firstNeighborsCoords2);
    }

    private int countSecondLivingNeighbors(int x, int y){
        return countNeighbors(x, y, secondNeighborsCoords1, secondNeighborsCoords2);
    }

    private int countNeighbors(int x, int y, int[][] firstNeighborsCoords1, int[][] firstNeighborsCoords2) {
        ArrayList<Boolean> neighbors;
        if(y%2 ==1) {
            neighbors = getStatesNeighbors(x, y, firstNeighborsCoords1);
        }else{
            neighbors = getStatesNeighbors(x, y, firstNeighborsCoords2);
        }
        return countLivingNeighbors(neighbors);
    }
}
