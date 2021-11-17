package com.os.jssg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TestUtils {
    public static String generateRandomInputPath() throws IOException {
        Path filePath = Files.list(Paths.get("./src/main/resources/SherlockHolmesSelectedStories")).findFirst().get();

        if (Files.exists(filePath)) {
            return filePath.toString();
        }

        return null;
    }

    public static String generateConfigPath() throws IOException {
        Path filePath = Files.list(Paths.get("./src/main/resources")).filter(f->f.toString().endsWith(".json")).findFirst().get();

        if (Files.exists(filePath)) {
            return filePath.toString();
        }

        return null;
    }

    public static String generateOutputPath() throws IOException {
        Path folderPath = Paths.get("./src/main/resources/output");

        Files.createDirectories(folderPath);

        return folderPath.toString();
    }

    public static String generateMDFile() throws IOException {
        Path filePath = Files.list(Paths.get("./src/main/resources")).filter(f->f.toString().endsWith(".md")).findFirst().get();

        if (Files.exists(filePath)) {
            return filePath.toString();
        }

        return null;
    }


}
