package ru.nsu.fit.g16202.kutergina.Model;


public class FieldHexagonLife implements Field{
    private boolean[][] field;
    private int maxX;
    private int maxY;

    public int getMaxX() {
        return maxX;
    }
    public int getMaxY() {
        return maxY;
    }

    public FieldHexagonLife() {
        setField();
    }

    public void setField() {
        this.maxY = Constants.getY();
        this.maxX = Constants.getX();
        this.field = new boolean[maxX][maxY];
    }

    public boolean getStateCell(int x, int y){
        if(x >= (maxX - y % 2) || x < 0 || y >= maxY || y < 0){
            return false;
        }
        return field[x][y];
    }

    public void changeStateCell(int x, int y, boolean state){
        if(!(x >= (maxX - y % 2) || x < 0 || y >= maxY || y < 0)) {
            field[x][y] = state;
        }
    }

    public void clear(){
        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                field[i][j] = false;
            }
        }
    }

    public void printField() {
        for (int j = 0; j < maxY; j++) {
            for (int i = 0; i < (maxX - j % 2) ; i++) {
                System.out.print(getStateCell(i,j) + " ");
            }
            System.out.println();
            if(j%2 == 0){
                System.out.print("   ");
            }
        }
        System.out.println();
    }

}
