package ru.vsu.sps.console;

import ru.vsu.sps.triangle.Triangle;
import ru.vsu.sps.triangle.TriangleComparator;
import ru.vsu.sps.utils.ArrayUtils;
import ru.vsu.sps.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ConsoleApp {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        try {
            InputArgs cmdArgs = InputArgs.fromCmdArgs(args);

            List<List<Integer>> list = ArrayUtils.getList2FromString(FileUtils.readFromFile(cmdArgs.inFile));
            List<Triangle> trianglesList = new ArrayList<>();

            for (List<Integer> val : list) {
                Triangle triangle = Triangle.fromIntegerList(val);
                if (triangle.isExists()) {
                    trianglesList.add(triangle);
                }
            }
            trianglesList.sort(new TriangleComparator());

            list.clear();
            for (Triangle triangle : trianglesList) {
                list.add(triangle.asList());
            }

            FileUtils.writeStringToFile(cmdArgs.outFile, ArrayUtils.getStringFromList2(list));
        } catch (InputArgsException|IOException|IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}