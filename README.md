JavaParser and Maven sample
---

A fully working sample Maven project that parses and generates code with [JavaParser](http://www.javaparser.org)

This is targeted at people without [Maven](https://maven.apache.org/) experience.

## Build

To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi)
and put the `mvn` command on your path.
Then, you will need to install a Java 1.8 (or higher) JDK (not JRE!), and make sure you can run `java` from the command line.

Now you can run 

```bash
$ mvn clean install
```

Maven will compile your project,  an put the results it in two jar files in the `target` directory.

## Run

If you like to run from the command line,
execute

```bash
java -jar target/javaparser-maven-1.0-SNAPSHOT-shaded.jar /Absolute/Path/To/The/Java/File/You/Want/To/Parse
```
