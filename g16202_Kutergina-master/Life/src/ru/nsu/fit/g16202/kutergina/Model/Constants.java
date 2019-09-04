package ru.nsu.fit.g16202.kutergina.Model;

public class Constants {
    public static double getLiveBegin() {
        return LIVE_BEGIN;
    }

    public static void setLiveBegin(double liveBegin) {
        LIVE_BEGIN = liveBegin;
    }

    public static double getLiveEnd() {
        return LIVE_END;
    }

    public static void setLiveEnd(double liveEnd) {
        LIVE_END = liveEnd;
    }

    public static double getBirthBegin() {
        return BIRTH_BEGIN;
    }

    public static void setBirthBegin(double birthBegin) {
        BIRTH_BEGIN = birthBegin;
    }

    public static double getBirthEnd() {
        return BIRTH_END;
    }

    public static void setBirthEnd(double birthEnd) {
        BIRTH_END = birthEnd;
    }

    public static double getFstImpact() {
        return FST_IMPACT;
    }

    public static void setFstImpact(double fstImpact) {
        FST_IMPACT = fstImpact;
    }

    public static double getSndImpact() {
        return SND_IMPACT;
    }

    public static void setSndImpact(double sndImpact) {
        SND_IMPACT = sndImpact;
    }

    public static int getX() {
        return X;
    }

    public static void setX(int x) throws Exception {
        if (x < minX || x > maxX) {
            throw new Exception();
        }
        X = x;
    }

    public static int getY() {
        return Y;
    }

    public static void setY(int y) throws Exception {
        if (y < minY || y > maxY) {
            throw new Exception();
        }
        Y = y;
    }

    public static int getWidthLine() {
        return widthLine;
    }

    public static void setWidthLine(int widthLine) throws Exception {
        if (widthLine < minWidthLine || widthLine > maxWidthLine) {
            throw new Exception();
        }
        Constants.widthLine = widthLine;
    }

    public static int getSizeCell() {
        return sizeCell;
    }

    public static void setSizeCell(int sizeCell) throws Exception {
        if (sizeCell < minSizeCell || sizeCell > maxSizeCell) {
            throw new Exception();
        }
        Constants.sizeCell = sizeCell;
    }


    public static void setAll(double liveBegin, double liveEnd,
                              double birthBegin, double birthEnd,
                              double firstImpact, double secondImpact,
                              int x, int y,
                              int widthLine, int sizeCell) throws Exception {
        setLiveBegin(liveBegin);
        setLiveEnd(liveEnd);
        setBirthBegin(birthBegin);
        setBirthEnd(birthEnd);
        setFstImpact(firstImpact);
        setSndImpact(secondImpact);
        setX(x);
        setY(y);
        setWidthLine(widthLine);
        setSizeCell(sizeCell);
    }
    private static double LIVE_BEGIN = 2.0;
    private static double LIVE_END = 3.3;
    private static double BIRTH_BEGIN = 2.3;
    private static double BIRTH_END = 2.9;
    private static double FST_IMPACT = 1.0;
    private static double SND_IMPACT = 0.3;
    private static int X = 20;
    private static int Y = 20;
    private static int widthLine = 3;

    private static int sizeCell = 15;
    public final static int minWidthLine = 1;
    public final static int maxWidthLine = 10;
    public final static int minSizeCell = 3;
    public final static int maxSizeCell = 50;
    public final static int minX = 1;
    public final static int minY = 1;
    public final static int maxX = 100;
    public final static int maxY = 100;






}
