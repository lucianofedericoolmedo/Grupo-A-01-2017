Execute tests based on JUnit 5 milestones using Maven. In addition, it showcases that existing JUnit 4 based tests can be executed in the same test suite as JUnit 5 based tests or any other tests supported on
the JUnit Platform.

This sample project does not aim to demonstrate how to use the JUnit Jupiter APIs (http://junit.org/junit5/docs/current/user-guide/).

Please note that this project uses the [Maven Wrapper](https://github.com/takari/maven-wrapper).
Thus, to ensure that the correct version of Maven is used, invoke `mvnw` instead of `mvn`.

## Executing JUnit 4 and JUnit Jupiter Tests

Invoking `mvnw clean test` from the command line will execute all tests in the test source
folder that follow one of following patterns: `Test*`, `*Test`, `*Tests`, or `*TestCase`.
Note that [Surefire's default naming patterns](http://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html)
have been overridden in the `pom.xml` file. Surefire's execution of
the sample tests should result in output similar to the following:


