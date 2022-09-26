package ru.vsu.sps;


public class VerticalParabola {
    private final double x0;
    private final double y0;
    private final double a;

    public VerticalParabola(double x0, double y0, double a) {
        this.x0 = x0;
        this.y0 = y0;
        this.a = a;
    }

    public boolean isPointInParabola(double x, double y) {
        return a >= 0 == y > a * Math.pow(x - x0, 2) + y0;

    }
}
