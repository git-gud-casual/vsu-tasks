package ru.vsu.sps;

import java.util.*;

// Task 25
public class Task7 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        tests();

        int[] arr = inputIntArray();
        System.out.printf("%s\nAnswer: %d\n", Arrays.toString(arr), solution(arr));
    }

    public static int[] inputIntArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите массив: ");
        return stringToIntArray(scanner.nextLine());
    }

    public static int[] stringToIntArray(String str) {
        Scanner scanner = new Scanner(str);
        List<Integer> list = new ArrayList<>();

        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static void printIntArray(int[] arr) {
        System.out.print("int[] {");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println('}');
    }

    public static void tests() {
        int[][] testData = {
                // Answer: 2
                {1, 4, 4, 0, 1, 2, 3, 5},
                // Answer: 0
                {6, 4, 8, 9, 12, 20, 8},
                // Answer: 8
                {1, 2, 2, 3, 4, 5, 2, 0},
                // Answer: 2
                {0, 0, 0, 2, 2, 4, 6, 8},
                // Answer: 0
                {},
                // Answer: 0
                {4, 6, 9, 15, 10, 14, 2, 1},
                // Answer: 0
                {2, 2, 4, 6, 188, 202, 14},
                // Answer: 14
                {2, 2, 2, 2, 2, 2, 2, 2, 3},
                // Answer: 28
                {0, 0, 3, 14, 14, 23},
                // Answer: 46
                {10, 101, 20, 26, 0}};
        for (int i = 0; i < testData.length; i++) {
            System.out.printf("Test %d\n", i + 1);
            System.out.println(Arrays.toString(testData[i]));
            System.out.printf("Answer: %d\n", solution(testData[i]));
            System.out.println("--------------");
        }
    }

    public static int solution(int[] arr) {
        int helpSum = 0, totalSum = 0;
        boolean wasPrime = false;

        for (int i : arr) {
            if (isPrime(Math.abs(i))) {
                if (wasPrime) {
                    totalSum += helpSum;
                    helpSum = i == 2? 2 : 0;
                }
                else {
                    wasPrime = true;
                }
            }
            else if (wasPrime && i % 2 == 0) {
                helpSum += i;
            }
        }
        return totalSum;
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return num != 1;
    }
}
