package com.os.jssg;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.os.jssg.processor.HTMLProcessor;


import com.os.jssg.utils.JSonUtils;
import lombok.SneakyThrows;
import picocli.CommandLine;

import java.io.FileReader;


@CommandLine.Command(name = "jssg",
        description = "A tool that generates a full static HTML website based on raw text",
        version = "JSSG v.1.0",
        header = "Java Static Site Generator")
public class JssgApplication implements Runnable {
    @CommandLine.Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version and exit")
    Boolean versionHelpRequested = false;


    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Print usage help and exit")
    Boolean usageHelpRequested = false;

    // The path to the input source files/directories
    @CommandLine.Option(names = {"-i", "--input"},description = "Generate HTML website from file/folder")
    private String input;

    // The path to the output directory of the generated files
    @CommandLine.Option(names = {"-o", "--output"}, description = "Specified different output path")
    private String output = "dist";

    // The language of the generated html files
    @CommandLine.Option(names = {"-l", "--lang"}, description = "Specified the language to use when generating the lang attribute on the root <html> element")
    private String lang = "en-CA";

    // The path to the config file
    @CommandLine.Option(names = { "-c", "--config" }, description = "Specified the path to the config file")
    private String config;

    @SneakyThrows
    public void run() {
        if(versionHelpRequested){
            new CommandLine(this).printVersionHelp(System.err);
        }
        else if(usageHelpRequested){
            new CommandLine(this).usage(System.err);
        }
        // Check for `--config` flag should always be after `--version` and `--help` flag
        else if(config != null){
            // parse JSON
            JssgApplication args = new JSonUtils().parseJSon(config);

            // assign values so that later on they can be used in the next if block
            input = args.input;
            output = args.output;
            lang = args.lang;
        }
        if(input!=null){
            HTMLProcessor processor = new HTMLProcessor();
            processor.convertToHTML(input, output, lang);
        }
    }



    public static void main(String... args) {
        CommandLine.run(new JssgApplication(), System.err, args);
    }
}