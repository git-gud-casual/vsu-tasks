package ru.vsu.sps.triangle;

public class Triangle {
    private final Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getSquare() {
        double abSide = a.getDistanceBetweenPoints(b);
        double bcSide = b.getDistanceBetweenPoints(c);
        double acSide = a.getDistanceBetweenPoints(c);
        double halfPerimeter = (abSide + bcSide + acSide) / 2;

        return Math.sqrt(halfPerimeter *
                (halfPerimeter - abSide) *
                (halfPerimeter - bcSide) *
                (halfPerimeter - acSide));
    }

}
