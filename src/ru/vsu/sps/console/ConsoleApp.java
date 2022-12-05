package ru.vsu.sps.console;

import ru.vsu.sps.utils.ArrayUtils;
import ru.vsu.sps.utils.FileUtils;
import ru.vsu.sps.utils.SelectionSort;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;


public class ConsoleApp {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        try {
            InputArgs cmdArgs = InputArgs.fromCmdArgs(args);

            int[][] arr = ArrayUtils.getArray2FromString(FileUtils.readFromFile(cmdArgs.inFile));

            if (!ArrayUtils.array2IsRectangular(arr)) {
                throw new AssertionError("Array is not rectangular");
            }

            SelectionSort.sortColumns(arr);

            FileUtils.writeStringToFile(cmdArgs.outFile, ArrayUtils.array2toString(arr));
        } catch (InputArgsException|IOException|AssertionError e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}