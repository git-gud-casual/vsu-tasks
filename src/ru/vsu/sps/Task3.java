package ru.vsu.sps;


import java.util.Scanner;

// 57 задача из Task_03
public class Task3 {
    private static final Circle circle = new Circle(-4, -6, 4);
    private static final HorizontalParabola bigHorizontalParabola = new HorizontalParabola(2, 2, -1. / 8),
    lowHorizontalParabola = new HorizontalParabola(2,2, -1);
    private static final VerticalParabola upVerticalParabola = new VerticalParabola(6, 3, 1. / 9),
    downVerticalParabola = new VerticalParabola(1, -1, -1. / 4);

    public static void main(String[] args) {
        /*      TESTS
            (6.0, 4.0) - WHITE      | OK
            (8.0, 0.0) - BLUE       | OK
            (2.0, -5.0) - GREEN     | OK
            (-1.0, -2.5) - YELLOW   | OK
            (-2.0, -5.0) - BLUE     | OK
            (-5.0, -6.0) - YELLOW   | OK
            (-4.0, -3.0) - GRAY     | OK
            (-10.0, -10.0) - WHITE  | OK
            (0.0, 0.0) - ORANGE     | OK
            (-4.0, 2.0) - BLUE      | OK
         */
        System.out.println("Тесты");
        printColorForPoint(6, 4);
        printColorForPoint(8, 0);
        printColorForPoint(2, -5);
        printColorForPoint(-1, -2.5);
        printColorForPoint(-2, -5);
        printColorForPoint(-5, -6);
        printColorForPoint(-4, -3);
        printColorForPoint(-10, -10);
        printColorForPoint(0, 0);
        printColorForPoint(-4, 2);
        System.out.println("Конец тестов");
        /* END OF TESTS */

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nВведите x, y: ");
        printColorForPoint(scanner.nextDouble(), scanner.nextDouble());
    }

    public static void printColorForPoint(double x, double y) {
        System.out.printf("(%.1f, %.1f) - ", x, y);
        switch (getColor(x, y)) {
            case BLUE -> System.out.println("BLUE");
            case GRAY -> System.out.println("GRAY");
            case GREEN -> System.out.println("GREEN");
            case WHITE -> System.out.println("WHITE");
            case ORANGE -> System.out.println("ORANGE");
            case YELLOW -> System.out.println("YELLOW");
        }
    }

    public static SimpleColor getColor(double x, double y) {
        if (upVerticalParabola.isPointInParabola(x, y)) {
            return SimpleColor.WHITE;
        }
        else if (downVerticalParabola.isPointInParabola(x, y)) {
            if (circle.isInCircle(x, y)) {
                return SimpleColor.BLUE;
            }
            else if (bigHorizontalParabola.isPointInParabola(x, y)) {
                return SimpleColor.YELLOW;
            }
            else {
                return SimpleColor.GREEN;
            }
        }
        else if (bigHorizontalParabola.isPointInParabola(x, y)) {
            if (!lowHorizontalParabola.isPointInParabola(x, y) && y <= 2) {
                if (downVerticalParabola.isPointInParabola(x, y)) {
                    return SimpleColor.YELLOW;
                }
                else if (circle.isInCircle(x, y)) {
                    return SimpleColor.GRAY;
                }
                else {
                    return SimpleColor.ORANGE;
                }
            }
            else {
                return SimpleColor.BLUE;
            }
        }
        else if (circle.isInCircle(x, y)) {
            return SimpleColor.YELLOW;
        }
        else if (x < 0 && y < 0) {
            return SimpleColor.WHITE;
        }
        else {
            return SimpleColor.BLUE;
        }
    }


}
