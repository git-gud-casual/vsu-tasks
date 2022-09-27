package ru.vsu.sps;


import java.util.Locale;
import java.util.Scanner;

// 29
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        System.out.println(getNCountNum(n));
    }

    public static int getNCountNum(int n) {
        int num = -1;

        while (n > 0) {
            num += 1;
            if (isNumIncreasesOrDecreases(num)) {
                n -= 1;
            }
        }
        return num;
    }

    public static boolean isNumIncreasesOrDecreases(int num) {
        int difference, lastDifference = 0;

        for (int i = num; i > 9; i /= 10) {
            difference = i % 10 - i / 10 % 10;

            if (difference == 0 || (difference < 0 && lastDifference > 0)
                                || (difference > 0 && lastDifference < 0)) {
                return false;
            }
            lastDifference = difference;
        }

        return true;
    }
}
