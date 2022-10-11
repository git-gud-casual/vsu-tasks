package ru.vsu.sps;

import java.util.Locale;
import java.util.Scanner;

// 28 Task 5
public class Task5 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите s: ");
        int s = scanner.nextInt();
        printFigure(s);
    }

    public static void printFigure(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(getChar(j, i, size));
            }
            System.out.println();
        }
    }

    public static char getChar(int x, int y, int size) {
        int distanceBetweenStars = (size - 4) / 3 + 1;

        if (y % distanceBetweenStars == 0) {
            if (y / distanceBetweenStars % 3 == 0) {
                if (x >= distanceBetweenStars && x < size - distanceBetweenStars) {
                    if (x % distanceBetweenStars == 0) {
                        return '*';
                    } else {
                        return '-';
                    }
                }
            }
            else {
                if (x % distanceBetweenStars == 0) {
                    return '*';
                } else if (x < distanceBetweenStars || x > distanceBetweenStars * 2) {
                    return '-';
                }
            }
        }
        else if ((y / distanceBetweenStars % 2 == 0 && (x == distanceBetweenStars || x == distanceBetweenStars * 2)) ||
                ((x == 0 || x == size - 1) && y / distanceBetweenStars % 2 != 0)) {
            return '|';
        }
        return ' ';
    }
}
