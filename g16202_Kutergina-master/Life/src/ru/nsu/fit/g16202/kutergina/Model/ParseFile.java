package ru.nsu.fit.g16202.kutergina.Model;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;

import javax.swing.*;
import java.io.*;

public class ParseFile {
    private Communicator communicator;
    private int x;
    private int y;
    private int sizeCell;
    private int widthLine;

    public ParseFile(Communicator communicator) {
        this.communicator = communicator;
    }

    public void setField(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        try {
            readSizeField(bufferedReader);
            readWidthLine(bufferedReader);
            readSizeCell(bufferedReader);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Incorrect file", "Warn",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int oldX = Constants.getX();
        int oldY = Constants.getY();
        int oldSizeCell = Constants.getSizeCell();
        int oldWidthLine = Constants.getWidthLine();
        try {
            setParam(x, y, sizeCell, widthLine);
            communicator.getHexagonLifeAction().setHexagonLifeAction();
            communicator.getHexagonField().setField();
            setLiveCells(bufferedReader);
            communicator.fieldToView();
        } catch (Exception e) {
            try {
                setParam(oldX, oldY, oldSizeCell, oldWidthLine);
            } catch (Exception e1) {
                System.out.println("Exception param");
            }
            JOptionPane.showMessageDialog(new JFrame(), "Incorrect file", "Warn",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setParam(int x, int y, int sizeCell, int widthLine) throws Exception {
        Constants.setX(x);
        Constants.setY(y);
        Constants.setSizeCell(sizeCell);
        Constants.setWidthLine(widthLine);
    }

    private void readSizeCell(BufferedReader bufferedReader) throws IOException {
        sizeCell = Integer.parseInt(getStringWithoutComment(getStringWithoutSpace(bufferedReader.readLine())));
    }

    private String getStringWithoutComment(String s){
        return s.split("//")[0];
    }

    private String getStringWithoutSpace(String s){
        return s.replaceAll("\\s", "");
    }

    private void setLiveCells(BufferedReader bufferedReader) throws IOException {
        String stringWithoutSpace = getStringWithoutSpace(bufferedReader.readLine());
        int liveCells = Integer.parseInt(getStringWithoutComment(stringWithoutSpace));
        for(int i = 0; i < liveCells; i++){
            String s = bufferedReader.readLine();
            if (s != null) {
                String stringWithoutComment = getStringWithoutComment(s);
                String[] split = stringWithoutComment.split(" ");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                communicator.getHexagonLifeAction().changeStateFieldCell(x, y, true);
            }
        }
    }

    private void readWidthLine(BufferedReader bufferedReader) throws IOException {
        widthLine = Integer.parseInt(getStringWithoutComment(getStringWithoutSpace(bufferedReader.readLine())));
    }

    private void readSizeField(BufferedReader bufferedReader) throws IOException {
        String[] split = getStringWithoutComment(bufferedReader.readLine()).split(" ");
        x = Integer.parseInt(split[0]);
        y = Integer.parseInt(split[1]);
    }
}
