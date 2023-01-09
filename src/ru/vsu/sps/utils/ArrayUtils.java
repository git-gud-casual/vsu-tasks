package ru.vsu.sps.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayUtils {
    public static List<Integer> getListFromString(String s) {
        Scanner scanner = new Scanner(s);

        List<Integer> list = new ArrayList<>();

        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    public static List<Integer> getListFromConsole() {
        Scanner scanner = new Scanner(System.in);

        return getListFromString(scanner.nextLine());
    }
}