package ru.vsu.sps.utils;

import java.util.ArrayList;
import java.util.List;

public class TaskLogic {
    public static List<Integer> createNewList(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        sort(sortedList);

        List<Integer> resultList, helpList;

        resultList = new ArrayList<>();
        helpList = new ArrayList<>();
        for (Integer val : sortedList) {
            if (helpList.size() == 0 ||
                    helpList.get(helpList.size() - 1) + 1 == val) {
                helpList.add(val);
            } else {
                if (resultList.size() < helpList.size()) {
                    resultList = new ArrayList<>(helpList);
                    helpList = new ArrayList<>();
                    helpList.add(val);
                }
            }
        }
        return resultList;
    }

    private static void sort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    Integer swap = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, swap);
                }
            }
        }
    }
}
