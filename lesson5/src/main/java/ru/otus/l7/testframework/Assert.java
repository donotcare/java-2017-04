package ru.otus.l7.testframework;

import java.util.Objects;

public class Assert {
    public static void assertTrue(boolean condition) {
        if(!condition) {
            throw new RuntimeException("Test assertTrue failed");
        }
    }

    public static void assertFalse(boolean condition) {
        if(condition) {
            throw new RuntimeException("Test assertFalse failed");
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        if(!Objects.equals(expected, actual)) {
            throw new RuntimeException(String.format("Test assertEquals failed [expected: %s actual: %s]", expected, actual));
        }
    }
}
