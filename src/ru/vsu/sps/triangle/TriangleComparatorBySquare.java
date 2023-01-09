package ru.vsu.sps.triangle;

import java.util.Comparator;

public class TriangleComparatorBySquare implements Comparator<Triangle> {
    @Override
    public int compare(Triangle o1, Triangle o2) {
        double squaresDelta = o1.getSquare() - o2.getSquare();

        if (Math.abs(squaresDelta) < 1e-6) {
            return 0;
        }
        else if (squaresDelta > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
