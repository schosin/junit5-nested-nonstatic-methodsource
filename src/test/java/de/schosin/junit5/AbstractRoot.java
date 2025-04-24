package de.schosin.junit5;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
abstract class AbstractRoot {

    protected static final String DYNAMIC_DATA = "dynamicData";
    protected static final String STATIC_DATA = "staticData";

    List<String> dynamicData;

    @BeforeEach
    void setup() {
        this.dynamicData = List.of("foo", "bar");
    }

    @TestInstance(Lifecycle.PER_CLASS)
    abstract class AbstractTest {

        Stream<String> dynamicData() {
            return dynamicData.stream();
        }

        static Stream<String> staticData() {
            return Stream.of("baz", "quux");
        }

    }

}
