package com.kodilla.testing.collection;

import com.kodilla.testing.collection.OddNumbersExterminator;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;

public class CollectionTestSuite {
    @BeforeEach
    public void before() {
        System.out.println("Test Case: begin");
    }
    @AfterEach
    public void after() {
        System.out.println("Test Case: end");
    }
    @DisplayName("When created Collection with Empty Integer items, " +
            "then exterminate should return Empty items"
    )
    @Test
    void testOddNumbersExterminatorEmptyList() {
        /*
         * Given
         */
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();
        /*
         * When
         */
        List<Integer> expectedEmptyList = new ArrayList<Integer>();
        List<Integer> result = oddNumbersExterminator.exterminate(new ArrayList<Integer>());
        //System.out.println("result:" + result);
        /*
         * Then
         */
        Assertions.assertArrayEquals(expectedEmptyList.toArray(), result.toArray());
    }
    @DisplayName("When created Collection with Integer items, " +
            "then exterminate should return even correct items"
    )
    @Test
    void testOddNumbersExterminatorNormalList() {
        /*
         * Given
         */
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();
        /*
         * When
         */
        List<Integer> theList = new ArrayList<Integer>();
        theList.add(10);
        theList.add(15);
        theList.add(20);
        theList.add(25);
        theList.add(30);
        theList.add(35);
        List<Integer> expectedList = new ArrayList<Integer>();
        expectedList.add(10);
        expectedList.add(20);
        expectedList.add(30);
        List<Integer> result = oddNumbersExterminator.exterminate(theList);
        /*
         * Then
         */
        Assertions.assertArrayEquals(expectedList.toArray(), result.toArray());
    }
}
