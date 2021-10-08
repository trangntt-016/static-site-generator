package com.os.jssg;

import com.os.jssg.processor.HTMLProcessor;
import lombok.SneakyThrows;
import picocli.CommandLine;

import java.util.List;


@CommandLine.Command(name = "jssg",
        description = "A tool that generates a full static HTML website based on raw text",
        version = "JSSG v.1.0",
        header = "Java Static Site Generator")
class JssgApplication implements Runnable {
    @CommandLine.Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version and exit")
    Boolean versionHelpRequested = false;


    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Print usage help and exit")
    Boolean usageHelpRequested = false;

    @CommandLine.Option(names = {"-i", "--input"},description = "Generate HTML website from file/folder")
    private String filePath;

    @CommandLine.Option(names = {"-o", "--output"}, defaultValue = "dist", description = "Specified different output path")
    private String outputPath;

    @CommandLine.Option(names = {"-l", "--lang"}, defaultValue = "en-CA", description = "Specified the language to use when generating the lang attribute on the root <html> element")
    private String language;

    @SneakyThrows
    public void run() {
        if(versionHelpRequested){
            new CommandLine(this).printVersionHelp(System.err);
        }
        else if(usageHelpRequested){
            new CommandLine(this).usage(System.err);
        }
        else if(filePath!=null){
            HTMLProcessor processor = new HTMLProcessor();
            processor.convertToHTML(filePath, outputPath, language);
        }
    }

    public static void main(String... args) {
        CommandLine.run(new JssgApplication(), System.err, args);
    }
}