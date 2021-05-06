package com.kodilla.stream.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayOperationsTestSuite implements ArrayOperations {
    @Test
    void testGetAverage() {
        /*
        * Given
         */
        /*
        * When
         */
        int[] randomNumbers = new int[] {37,36,39,76,8,34,68,59,86,37,48,7,94,33,20,85,0,56,27,88};
        double average = ArrayOperations.getAverage(randomNumbers);
        /*
        * Then
         */
        assertEquals(46.9d, average, 0.1);
    }
}
