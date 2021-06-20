package com.kodilla.patterns2.adapter.bookclassifier;

import com.kodilla.patterns2.adapter.bookclasifier.MedianAdapter;
import com.kodilla.patterns2.adapter.bookclasifier.librarya.Book;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MedianAdapterTestSuite {
    @Test
    public void publicationYearMedianTest() {
        /*
        * Given
        * */
        Set<Book> books = new HashSet<>();
        books.add(new Book("Anonymous001", "Volume001", 1923, "111"));
        books.add(new Book("Anonymous002", "Volume002", 1933, "222"));
        books.add(new Book("Anonymous003", "Volume003", 2000, "333"));
        books.add(new Book("Anonymous004", "Volume004", 2007, "444"));
        books.add(new Book("Anonymous005", "Volume005", 2001, "555"));
        books.add(new Book("Anonymous006", "Volume006", 1999, "666"));
        MedianAdapter medianAdapter = new MedianAdapter();
        /*
        * When
        * */
        int median = medianAdapter.publicationYearMedian(books);
        /*
        * Then
        * */
        System.out.println(median);
        assertEquals(median, 2000);
    }
}
