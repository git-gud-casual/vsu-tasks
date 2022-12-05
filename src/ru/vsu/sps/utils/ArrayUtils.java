package ru.vsu.sps.utils;

import java.util.Scanner;
import java.util.ArrayList;

public class ArrayUtils {
    public static int[][] getArray2FromString(String s) {
        Scanner scanner = new Scanner(s);

        ArrayList<int[]> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(getArrayFromString(scanner.nextLine()));
        }

        int[][] arr = new int[list.size()][];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static int[] getArrayFromString(String s) {
        Scanner scanner = new Scanner(s);

        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static boolean array2IsRectangular(int[][] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].length != arr[i + 1].length) {
                return false;
            }
        }
        return true;
    }

    public static String array2toString(int[][] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : arr) {
            for (int val : row) {
                stringBuilder.append(val);
                stringBuilder.append('\t');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}