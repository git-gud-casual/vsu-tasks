package ru.vsu.sps;


import ru.vsu.sps.console.InputArgs;
import ru.vsu.sps.console.InputArgsException;
import ru.vsu.sps.text.Text;
import ru.vsu.sps.utils.FileUtils;

import java.io.IOException;
import java.util.Locale;

// 9
public class Task11 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        try {
            InputArgs cmdArgs = InputArgs.fromCmdArgs(args);
            Text textObject = new Text(FileUtils.readFromFile(cmdArgs.getInFile()));
            FileUtils.writeStringToFile(cmdArgs.getOutFile(), String.join("\n",
                                                                            textObject.getQuestionSentences()));

        } catch (InputArgsException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}