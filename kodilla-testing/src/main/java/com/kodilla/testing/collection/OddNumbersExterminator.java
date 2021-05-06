package com.kodilla.testing.collection;

import java.util.ArrayList;
import java.util.List;

public class OddNumbersExterminator {
    public List<Integer> exterminate(List<Integer> numbers) {
        /*
        * Accepting the argument collection List of the Integer numbers.
        * Returning a collection List of the Integer numbers,
        * stanowiący podzbiór kolekcji werjściowej,
        * z pominięciem liczb nieparzystych.
         */
        List<Integer> evenNumbers = new ArrayList<Integer>();
        for (Integer number: numbers
             ) {
            if (number % 2 == 0)
                evenNumbers.add(number);
        }
        return evenNumbers;
    }
}
