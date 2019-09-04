package ru.nsu.fit.g16202.kutergina.View;

import ru.nsu.fit.g16202.kutergina.Controller.Communicator;
import ru.nsu.fit.g16202.kutergina.Model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Stack;

import static java.lang.Math.*;

public class HexagonField extends JPanel {
    private int height;
    private BufferedImage image;
    private BufferedImage imageImpact;
    private BufferedImage imageColor;
    private int colorLive = Color.GRAY.getRGB();
    private int colorDie = Color.LIGHT_GRAY.getRGB();
    private Point[][] centerCoords;
    private Point[][] coordsForImpact;
    private ModeField mode = ModeField.REPLACE;
    private JScrollPane jScrollPane;
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private int width;

    public BufferedImage getImageColor() {
        return imageColor;
    }

    public ModeField getMode() {
        return mode;
    }

    public void setMode(ModeField mode) {
        this.mode = mode;
    }

    public Point getIndexByPixel(int x, int y){
        if(x >= 0 && x < width && y >= 0 && y < height){
            int rgb = imageColor.getRGB(x, y);
            x = rgb % 100;
            y = rgb / 100;
            return new Point(x, y);
        }
        return null;
    }


    public void printImpact(double[][] impacts){

        DecimalFormat df = new DecimalFormat("#.####");
        Graphics2D g = imageImpact.createGraphics();
        g.setFont(new Font("TimesRoman", Font.PLAIN, Constants.getSizeCell() - Constants.getWidthLine()));
        for(int j = 0; j < Constants.getY(); j++){
            for(int i = 0; i < Constants.getX() - j%2; i++) {
                Point point = coordsForImpact[i][j];
                g.drawString(df.format(impacts[i][j]),point.x, point.y );
            }
        }
        repaint();
    }

    public void clearImpact(){
        imageImpact = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        repaint();
    }

    public Point getPointByIndex(int i, int j){
        return centerCoords[i][j];
    }

    public int getColorDie() {
        return colorDie;
    }

    public int getColorLive() {
        return colorLive;
    }

    public boolean getStateCell(int x, int y){
        if(x >= 0 && x < Constants.getX() && y >=0 && y < Constants.getY()){
            Point point = centerCoords[x][y];
            int color = image.getRGB(point.x, point.y);
            return color == colorLive;
        }
        return false;
    }


    public HexagonField(){
        setField();
    }

    public void setField() {
        int k = (int) (sqrt(3) * Constants.getSizeCell());
        if(k%2 ==1) k++;
        width = (int) ((double)Constants.getX() * k + Constants.getWidthLine());
        height = (int) (((double)Constants.getY() * 3. / 2. + 1./2.)*Constants.getSizeCell())+Constants.getWidthLine();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        imageImpact = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        imageColor = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setPreferredSize(new Dimension(width, height));
        centerCoords = new Point[Constants.getX()][Constants.getY()];
        coordsForImpact = new Point[Constants.getX()][Constants.getY()];
        if(jScrollPane != null) {
            jScrollPane.setPreferredSize(new Dimension(width, height));
            jScrollPane.repaint();
        }
        drawImg();
        revalidate();
    }

    public void reFloodCell(MouseEvent e) {
        if(e.getX() > 0 && e.getX() < width && e.getY() > 0 && e.getY() < height){
            if(mode == ModeField.REPLACE) {
                int currentColor = image.getRGB(e.getX(), e.getY());
                if (currentColor == colorDie) {
                    Communicator.changeFlag = true;
                    floodFill(image, currentColor, colorLive, e.getX(), e.getY());
                }
            }else{
                int currentColor = image.getRGB(e.getX(), e.getY());
                if (currentColor == colorDie) {
                    Communicator.changeFlag = true;
                    floodFill(image, currentColor, colorLive, e.getX(), e.getY());
                }if(currentColor == colorLive){
                    Communicator.changeFlag = true;
                    floodFill(image, currentColor, colorDie, e.getX(), e.getY());
                }

            }
        }
    }

    private Point[] generateHexagon(int centerX, int centerY) {
        Point[] points = new Point[6];
        for(int i = 0; i < 6; i++){
            double angle_deg = 60 * i + 30;
            double angle_rad = PI / 180 * angle_deg;
            points[i] = new Point((int)(centerX + Constants.getSizeCell() * cos(angle_rad)), (int)( centerY + Constants.getSizeCell() * sin(angle_rad)));
        }
        return points;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0, 0, null );
        g.drawImage(imageImpact, 0, 0, null);
    }

    private void drawImg() {
        Graphics2D g2d = image.createGraphics();
        Graphics2D gImCol = imageColor.createGraphics();
        g2d.setColor(Color.BLACK);
        gImCol.setColor(Color.BLACK);
        for (int j = 0; j < Constants.getY(); j++) {
            for (int i = 0; i < Constants.getX() - j % 2; i++) {
                Point centerCoords = getCenterCoords(i, j);
                this.centerCoords[i][j] = centerCoords;
                int centerX = centerCoords.x;
                int centerY = centerCoords.y;
                Point[] points = generateHexagon(centerX, centerY);
                coordsForImpact[i][j] = new Point((int) (points[2].x + 1.5*Constants.getWidthLine()), (int) (points[2].y - 1.5 * Constants.getWidthLine()));
                paintOneHexagon(image, g2d, points);
                paintOneHexagon(imageColor ,gImCol, points);
                int currentColorI = image.getRGB(centerX, centerY);
                floodFill(image, currentColorI, colorDie, centerX, centerY);
                int currentColor = imageColor.getRGB(centerX, centerY);
                floodFill(imageColor, currentColor, new Color(j*100+i).getRGB(), centerX, centerY);

            }
        }
    }

    private Point getCenterCoords(int i, int j){
        int k = (int) (sqrt(3) * Constants.getSizeCell());
        if(k%2 == 1) k++;
        int centerX = (int) (Constants.getSizeCell() * sqrt(3)/2 + (double) (i * 2 + j % 2) * k /2 + Constants.getWidthLine()/2);
        int centerY = Constants.getSizeCell() + j * (Constants.getSizeCell() * 3 / 2 )+ Constants.getWidthLine()/2;
        return new Point(centerX, centerY);
    }

    private void paintOneHexagon(BufferedImage image, Graphics2D g, Point[] points) {
        for(int i = 0; i < 6; i++) {
            if(Constants.getWidthLine() > 1) {
                g.setStroke(new BasicStroke(Constants.getWidthLine()));
                g.drawLine(points[i].x, points[i].y, points[(i + 1) % 6].x, points[(i + 1) % 6].y);
            }else{
                BresenhamLine(image,points[i].x, points[i].y,points[(i+1)%6].x, points[(i+1)%6].y);
            }
        }
    }

    public void floodFill(BufferedImage image, int oldColor, int newColor, int x, int y){
        if(image.getRGB(x, y) == Color.black.getRGB()) return;
        Stack<Span> stack = new Stack<>();
        Span seedSpan = findSpan(image, x, y, oldColor);
        stack.push(seedSpan);
        while(!stack.empty()){
            Span pop = stack.pop();
            int xl = pop.getLeft().x;
            int xr = pop.getRight().x;
            int ys = pop. getRight().y;
            for(int i = xl; i < xr; i++){
                image.setRGB(i, ys, newColor);
                Span spanDown = findSpan(image, i, ys + 1, oldColor);
                if (stack.search(spanDown) == -1) {
                    stack.push(spanDown);
                }
                Span spanUp = findSpan(image,i, ys - 1, oldColor);
                if (stack.search(spanUp) == -1) {
                    stack.push(spanUp);
                }
            }
        }
        repaint();
    }

    private Span findSpan(BufferedImage image, int x, int y, int oldColor){
        int xl = x;
        int xr = x;
        while(xl > 0 && image.getRGB(xl, y) == oldColor){
            xl--;
        }
        while(xr < width && image.getRGB(xr, y) == oldColor){
            xr++;
        }
        return new Span(new Point(xl + 1,y), new Point(xr, y));
    }




    private void BresenhamLine(BufferedImage image, int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int dxSig = (int) signum(dx);
        int dySig = (int) signum(dy);
        if (dx < 0){
            dx = -dx;
        }
        if (dy < 0){
            dy = -dy;
        }
        int es;
        int el;
        int pdx;
        int pdy;
        if (dx > dy) {
            pdx = dxSig;
            pdy = 0;
            es = dy;
            el = dx;
        } else {
            pdx = 0;
            pdy = dySig;
            es = dx;
            el = dy;
        }
        int x = x0;
        int y = y0;
        int err = el/2;
        image.setRGB(x, y, Color.black.getRGB());

        for (int i = 0; i < el; i++) {
            err -= es;
            if (err < 0) {
                err += el;
                x += dxSig;
                y += dySig;
            }
            else {
                x += pdx;
                y += pdy;
            }
            image.setRGB (x, y, Color.black.getRGB());
        }
        repaint();
    }


    public void setScroll(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
}



