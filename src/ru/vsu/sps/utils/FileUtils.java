package ru.vsu.sps.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static String readFromFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }

    public static void writeStringToFile(Path filePath, String s) throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        Files.writeString(filePath, s);
    }
}
