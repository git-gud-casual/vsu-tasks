package ru.vsu.sps;


import java.util.Locale;
import java.util.Scanner;

// 19
public class Task6 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите x, n, e: ");
        double x = scanner.nextDouble();
        int n = scanner.nextInt();
        double e = scanner.nextDouble();

        System.out.printf("Сумма n слагаемых: %.1f\n", getNSum(n, x));
        System.out.printf("Сумма слагаемых по модулю больших e: %.1f\n", getESum(e, x));
        System.out.printf("Сумма слагаемых по модулю больших e/10: %.1f\n", getESum(e / 10, x));
        System.out.printf("Значение функции: %.1f\n", calcValueOfFunc(x));
    }

    public static double getNSum(int n, double x) {
        double sum = 0, num = 1;
        for (int i = 1; i <= n; i++) {
            sum += num;
            num = getNextMember(i + 1, num, x);
        }
        return sum;
    }



    public static double getESum(double e, double x) {
        double sum = 0, num = 1;
        for (int i = 1; Math.abs(num) > e; i++) {
            sum += num;
            num = getNextMember(i + 1, num, x);
        }
        return sum;
    }

    public static double getNextMember(double n, double prevMember, double x) {
        return prevMember / (n - 1) * x * n;
    }

    public static double calcValueOfFunc(double x) {
        return 1 / Math.pow(1 - x, 2);
    }
}
