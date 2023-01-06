package logic;

import ru.vsu.sps.triangle.Triangle;

import java.util.Comparator;
import java.util.List;

public class TaskComparator implements Comparator<List<Integer>> {
    @Override
    public int compare(List<Integer> o1, List<Integer> o2) {
        double o1Square = Triangle.getTriangleFromIntegerList(o1).getSquare();
        double o2Square = Triangle.getTriangleFromIntegerList(o2).getSquare();
        if (o1Square - o2Square < 1e-6) {
            return 0;
        }
        else if (o1Square > o2Square) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
