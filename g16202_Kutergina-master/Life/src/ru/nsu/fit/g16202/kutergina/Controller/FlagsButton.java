package ru.nsu.fit.g16202.kutergina.Controller;

public class FlagsButton {
    public static void setRun() {
        run = !run;
    }

    public static void setImpact() {
        impact = !impact;
    }

    public static boolean isRun() {
        return run;
    }

    public static boolean isImpact() {
        return impact;
    }

    private static boolean run = false;

    private static boolean impact = false;


}
