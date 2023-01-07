package ru.vsu.sps.console;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputArgs {
    final Path inFile, outFile;

    public InputArgs(String inFile, String outFile) throws InputArgsException {
        this.inFile = Paths.get(inFile);
        this.outFile = Paths.get(outFile);

        if (!Files.exists(this.inFile)) {
            throw new InputArgsException("Input file does not exist");
        }
    }

    public static InputArgs fromCmdArgs(String[] args) throws InputArgsException {
        String inFile = null, outFile = null;
        int i = 0;
        while (i < args.length) {
            if (args[i].startsWith("--")) {
                if (args[i].startsWith("--input_file=")) {
                    inFile = args[i].split("=")[1];
                }
                else if (args[i].startsWith("--output_file=")) {
                    outFile = args[i].split("=")[1];
                }
            }
            else if (args[i].startsWith("-")) {
                if (i + 1 >= args.length) {
                    break;
                }
                else if (args[i].equals("-i")) {
                        inFile = args[++i];
                }
                else if (args[i].equals("-o")) {
                        outFile = args[++i];
                }
            }
            else {
                if (inFile == null) {
                    inFile = args[i];
                }
                else if (outFile == null) {
                    outFile = args[i];
                }
            }
            i++;
        }

        if (inFile == null || outFile == null) {
            throw new InputArgsException("Invalid params");
        }
        else {
            return new InputArgs(inFile, outFile);
        }
    }

    public Path getInFile() {
        return inFile;
    }

    public Path getOutFile() {
        return outFile;
    }
}