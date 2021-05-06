package com.kodilla.patterns.singleton;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTestSuite {

    private static Logger logger;

    @BeforeAll
    public static void openLoggerFile() {
        logger = Logger.INSTANCE;
        logger.open("singleton.log");
    }

    @AfterAll
    public static void getLastLog() {
        logger.getLastLog();
    }

    @Test
    void testGetFileName() {
        /**
         * Given
         * When
         */
        String fileName = logger.getFileName();
        System.out.println("Opened log file: " + fileName);
        /**
         * Then
         */
        assertEquals("singleton.log", fileName);
    }

    @Test
    void testAddLog() {
        /**
         * Given
         * When
         */
        logger.log("test singleton");
        /**
         * Then
         */
    }

    @Test
    void testGetLog() {
        /**
         * Given
         * When
         */
        logger.log("test singleton");
        String result = logger.getLastLog();
        result = result.substring(result.indexOf(" ") + 1);
        /**
         * Then
         */
        assertEquals("test singleton", result);
    }
}
