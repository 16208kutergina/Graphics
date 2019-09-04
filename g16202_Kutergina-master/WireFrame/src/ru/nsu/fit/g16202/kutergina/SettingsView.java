package ru.nsu.fit.g16202.kutergina;

import java.awt.*;

public class SettingsView {
    private Color colorLineSpline = Color.BLUE;
    private Color colorPointSpline = Color.red;
    private Color colorBackgroundSpline = Color.BLACK;

    private int radiusPointSpline = 7;

    private int sizeSideImageSpline = 800;
    private int sizeSideImageFigure = 800;
//
//    private Color colorCreatingFigure = Color.BLACK;
//
//    public Color getColorCreatingFigure() {
//        return colorCreatingFigure;
//    }
//
//    public void setColorCreatingFigure(Color colorCreatingFigure) {
//        this.colorCreatingFigure = colorCreatingFigure;
//    }

    public int getSizeSideImageFigure() {
        return sizeSideImageFigure;
    }

    public void setSizeSideImageFigure(int sizeSideImageFigure) {
        this.sizeSideImageFigure = sizeSideImageFigure;
    }

    public int getSizeSideImageSpline() {
        return sizeSideImageSpline;
    }

    public void setSizeSideImageSpline(int sizeSideImageSpline) {
        this.sizeSideImageSpline = sizeSideImageSpline;
    }

    public Color getColorLineSpline() {
        return colorLineSpline;
    }

    public void setColorLineSpline(Color colorLineSpline) {
        this.colorLineSpline = colorLineSpline;
    }

    public Color getColorPointSpline() {
        return colorPointSpline;
    }

    public void setColorPointSpline(Color colorPointSpline) {
        this.colorPointSpline = colorPointSpline;
    }

    public Color getColorBackgroundSpline() {
        return colorBackgroundSpline;
    }

    public void setColorBackgroundSpline(Color colorBackgroundSpline) {
        this.colorBackgroundSpline = colorBackgroundSpline;
    }

    public int getRadiusPointSpline() {
        return radiusPointSpline;
    }

    public void setRadiusPointSpline(int radiusPointSpline) {
        this.radiusPointSpline = radiusPointSpline;
    }
}
