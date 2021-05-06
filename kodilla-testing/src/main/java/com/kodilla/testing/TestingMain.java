package com.kodilla.testing;

import com.kodilla.testing.library.Book;
import com.kodilla.testing.library.BookLibrary;
import com.kodilla.testing.library.LibraryDatabase;
import com.kodilla.testing.library.LibraryUser;
import com.kodilla.testing.user.SimpleUser;
import com.kodilla.testing.calculator.Calculator;
import com.kodilla.testing.weather.stub.Temperatures;
import com.kodilla.testing.weather.stub.WeatherForecast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestingMain {
    public static void main(String[] args) {
        System.out.println("Moduł 6. Testy jednostkowe");
        LibraryDatabase libraryDatabaseMock = new LibraryDatabase() {
            @Override
            public List<Book> listBooksWithCondition(String titleFragment) {
                return null;
            }

            @Override
            public List<Book> listBooksInHandsOf(LibraryUser libraryUser) {
                return null;
            }

            @Override
            public boolean rentABook(LibraryUser libraryUser, Book book) {
                return false;
            }

            @Override
            public int returnBooks(LibraryUser libraryUser) {
                return 0;
            }
        };
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        LibraryUser libraryUser1 = new LibraryUser("John","Smith", "320101011");
        LibraryUser libraryUser2 = new LibraryUser("Hatun","Majid", "330506022");
        LibraryUser libraryUser3 = new LibraryUser("Irfan","Hake", "351205033");
        /*
        Temperatures temperatures = new Temperatures() {
            @Override
            public Map<String, Double> getTemperatures() {
                Map<String, Double> stubResult = new HashMap<>();
                / * dummy data * /
                stubResult.put("Rzeszów", 25.5);
                stubResult.put("Kraków", 26.2);
                stubResult.put("Wrocław", 24.8);
                stubResult.put("Warszawa", 25.2);
                stubResult.put("Gdańsk", 26.1);
                return stubResult;
            }
        };

        WeatherForecast weatherForecast = new WeatherForecast(temperatures);
        System.out.println("Average of :" + weatherForecast.calculateAverage());
        System.out.println("Meridian of :" + weatherForecast.calculateMedian());
        */
    }
}