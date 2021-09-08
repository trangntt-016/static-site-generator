package com.os.jssg;

import com.os.jssg.utils.HTMLProcessor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


@CommandLine.Command(name = "jssg",
        description = "A tool that generates a full static HTML website based on raw text",
        version = "JSSG v.1.0",
        header = "%n@|green Java Static Site Generator|@")
class JssgApplication implements Runnable {
    @CommandLine.Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version and exit")
    Boolean versionHelpRequested = false;


    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Print usage help and exit")
    Boolean usageHelpRequested = false;

    @CommandLine.Option(names = {"-i", "--input"}, description = "Generate HTML website from file/folder")
    private String filePath;

    public void run() {
        if(versionHelpRequested){
            new CommandLine(this).printVersionHelp(System.err);
        }
        else if(usageHelpRequested){
            new CommandLine(this).usage(System.err);
        }
        else if(filePath!=null){
            HTMLProcessor processor = new HTMLProcessor();
            processor.convertToHTML(filePath);
        }
    }

    public static void main(String... args) {
        CommandLine.run(new JssgApplication(), System.err, args);
    }
}