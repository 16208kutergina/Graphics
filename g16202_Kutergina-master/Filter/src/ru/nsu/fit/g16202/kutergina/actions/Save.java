package ru.nsu.fit.g16202.kutergina.actions;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static ru.nsu.fit.g16202.kutergina.Frame.zoneC;

public class Save extends JButtonFunctional {
    public Save() {
        super("Save", "Save", "image/save.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("Data"));
        fc.setFileFilter(new FileNameExtensionFilter("image", "bmp", "png", "jpg", "jpeg"));
        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
            try ( FileOutputStream fw = new FileOutputStream(fc.getSelectedFile()))  {
                ImageIO.write(zoneC.getBaseImage(),"png", fw);
            fw.flush();
            }
            catch ( IOException ex ) {
                System.out.println("Failed to save file");
            }
        }
    }
    }

