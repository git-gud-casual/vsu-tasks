package ru.vsu.sps.triangle;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistanceBetweenPoints(Point anotherPoint) {
        return Math.sqrt(Math.pow(x - anotherPoint.getX(), 2) + Math.pow(y - anotherPoint.getY(), 2));
    }

    public List<Integer> asList() {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        return list;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
