# Java Static Site Generator (JSSG)

A simple Static Site Generator (SSG) for generating a complete HTML website from raw data and files, without having to author any HTML by hand.

## Built with
* [Picocli 4.6.1](https://picocli.info/)
* [Java 11](https://www.java.com/en/)
* [Maven](https://maven.apache.org/)

## Getting Started
### Prerequisites
* [Java 11](https://www.java.com/en/)
* [IntelliJ](https://www.jetbrains.com/idea/download/)
* [Maven 3.8.1](https://maven.apache.org/download.cgi)
* [Windows 10](https://www.microsoft.com/en-ca/software-download/windows10)


### Executing program
* clone this github

```
git clone https://github.com/trangntt-016/static-site-generator.git
```
* Install jssg

```
cd ./static-site-generator
```

Config the .bat file
```
Right click on jssg.bat -> edit -> change D:\SchoolWork\OSD600\static-site-generator\target\jssg-0.0.1-SNAPSHOT.jar to your absolute path where the jssg-0.0.1-SNAPSHOT.jar locates 
Save
```

Config the system environment
```
Start -> Edit system environment variables -> Environment variables -> User variables: Path -> New -> (Absolute path where your jssg.bat file locates)-> ok

Then, look at the 2nd block: system variables: Path -> New -> (Absolute path where your jssg.bat file locates)-> ok

Ok -> Apply -> Ok
```

* Run jssg
```
Open Windows Terminal -> jssg -h
```

## Features
* Display the version of the tool and a list of command lines by using (--h, --v, --help, --v)


* Allow the user to specify an input file or folder to be processed, using --input or -i . Then it generates one .html output file for each input file and the filename is based on the title of the story’s header. For example: jssg -i '.\src\main\resources\Sherlock Holmes Selected Stories\'


* jssg places all output into a ./dist folder by default. Each time the tool is run, an existing dist folder should first be removed so that dist always contains the last output.


* If --output or -o werent’s specified, dist will be used. If the user specifies a different output path (--output or -o), jssg will use that, it also prints an error if the specified output path is not a valid directory.


* If the user specifies a folder for the input, Jssg automatically generates an index.html file, which has relative links to each of the generated HTML files.


## Demo

https://trangntt-016.github.io/static-site-generator/

Command line's features: https://tracy016.medium.com/osd600-build-a-static-site-generator-with-picocli-c34bfb8a33e4

## License

This project is licensed under the [MIT] License - see the LICENSE.md file for details

## Author
#### Trang Nguyen (Tracy)

## Acknowledgment
* [Picocli Document](https://picocli.info/)
