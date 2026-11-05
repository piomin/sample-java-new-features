# New Features of Java After 8 [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

[![CircleCI](https://circleci.com/gh/piomin/sample-java-new-features.svg?style=svg)](https://circleci.com/gh/piomin/sample-java-new-features)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=piomin_sample-java-new-features)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-java-new-features&metric=bugs)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-microservices-new)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-java-new-features&metric=coverage)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-microservices-new)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-java-new-features&metric=ncloc)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-microservices-new)

Examples illustrating the most interesting developer-friendly features after Java 8 described in the following article: [New Developer Friendly Features After Java 8](https://piotrminkowski.com/2021/02/01/new-developer-friendly-features-after-java-8/)


## JVM and Tooling Improvements (Java 18-25)

The features below live in the JVM, the launcher or the JDK tools, so they need no changes in the Java code. Command-line flags were checked against OpenJDK 25.0.2.

### Compact object headers (JEP 450, JEP 519)

Shrinks the object header from 12 to 8 bytes on 64-bit platforms, which reduces heap usage and improves data density. It was experimental in Java 24 (JEP 450) and became a product feature in Java 25 (JEP 519), but it is still disabled by default.

```shell
# Java 25
java -XX:+UseCompactObjectHeaders -jar app.jar
# Java 24 (experimental)
java -XX:+UnlockExperimentalVMOptions -XX:+UseCompactObjectHeaders -jar app.jar
```

### Generational ZGC (JEP 439, JEP 474, JEP 490)

Generational ZGC was introduced in Java 21 (JEP 439) behind `-XX:+ZGenerational`. In Java 23 it became the default mode of ZGC (JEP 474), and in Java 24 the non-generational mode was removed (JEP 490). In Java 25 `-XX:+UseZGC` is therefore all you need, and the `ZGenerational` flag no longer exists.

```shell
java -XX:+UseZGC -jar app.jar
```

### Ahead-of-time class loading and linking (JEP 483, JEP 514, JEP 515)

Improves startup time by caching loaded and linked classes of an application in an AOT cache.

- Java 24 (JEP 483) - three steps: record a configuration, create the cache, run with the cache.
- Java 25 (JEP 514) - `-XX:AOTCacheOutput` combines the record and create steps into one.
- Java 25 (JEP 515) - the AOT cache also stores method execution profiles, so the JIT compiler can reach peak performance faster after start.

```shell
# 1. training run - record the AOT configuration
java -XX:AOTMode=record -XX:AOTConfiguration=app.aotconf -cp app.jar com.example.Main
# 2. create the AOT cache from the configuration
java -XX:AOTMode=create -XX:AOTConfiguration=app.aotconf -XX:AOTCache=app.aot -cp app.jar
# 3. production run using the cache
java -XX:AOTCache=app.aot -cp app.jar com.example.Main

# Java 25 shortcut: steps 1 and 2 in a single command
java -XX:AOTCacheOutput=app.aot -cp app.jar com.example.Main
```

The cache is only valid for the same JDK and a compatible classpath as the training run.

### JFR improvements (JEP 509, JEP 520)

- JEP 509 (Java 25, experimental) - CPU-time profiling in JDK Flight Recorder. It produces the `jdk.CPUTimeSample` event and is supported on Linux only.
- JEP 520 (Java 25) - method timing and tracing. Selected methods are instrumented with bytecode, without modifying the source. The options are `method-timing` and `method-trace` of `-XX:StartFlightRecording`.

```shell
java -XX:StartFlightRecording:filename=rec.jfr,method-timing=com.example.Service::process -jar app.jar
jfr print --events jdk.MethodTiming rec.jfr
```

### Simple Web Server `jwebserver` (JEP 408)

Since Java 18 the JDK ships a minimal, static-file HTTP server, intended for prototyping and testing. It binds to loopback by default.

```shell
jwebserver                      # serves the current directory on 127.0.0.1:8000
jwebserver -p 9000 -d /tmp/site # custom port and directory
```

It can also be used programmatically through the `com.sun.net.httpserver.SimpleFileServer` API.

### Launching multi-file source-code programs (JEP 458)

Since Java 22 the `java` launcher in source-file mode compiles and runs a program split into several `.java` files in memory, with no separate `javac` step. Only the first file declares the entry point.

```shell
java Main.java   # Main.java may use classes defined in Helper.java in the same directory
```

### Vector API (JEP 508, incubator)

The Vector API is in its tenth incubation round in Java 25. Because it is an incubator module, it has to be enabled explicitly at compile time and at runtime.

```shell
javac --add-modules jdk.incubator.vector Example.java
java  --add-modules jdk.incubator.vector Example
```
