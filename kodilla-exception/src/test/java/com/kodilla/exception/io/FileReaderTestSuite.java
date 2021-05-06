package com.kodilla.exception.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTestSuite {
    @Test
    void testReadFile() throws IOException {
        /**
         * GIVEN
         */
        FileReader fileReader = new FileReader();
        /**
         * WHEN & THEN
         */
        assertDoesNotThrow(() -> fileReader.readFile());
    }
    @Test
    void whenFileDosentExistsReadFileShouldThrowException() {
        /**
         * GIVEN
         */
        FileReader fileReader = new FileReader();
        String fileName = "this-file-is-missing.txt";
        /**
         * WHEN & Then
         */
        assertThrows(FileReaderException.class, () -> { fileReader.readFile(fileName); });
    }
    @Test
    public void testReadFileWithName() {
        /**
         * GIVEN
         */
        FileReader fileReader = new FileReader();
        /**
         * WHEN & THEN
         */
        assertAll(
                () -> assertThrows(FileReaderException.class, () -> fileReader.readFile("nie_ma_takiego_pliku.txt")),
                () -> assertThrows(FileReaderException.class, () -> fileReader.readFile(null)),
                () -> assertDoesNotThrow(() -> fileReader.readFile("names.txt"))
        );
    }
}
