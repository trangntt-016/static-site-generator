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

```
Right click on jssg.bat -> edit -> change D:\SchoolWork\OSD600\temp\static-site-generator\target\jssg-0.0.1-SNAPSHOT.jar to your absolute path where the jssg-0.0.1-SNAPSHOT.jar locates 
Save
```

* Install jssg to your path

```
Start -> Edit the system environment variables -> Environment Variables -> Path -> Edit -> New -> add your absolute path where jssg.bat locates -> OK
```

* Test jssg
```
Open Windows Terminal -> jssg -v
```

## Features
* Display the version of the tool and --help to show a list of command lines


* Allow the user to specify an input file or folder to be processed, using --input or -i . Then it generates one .html output file for each input file and the filename is based on the title of the story’s header.


* jssg places all output into a ./dist folder by default. Each time the tool is run, an existing dist folder should first be removed so that dist always contains the last output. For example, if I run --input on a file “Silver Blaze.txt”


* If --output or -o werent’s specified, dist will be used. If the user specifies a different output path (--output or -o), jssg will use that, it also prints an error if the specified output path is not a valid directory.


## License

This project is licensed under the [MIT] License - see the LICENSE.md file for details

## Acknowledgment
* [Picocli Document](https://picocli.info/)
