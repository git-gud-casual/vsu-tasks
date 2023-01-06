package ru.vsu.sps.console;

import logic.TaskComparator;
import ru.vsu.sps.utils.ArrayUtils;
import ru.vsu.sps.utils.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ConsoleApp {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        try {
            InputArgs cmdArgs = InputArgs.fromCmdArgs(args);

            List<List<Integer>> list = ArrayUtils.getList2FromString(FileUtils.readFromFile(cmdArgs.inFile));
            list.sort(new TaskComparator());

            FileUtils.writeStringToFile(cmdArgs.outFile, ArrayUtils.getStringFromList2(list));
        } catch (InputArgsException|IOException|IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}