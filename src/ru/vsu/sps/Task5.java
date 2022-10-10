package ru.vsu.sps;


import java.util.Locale;
import java.util.Scanner;

// 28 Task 5
public class Task5 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        /*
          0 1 2 3 4 5 6 7 8 9
        0       * - - *
        1       |     |
        2       |     |
        3 * - - *     * - - *
        4 |                 |
        5 |                 |
        6 * - - *     * - - *
        7       |     |
        8       |     |
        9       * - - *

        */

        System.out.print("Введите s: ");
        int s = scanner.nextInt();
        printFigure(s);
    }

    public static void printFigure(int size) {
        int linesCount = (size - 4) / 3;

        for (int i = 0; i < size; i++) {
            if (i % (linesCount + 1) == 0) {
                if (i / (linesCount + 1) % 3 == 0) {

                    for (int j = 0; j < size - linesCount - 1; j++) {
                        if (j / (linesCount + 1) >= 1) {
                            if (j % (linesCount + 1) == 0) {
                                System.out.print('*');
                            }
                            else {
                                System.out.print('-');
                            }
                        }
                        else {
                            System.out.print(' ');
                        }
                    }
                }
                else {
                    for (int j = 0; j < size; j++) {
                        if (j % (linesCount + 1) == 0) {
                            System.out.print('*');
                        }
                        else if (j / (linesCount + 1) % 2 == 0) {
                            System.out.print('-');
                        }
                        else {
                            System.out.print(' ');
                        }
                    }
                }
            }
            else {
                if (i / (linesCount + 1) % 2 == 0) {
                    for (int j = 0; j < size - linesCount - 1; j++) {
                        if (j % (linesCount + 1) == 0 && j > 0) {
                            System.out.print('|');
                        }
                        else {
                            System.out.print(' ');
                        }
                    }
                }
                else {
                    for (int j = 0; j < size; j++) {
                        if (j == 0 || j == size - 1) {
                            System.out.print('|');
                        }
                        else {
                            System.out.print(' ');
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}