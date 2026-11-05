# New Features of Java After 8 [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

[![CircleCI](https://circleci.com/gh/piomin/sample-java-new-features.svg?style=svg)](https://circleci.com/gh/piomin/sample-java-new-features)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=piomin_sample-java-new-features)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-java-new-features&metric=bugs)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-microservices-new)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-java-new-features&metric=coverage)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-microservices-new)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=piomin_sample-java-new-features&metric=ncloc)](https://sonarcloud.io/dashboard?id=piomin_sample-spring-microservices-new)

Examples illustrating the most interesting developer-friendly features after Java 8 described in the following article: [New Developer Friendly Features After Java 8](https://piotrminkowski.com/2021/02/01/new-developer-friendly-features-after-java-8/)

## Compact source files and instance main methods (Java 25)

JEP 512 example in `src/main/source-launcher/Hello.java` (kept outside the Maven compile roots). Run it directly with the source launcher:

```shell
java src/main/source-launcher/Hello.java
```

A multi-file source program (Java 22, JEP 458) is in `src/main/source-launcher/multifile`:

```shell
java src/main/source-launcher/multifile/Main.java
```
