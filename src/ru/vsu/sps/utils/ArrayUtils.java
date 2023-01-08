package ru.vsu.sps.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayUtils {
    public static List<List<Integer>> getList2FromString(String s) {
        Scanner scanner = new Scanner(s);

        ArrayList<List<Integer>> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(getListFromString(scanner.nextLine()));
        }

        return list;
    }

    public static List<Integer> getListFromString(String s) {
        Scanner scanner = new Scanner(s);

        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        return list;
    }

    public static String getStringFromList(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer val : list) {
            stringBuilder.append(val);
            stringBuilder.append('\t');
        }
        return stringBuilder.toString();
    }

    public static String getStringFromList2(List<List<Integer>> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (List<Integer> val : list) {
            stringBuilder.append(getStringFromList(val));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
