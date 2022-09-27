package ru.vsu.sps;


import java.util.Locale;
import java.util.Scanner;

// 29
public class Task4 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        System.out.println(getNCountNum(n));

    }

    public static int getNCountNum(int n) {
        int num = -1;

        while (n > 0) {
            num += 1;
            if (isNumDecreases(num) || isNumIncreases(num)) {
                n -= 1;
            }
        }
        return num;
    }

    public static boolean isNumIncreases(int num) {
        int lastNum;

        while (num > 9) {
            lastNum = num % 10;
            num /= 10;
            if (lastNum < num % 10) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumDecreases(int num) {
        int lastNum;

        while (num > 9) {
            lastNum = num % 10;
            num /= 10;
            if (lastNum > num % 10) {
                return false;
            }
        }

        return true;
    }
}
