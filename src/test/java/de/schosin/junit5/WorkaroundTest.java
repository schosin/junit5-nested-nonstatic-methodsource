package de.schosin.junit5;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Passing or failing test in another @Nested before (sorted by class name) @Nested's with @MethodSource fixes error
 * 
 * Renmaing ATest to DTest
 */
@TestInstance(Lifecycle.PER_CLASS)
class WorkaroundTest extends AbstractRoot {

    @Nested
    class CTest extends AbstractTest {

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

    @Nested
    class BTest extends AbstractTest {

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

    @Nested
    class ATest {

        /**
         * Pass / fail does not matter
         */
        @Test
        void test() {
            assertTrue(2 > 1);
            // fail("not implemented yet");
        }

    }

}
