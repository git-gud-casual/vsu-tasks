package ru.vsu.sps.triangle;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    private final Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private Triangle(List<Point> pointsList) {
        this(pointsList.get(0), pointsList.get(1), pointsList.get(2));
    }

    static private final int intCountDefiningThreePoints = 6;
    static public Triangle fromIntegerList(List<Integer> list) {
        if (list.size() != intCountDefiningThreePoints) {
            throw new IllegalArgumentException("Integer list size should be 6");
        }

        ArrayList<Point> pointsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i += 2) {
            pointsList.add(new Point(list.get(i), list.get(i + 1)));
        }
        return new Triangle(pointsList);
    }

    public boolean isExists() {
        double abSide = a.getDistanceBetweenPoints(b);
        double bcSide = b.getDistanceBetweenPoints(c);
        double acSide = a.getDistanceBetweenPoints(c);

        return abSide < bcSide + acSide &&
                acSide < bcSide + abSide &&
                bcSide < acSide + abSide;
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

    public List<Integer> asList() {
        List<Integer> list = new ArrayList<>(a.asList());
        list.addAll(b.asList());
        list.addAll(c.asList());
        return list;
    }

}
