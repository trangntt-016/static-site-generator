Contributing
============

Thank you for your interest in contributing to JSSG!

We appreciate your effort and to make sure that your pull request is easy to review, we ask you to note the following
guidelines including legal contributor agreement:

* Use JDK 11, [IntelliJ](https://www.jetbrains.com/idea/download/), [Maven 3.8.1](https://maven.apache.org/download.cgi)
  , [Picocli 4.6.1](https://picocli.info/), [JUnit5](https://junit.org/junit5/) or newer to build the project

* To use Formatter and Eslint in this project, please read this
  detailed [instruction](https://tracy016.medium.com/osd600-adding-static-analysis-toolings-b8488bf239da)

* To enable "auto-test", enable "Toggle auto-test" in run dialog box
![image](https://intellij-support.jetbrains.com/hc/user_images/35Aia3Dk3aHoTBe1gB8WIw.png)
  This will start auto testing. Although this works fine, it takes time to build the project even when my project is tiny so for larger projects it will certainly take very long time to complete the build and execute tests.

* This project has some basic unit tests and still not covers all.
  Tests should conform to adequate practices in order to execute as expected, please read [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/).

  Tests are required at least for the following components:

  * HTMLProcessors
  * HTMLUtils
  * JSonUtils
  * MDUtils
  * TextUtil

* In Intellij, to view the test coverage, create a folder in resources where you can store your report. Then right click on the root folder of test -> `More Run/Debug -> Run Tests in com.os.jssg with Coverage -> Click on Generate Coverage Report on the left side of popup modal`  
  
## Legal stuff

As a contributor:

* You will only submit contributions where you have authored 100% of the content.
* Whatever content you contribute will be provided under the project license(s).