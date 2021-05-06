package com.kodilla.patterns.prototype.library;

import com.kodilla.patterns.prototype.Board;
import com.kodilla.patterns.prototype.TasksList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTestSuite {

    @Test
    void testGetBooks() {
        /**
         * Given
         * Creating the book list
         */
        Library library = new Library("Library branch 1");
        IntStream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(n -> library.getBooks().add(new Book(String.format("volume %02d",n),String.format("anonymous %02d",n), generateLocalDate())));
        /**
         * Given
         * making a shallow clone of object library
         */
        Library clonedLibrary = null;
        try {
            clonedLibrary = library.shallowCopy();
            clonedLibrary.setName("Library branch 2");
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        /**
         * Given
         * Making a deep copy of object library
         */
        Library deepClonedLibrary = null;
        try {
            deepClonedLibrary = library.deepCopy();
            deepClonedLibrary.setName("Library branch 3");
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        /**
         * When
         * add volumes
         */
        IntStream.iterate(11, n -> n + 1)
                .limit(5)
                .forEach(n -> library.getBooks().add(new Book(String.format("volume %02d",n),String.format("anonymous %02d",n), generateLocalDate())));
        /**
         * Then
         */
        System.out.println(library);
        System.out.println(clonedLibrary);
        System.out.println(deepClonedLibrary);
        /**
         * When
         * Then
         * assertEquals of lists' size
         */
        assertEquals(15, library.getBooks().size());
        assertEquals(15, clonedLibrary.getBooks().size());
        assertEquals(10, deepClonedLibrary.getBooks().size());
        /**
         * When
         * Then
         * assertEquals of compare cloned lists with base list
         */
        assertEquals(clonedLibrary.getBooks(), library.getBooks());
        assertNotEquals(deepClonedLibrary.getBooks(), library.getBooks());
    }
    private static LocalDate generateLocalDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2020, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

}
