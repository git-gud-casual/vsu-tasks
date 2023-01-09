package ru.vsu.sps;


import ru.vsu.sps.utils.ArrayUtils;

import java.util.*;
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

        genAll(list.size(), byK, null, (List<Integer> placingIndexes) -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer index : placingIndexes) {
                stringBuilder.append(list.get(index));
                stringBuilder.append(' ');
            }
            System.out.println(stringBuilder);
        });
    }

    public static void genAll(int size, int byK, List<Integer> usedIndexes, Consumer<List<Integer>> callback) {
        if (byK == 0) {
            callback.accept(usedIndexes);
            return;
        }
        if (usedIndexes == null) {
            usedIndexes = new ArrayList<>();
        }

        for (int i = 0; i < size; i++) {
            if (!usedIndexes.contains(i)) {
                usedIndexes.add(i);
                genAll(size, byK - 1, usedIndexes, callback);
                usedIndexes.remove(usedIndexes.size() - 1);
            }
        }
    }
}