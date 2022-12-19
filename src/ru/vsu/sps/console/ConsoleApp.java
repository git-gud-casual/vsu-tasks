package ru.vsu.sps.console;

import ru.vsu.sps.utils.ArrayUtils;
import ru.vsu.sps.utils.FileUtils;
import ru.vsu.sps.utils.SelectionSort;
import ru.vsu.sps.utils.TaskLogic;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;


public class ConsoleApp {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        try {
            InputArgs cmdArgs = InputArgs.fromCmdArgs(args);

            List<Integer> list = ArrayUtils.getListFromString(FileUtils.readFromFile(cmdArgs.inFile));
            List<Integer> resultList = TaskLogic.createNewList(list);

            FileUtils.writeStringToFile(cmdArgs.outFile, resultList.toString());
        } catch (InputArgsException|IOException|AssertionError e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}