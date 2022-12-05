package ru.vsu.sps.utils;

public class SelectionSort {
    public static void sortColumns(int[][] array) {
        for (int i = 0; i < array[0].length - 1; i++) {
            int min_index = i;

            for (int j = i + 1; j < array[0].length; j++) {
                if (compareColumns(array, i, j) > 0) {
                    min_index = j;
                }
            }

            if (min_index != i) {
                int temp;
                for (int rowIndex = 0; rowIndex < array.length; rowIndex++) {
                    temp = array[rowIndex][i];
                    array[rowIndex][i] = array[rowIndex][min_index];
                    array[rowIndex][min_index] = temp;
                }
            }
        }
    }

    private static int compareColumns(int[][] array, int index1, int index2) {
        for (int[] row : array) {
            if (row[index1] != row[index2]) {
                return row[index1] - row[index2];
            }
        }
        return 0;
    }
}
