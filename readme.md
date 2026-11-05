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
Examples illustrating the most interesting developer-friendly features after Java 8. The original article, covering the Java 9-15 features, is [New Developer Friendly Features After Java 8](https://piotrminkowski.com/2021/02/01/new-developer-friendly-features-after-java-8/). The project has since been upgraded to Java 25 and extended with newer features.

## Feature index

| Feature | Java version | Status | Source / test |
|---|---|---|---|
| Private interface methods | 9 | Final | [`privatemethods/ExampleInterface`](src/main/java/pl/piomin/java/samples/privatemethods/ExampleInterface.java) |
| Immutable collection factories (`List.of`, `Map.of`) | 9 | Final | [`unmodifiablecollections/CollectionsCreator`](src/main/java/pl/piomin/java/samples/unmodifiablecollections/CollectionsCreator.java), [test](src/test/java/pl/piomin/java/samples/unmodifiablecollections/CollectionsCreatorTest.java) |
| `Optional` improvements (`orElseThrow`, `ifPresentOrElse`) | 9-10 | Final | [`optional/OptionalMethods`](src/main/java/pl/piomin/java/samples/optional/OptionalMethods.java) |
| `ServiceLoader` / SPI | 9 | Final | [`spi/`](src/main/java/pl/piomin/java/samples/spi), [test](src/test/java/pl/piomin/java/samples/spi/SpiTests.java) |
| Local-variable type inference (`var`) | 10-11 | Final | [`localvar/LocalVar`](src/main/java/pl/piomin/java/samples/localvar/LocalVar.java) |
| `DelayQueue` | (classic) | Final | [`helper/DelayedEvent`](src/main/java/pl/piomin/java/samples/helper/DelayedEvent.java), [test](src/test/java/pl/piomin/java/samples/delay/DelayTest.java) |
| Switch expressions | 14 | Final | [`switches/SwitchComparison`](src/main/java/pl/piomin/java/samples/switches/SwitchComparison.java), [test](src/test/java/pl/piomin/java/samples/switches/SwitchComparisonTests.java) |
| Text blocks | 15 | Final | [`textblocks/TextBlockString`](src/main/java/pl/piomin/java/samples/textblocks/TextBlockString.java), [test](src/test/java/pl/piomin/java/samples/textblocks/TextBlockTests.java) |
| Records | 16 | Final | [`records/`](src/main/java/pl/piomin/java/samples/records), [test](src/test/java/pl/piomin/java/samples/records/PersonTests.java) |
| Records with MapStruct | 16 | Final | [`mapper/PersonMapper`](src/main/java/pl/piomin/java/samples/mapper/PersonMapper.java), [test](src/test/java/pl/piomin/java/samples/mapper/MapperTests.java) |
| Sealed classes | 17 | Final | [`sealedclasses/`](src/main/java/pl/piomin/java/samples/sealedclasses) |
| `HexFormat` | 17 | Final | [test](src/test/java/pl/piomin/java/samples/format/FormatTests.java) |
| Day period pattern `B` in `DateTimeFormatter` | 16 | Final | [test](src/test/java/pl/piomin/java/samples/delay/DelayTest.java) |
| Pattern matching for `switch` | 21 | Final | [`switches/SwitchComparison`](src/main/java/pl/piomin/java/samples/switches/SwitchComparison.java), [test](src/test/java/pl/piomin/java/samples/switches/SwitchComparisonTests.java) |
| Sequenced collections | 21 | Final | [test](src/test/java/pl/piomin/java/samples/collections/SequencedCollectionTests.java) |
| `EnumSet` (`range`, `complementOf`) | (classic) | Final | [test](src/test/java/pl/piomin/java/samples/enums/EnumSetTest.java) |
| Stream Gatherers (JEP 485) | 24 | Final | [`gatherers/GatherersComparison`](src/main/java/pl/piomin/java/samples/gatherers/GatherersComparison.java), [test](src/test/java/pl/piomin/java/samples/gatherers/GatherersComparisonTests.java) |
| Flexible constructor bodies (JEP 513) | 25 | Final | [`constructors/SavingsAccount`](src/main/java/pl/piomin/java/samples/constructors/SavingsAccount.java), [test](src/test/java/pl/piomin/java/samples/constructors/SavingsAccountTests.java) |
| Scoped values (JEP 506) | 25 | Final | [`scopedvalues/ScopedValueExample`](src/main/java/pl/piomin/java/samples/scopedvalues/ScopedValueExample.java), [test](src/test/java/pl/piomin/java/samples/scopedvalues/ScopedValueExampleTests.java) |
| Structured concurrency (JEP 505) | 25 | **Preview** (`--enable-preview`) | [test](src/test/java/pl/piomin/java/samples/structuredconcurrency/StructuredConcurrencyTests.java); also used in `ScopedValueExample` |

Features marked **Preview** require `--enable-preview` at compile time and at run time. Status is as of JDK 25. Java version `(classic)` means the example is not tied to a recent language change.

## Build and run

Requirements: JDK 25 and Maven 3.9+.

```shell
java -version        # should report 25
mvn clean verify     # compile and run all tests
mvn test -Dtest=ScopedValueExampleTests   # run a single test class
```

Preview features are already enabled in `pom.xml`: `--enable-preview` is passed to `maven-compiler-plugin` and to the Surefire `argLine`. To run compiled classes outside Maven, pass the flag yourself:

```shell
java --enable-preview -cp target/classes:target/test-classes <main-class>
```

When adding a preview feature, keep it in a clearly named class or test and mark it **Preview** in the table above.
