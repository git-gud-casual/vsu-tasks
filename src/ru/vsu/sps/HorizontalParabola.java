package ru.vsu.sps;


public class HorizontalParabola {
    private final double x0;
    private final double y0;
    private final double a;

    public HorizontalParabola(double x0, double y0, double a) {
        this.x0 = x0;
        this.y0 = y0;
        this.a = a;
    }

    public boolean isPointInParabola(double x, double y) {
        return a >= 0 == x > a * Math.pow(y - y0, 2) + x0;
    }
}