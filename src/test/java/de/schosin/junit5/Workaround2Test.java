package de.schosin.junit5;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * Having a @ValueSource or another @MethodSource with a static method call fixes the error even for the current @Nested 
 * 
 * Unless the test method name is different
 */
@TestInstance(Lifecycle.PER_CLASS)
class Workaround2Test extends AbstractRoot {

    @Nested
    class BTest extends AbstractTest {

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

    /*
     * Rename secondTestMethod to firstTestMethod to cause error
     * Use @ValueSource instead @MethodSource also fixes it
     */
    @Nested
    class ATest extends AbstractTest {

        @ParameterizedTest
        @MethodSource(STATIC_DATA)
        // @ValueSource(strings = { "baz", "quux" })
        // void firstTestMethod(String data) {
        void secondTestMethod(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

}
