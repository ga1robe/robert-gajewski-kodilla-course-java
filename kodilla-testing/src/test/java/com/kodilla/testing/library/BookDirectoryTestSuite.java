package com.kodilla.testing.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookDirectoryTestSuite {
    private static int testCounter = 0;
    private static List<LibraryUser> libraryUsers = new ArrayList<>();
    private static List<Book> resultListOfBooks = new ArrayList<>();
    @Mock
    private LibraryDatabase libraryDatabaseMock;
    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
        libraryUsers.add(new LibraryUser("John","Smith", "320101011"));
        libraryUsers.add(new LibraryUser("Hatun","Majid", "330506022"));
        libraryUsers.add(new LibraryUser("Irfan","Hake", "351205033"));
    }
    @Test
    void testListBooksWithConditionsReturnList() {
        /*
        * Given
         */
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<>();
        resultListOfBooks.add(new Book("Secrets of Alamo", "John Smith", 2008));
        resultListOfBooks.add(new Book("Secretaries and Directors", "Dilbert Michigan", 2012));
        resultListOfBooks.add(new Book("Secret life of programmers", "Steve Wolkowitz", 2016));
        resultListOfBooks.add(new Book("Secrets of Java", "Ian Tenewitch", 2010));
        when(libraryDatabaseMock.listBooksWithCondition("Secret")).thenReturn(resultListOfBooks);
        /*
        * When
         */
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");
        /*
        * Then
         */
        //assertTrue(false);
        assertEquals(4, theListOfBooks.size());
    }
    @Test
    void testListBooksWithConditionMoreThan20() {
        /*
         * Given
         */
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf0Books = new ArrayList<Book>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);
        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks")).thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("FortyBooks")).thenReturn(resultListOf40Books);
        /*
         * When
         */
        List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
        List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
        List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FortyBooks");
        /*
         * Then
         */
        //assertTrue(false);
        assertEquals(0, theListOfBooks0.size());
        assertEquals(15, theListOfBooks15.size());
        assertEquals(0, theListOfBooks40.size());
    }
    @Test
    void testListBooksWithConditionFragmentShorterThan3() {
        /*
         * Given
         */
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        /*
         * When
         */
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");
        /*
         * Then
         */
        //assertTrue(false);
        assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());
    }
    @Test
    void testListBooksInHandsOfEquals0() {
        /*
         * Given
         */
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        when(libraryDatabaseMock.listBooksInHandsOf(libraryUsers.get(0))).thenReturn(resultListOfBooks);
        /*
         * When
         */
        List<Book> theListOfBooks = bookLibrary.listBooksInHandsOf(libraryUsers.get(0));
        /*
         * Then
         */
        assertEquals(0, theListOfBooks.size());
    }
    @Test
    void testListBooksInHandsOfEquals1() {
        /*
         * Given
         */
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf1Books = generateListOfNBooks(1);
        when(libraryDatabaseMock.listBooksInHandsOf(libraryUsers.get(1))).thenReturn(resultListOf1Books);
        /*
         * When
         */
        List<Book> theListOfBooks = bookLibrary.listBooksInHandsOf(libraryUsers.get(1));
        /*
         * Then
         */
        assertEquals(1, theListOfBooks.size());
    }
    @Test
    void testListBooksInHandsOfEquals5() {
        /*
         * Given
         */
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf5Books = generateListOfNBooks(5);
        when(libraryDatabaseMock.listBooksInHandsOf(libraryUsers.get(2))).thenReturn(resultListOf5Books);
        /*
         * When
         */
        List<Book> theListOfBooks = bookLibrary.listBooksInHandsOf(libraryUsers.get(2));
        /*
         * Then
         */
        assertEquals(5, theListOfBooks.size());
    }
    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<>();
        for (int n = 1; n <= booksQuantity; n++) {
            Book theBook = new Book
                    ("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }
}
