package ru.vsu.sps.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FileUtils {
    public static String readFromFile(Path filePath) throws IOException {
        return String.join("\n", Files.readAllLines(filePath));
    }

    public static void writeStringToFile(Path filePath, String s) throws IOException {
        if (Files.notExists(filePath)) {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
        Files.write(filePath, Arrays.asList(s.split("\n")));
    }
}