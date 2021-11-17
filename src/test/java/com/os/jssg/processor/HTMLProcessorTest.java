package com.os.jssg.processor;

import com.os.jssg.utils.HTMLUtils;
import org.junit.jupiter.api.Test;
import com.os.jssg.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HTMLProcessorTest {
    private HTMLProcessor processor = new HTMLProcessor();
    private HTMLUtils utils = new HTMLUtils();

    @Test
    void shouldCreateNewFileFromValidPathAndValidOutputPathAndValidLanguage() throws IOException {
        String inputPath = TestUtils.generateRandomInputPath();

        String outputPath = TestUtils.generateOutputPath();

        String language = "en";

        processor.convertToHTML(inputPath, outputPath, language);
        
        utils.cleanDirectory(Paths.get(outputPath));
    }

    @Test
    void shouldCreateNoFilesFromInValidPathAndValidOutputPathAndValidLanguage() throws IOException {
        String inputPath = "invalid filepath";

        String outputPath = TestUtils.generateOutputPath();

        String language = "en";

        // WHEN
        processor.convertToHTML(inputPath, outputPath, language);

        // THEN
        List<Path> filesPath= Files.list(Paths.get("./src/main/resources/output")).collect(Collectors.toList());

        assertThat(filesPath).hasSize(0);

        // clean directory after the test
        utils.cleanDirectory(Paths.get(outputPath));
    }

    @Test
    void shouldCreateNoFilesFromValidPathAndInvalidOutputPathAndValidLanguage() throws IOException {
        String inputPath = TestUtils.generateRandomInputPath();

        String outputPath = "invalid output";

        String language = "en";

        // WHEN
        processor.convertToHTML(inputPath, outputPath, language);

        // THEN
        List<Path> filesPath= Files.list(Paths.get("./src/main/resources/output")).collect(Collectors.toList());

        assertThat(filesPath).hasSize(0);

        // clean directory after the test
        utils.cleanDirectory(Paths.get(outputPath));
    }

    @Test
    void shouldCreateNewFileFromMdPathAndValidOutputPathAndValidLanguage() throws IOException{
        String inputPath = TestUtils.generateMDFile();

        String outputPath = TestUtils.generateOutputPath();

        String language = "en";

        processor.convertToHTML(inputPath, outputPath, language);

        utils.cleanDirectory(Paths.get(outputPath));
    }

    @Test
    void shouldCreateNewFilesFromFolderPathAndValidOutputPathAndValidLanguage() throws IOException{
        String inputPath = TestUtils.generateFolder();

        String outputPath = TestUtils.generateOutputPath();

        String language = "en";

        processor.convertToHTML(inputPath, outputPath, language);

        utils.cleanDirectory(Paths.get(outputPath));
    }

    @Test
    void shouldCreateNoFilesFromEmtyFolderPathAndValidOutputPathAndValidLanguage() throws IOException{
        String inputPath = TestUtils.generateOutputPath();

        String outputPath = TestUtils.generateOutputPath();

        String language = "en";

        processor.convertToHTML(inputPath, outputPath, language);

        utils.cleanDirectory(Paths.get(outputPath));
    }

}
