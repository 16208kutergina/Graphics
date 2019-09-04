package ru.nsu.fit.g16202.kutergina.Model;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static ru.nsu.fit.g16202.kutergina.Controller.Communicator.changeFlag;

public class SaveFile {
    public static void saveFile(Communicator communicator) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("Data"));
        fc.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
            try ( FileWriter fw = new FileWriter(fc.getSelectedFile()) ) {
                communicator.fieldToModel();
                writeSize(fw);
                writeWidthLine(fw);
                writeSizeCell(fw);
                writeCountLifeCells(fw, communicator);
                writeLifeCells(fw, communicator);
            }
            catch ( IOException ex ) {
                System.out.println("Failed to save file");
            }
        }
        changeFlag = false;
    }

    private static void writeSize(FileWriter fw) throws IOException {
        fw.write(Constants.getX()+ " "+ Constants.getY() + "\n");
    }

    private static void writeWidthLine(FileWriter fw) throws IOException {
        fw.write(Constants.getWidthLine() +"\n");
    }

    private static void writeSizeCell(FileWriter fw) throws IOException {
        fw.write(Constants.getSizeCell() + "\n");
    }
    private static void writeCountLifeCells(FileWriter fw, Communicator communicator) throws IOException {
        fw.write(communicator.getHexagonLifeAction().getCountLifeCells()+"\n");
    }
    private static void writeLifeCells(FileWriter fw, Communicator communicator) throws IOException {
        ArrayList<Point> lifeCells = communicator.getHexagonLifeAction().getLifeCells();
        for(Point p : lifeCells) {
            fw.write(p.x +" "+ p.y + "\n");
        }
    }
}
