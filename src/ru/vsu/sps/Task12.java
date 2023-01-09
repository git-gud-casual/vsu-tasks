package ru.vsu.sps;


import ru.vsu.sps.utils.ArrayUtils;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

// 15
public class Task12 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите список: ");
        List<Integer> list = ArrayUtils.getListFromConsole();
        System.out.println("Введите количество элементов размещения: ");
        int byK = scanner.nextInt();

        genAll(list, byK, (List<Integer> placing) -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < byK; i++) {
                stringBuilder.append(placing.get(i));
                stringBuilder.append(' ');
            }
            System.out.println(stringBuilder);
        });
    }

    public static void genAll(List<Integer> elements, int byK, Consumer<List<Integer>> callback) {
        if (byK == 0) {
            callback.accept(elements);
            return;
        }

        for (int i = 0; i < elements.size() - byK + 1; i++) {
            cycleMoveLeft(elements, byK - 1, i);
            genAll(elements, byK - 1, callback);
            cycleMoveRight(elements, byK - 1, i);
        }

    }

    public static void cycleMoveLeft(List<Integer> list, int beginIndex, int times) {
        while (times-- > 0) {
            Integer temp = list.get(beginIndex);
            for (int i = beginIndex + 1; i < list.size(); i++) {
                swap(list, i, i - 1);
            }
            list.set(list.size() - 1, temp);
        }
    }

    public static void cycleMoveRight(List<Integer> list, int beginIndex, int times) {
        while (times-- > 0) {
            Integer temp = list.get(list.size() - 1);
            for (int i = list.size() - 1; i > beginIndex; i--) {
                swap(list, i, i - 1);
            }
            list.set(beginIndex, temp);
        }
    }

    public static void swap(List<Integer> list, int index1, int index2) {
        Integer swapVal = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, swapVal);
    }
}