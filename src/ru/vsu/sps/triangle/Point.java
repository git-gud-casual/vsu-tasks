package ru.vsu.sps.triangle;

public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y =y;
    }

    public double getDistanceBetweenPoints(Point anotherPoint) {
        return Math.sqrt(Math.pow(x - anotherPoint.getX(), 2) + Math.pow(y - anotherPoint.getY(), 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
