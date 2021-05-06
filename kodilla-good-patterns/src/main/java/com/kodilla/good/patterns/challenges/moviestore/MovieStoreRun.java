package com.kodilla.good.patterns.challenges.moviestore;

import java.util.List;
import java.util.stream.Collectors;

public class MovieStoreRun {
    public static void main(String[] args) {
        System.out.println("Welcome to module 13 - Good Practice");
        MovieStore movieStore = new MovieStore();
        String listOfMovies = movieStore
                .getMovies()
                .entrySet()
                .stream().map(f -> f.getValue())
                .flatMap(List::stream)
                .collect(Collectors.joining("!"));
        System.out.println("List of movies:\n" + listOfMovies);
    }
}
