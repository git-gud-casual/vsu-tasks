package ru.vsu.sps.triangle;

import java.util.Comparator;

public class TriangleComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle o1, Triangle o2) {
        return o1.compare(o2);
    }
}
