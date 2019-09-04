package ru.nsu.fit.g16202.kutergina.volumeRendering;

import ru.nsu.fit.g16202.kutergina.Frame;
import ru.nsu.fit.g16202.kutergina.actions.JButtonFunctional;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VolumeOpen extends JButtonFunctional {

    public VolumeOpen() {
        super("Open config", "Open config", "image/open.png");
        setEnabled(false);
    }

    @Override
    protected void go(ActionEvent e) {
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.setCurrentDirectory(new File("Data"));
        fileOpen.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int ret = fileOpen.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileOpen.getSelectedFile();
                ParseFileGraph pg = new ParseFileGraph(file);
                Graph graph = new Graph(pg.getAbsorptionPoints(), pg.getEmissionPoints());
                BufferedImage absorption = graph.getAbsorption();
                BufferedImage emission = graph.getEmission();
                absorption = getTransformImage(absorption);
                emission = getTransformImage(emission);
                Frame.absorZone.setImage(absorption);
                Frame.emissZone.setImage(emission);
                Frame.volumeAction.setData(pg);
                Frame.volumeAction.setEnabled(true);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private BufferedImage getTransformImage(BufferedImage absorption) {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -absorption.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        absorption = op.filter(absorption, null);
        return absorption;
    }
}
