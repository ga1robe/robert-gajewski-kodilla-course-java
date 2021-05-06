package com.kodilla.stream.array;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public interface ArrayOperations {
    static double getAverage(int[] numbers) {
        OptionalDouble average = IntStream.of(numbers).average();
        if (average.isPresent())
            return average.getAsDouble();
        return 0d; }
}
