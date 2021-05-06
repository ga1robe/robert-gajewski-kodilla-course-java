package com.kodilla.exception.main;

import com.kodilla.exception.io.FileReader;
//import com.kodilla.exception.io.FileReaderException;
import com.kodilla.exception.io.FileReaderWithoutHandling;
import com.kodilla.exception.test.SecondChallenge;

import java.io.IOException;

public class ExceptionModuleRunner {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader();

        fileReader.readFile();

//        FileReaderWithoutHandling fileReaderWithoutHandling = new FileReaderWithoutHandling();

        try {
            fileReader.readFile();
//            fileReaderWithoutHandling.readFile();
        } catch (IOException e) {
            System.out.println("Oh no! Something went wrong! IOException: " + e);
        } finally {
            System.out.println("I am going to be here.. always!");
        }
    }
}
