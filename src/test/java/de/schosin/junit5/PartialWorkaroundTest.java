package de.schosin.junit5;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Having a "regular" test in a otherwise broken @Nested will fix any @Nested coming after
 * 
 * Moving firstTestMethod into a @Nested CTest has the same outcome 
 */
@TestInstance(Lifecycle.PER_CLASS)
class PartialWorkaroundTest extends AbstractRoot {

    @Nested
    class DTest extends AbstractTest {

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

    /*
    @Nested
    class CTest extends AbstractTest {
    
        @Test
        void firstTestMethod() {
            assertTrue(2 > 1);
            // fail("not implemented yet");
        }
    
    }
    */

    @Nested
    class BTest extends AbstractTest {

        @Test
        void firstTestMethod() {
            assertTrue(2 > 1);
            // fail("not implemented yet");
        }

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

    @Nested
    class ATest extends AbstractTest {

        @ParameterizedTest
        @MethodSource(DYNAMIC_DATA)
        void test(String data) {
            assertTrue(data != null && !data.isEmpty(), "data is not empty");
        }

    }

}
