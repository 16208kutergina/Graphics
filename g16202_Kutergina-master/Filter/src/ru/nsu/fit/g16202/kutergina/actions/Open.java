package ru.nsu.fit.g16202.kutergina.actions;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static ru.nsu.fit.g16202.kutergina.Frame.zoneA;

public class Open extends JButtonFunctional {

    @Override
    protected void go(ActionEvent e) {
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.setCurrentDirectory(new File("Data"));
        fileOpen.setFileFilter(new FileNameExtensionFilter("image", "bmp", "png", "jpg", "jpeg"));
        int ret = fileOpen.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileOpen.getSelectedFile();
                zoneA.setImage(ImageIO.read(file));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public Open() {
        super("Open", "Open image", "image/open.png");
    }
}

