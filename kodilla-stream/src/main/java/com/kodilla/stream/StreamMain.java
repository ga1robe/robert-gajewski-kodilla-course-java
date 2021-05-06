package com.kodilla.stream;

//import com.kodilla.stream.beautifier.PoemBeautifier;
//import com.kodilla.stream.iterable.NumbersGenerator;

import com.kodilla.stream.book.Book;
import com.kodilla.stream.book.BookDirectory;
import com.kodilla.stream.forumuser.Forum;
import com.kodilla.stream.forumuser.ForumUser;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMain {

    public static void main(String[] args) {
        System.out.println("Welcome to module 7 - Strem");
        /*
        PoemBeautifier poemBeautifier = new PoemBeautifier();
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> (a.toLowerCase()));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> (a.toUpperCase()));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> ("ABC>".concat(a).concat("<ABC")));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> ("###> ".concat(a).concat("< ###")));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> ("$$$> ".concat(a).concat("< $$$")));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> ("@@@[ ".concat(a).concat(" ]@@@")));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> ("**********( ".concat(a).concat(" )**********")));
        poemBeautifier.beautify("abcdefghijklmnoprstuvwxyz", (a) -> ("%*********( ".concat(a.substring(7,16)).concat(" )*********%")));
        System.out.println("Using Stream to generate even numbers from 1 to 20");
        NumbersGenerator.generateEven(20);
        BookDirectory theBookDirectory = new BookDirectory();
        String theResultStringOfBooks = theBookDirectory.getList().stream()
                .filter(book -> book.getYearOfPublication() > 2005)
                .map(Book::toString)
                .collect(Collectors.joining(",\n","<<",">>"));
        System.out.println(theResultStringOfBooks);
        */
        Forum forum = new Forum();
        LocalDate date20yearAgo = LocalDate.now().minusYears(20);
        Map<Integer, String> theResultMapOfUsers = forum.getList().stream().filter(user -> user.getDateOfBirth().isBefore(date20yearAgo)).filter(user -> user.getSex() == 'M').filter(user -> user.getNumberOfPostPublished() >= 1).collect(Collectors.toMap(ForumUser::getUserID, ForumUser::toString));;
        System.out.println("# elements: " + theResultMapOfUsers.size());
        theResultMapOfUsers.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).forEach(System.out::println);
    }
}
