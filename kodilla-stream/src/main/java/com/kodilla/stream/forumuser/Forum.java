package com.kodilla.stream.forumuser;

import com.kodilla.stream.book.Book;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Forum {
    private final List<ForumUser> theUserList = new ArrayList<>();
    public Forum() {
        theUserList.add(new ForumUser(1, "username1", 'M', LocalDate.of(2001,10,31), 11));
        theUserList.add(new ForumUser(2, "username2", 'F', LocalDate.of(1999,5,2), 1));
        theUserList.add(new ForumUser(3, "username3", 'M', LocalDate.of(2002,7,7), 2));
        theUserList.add(new ForumUser(4, "username4", 'F', LocalDate.of(1999,11,30), 167));
        theUserList.add(new ForumUser(5, "username5", 'F', LocalDate.of(2000,1,1), 23));
        theUserList.add(new ForumUser(6, "username6", 'M', LocalDate.of(1999,2,2), 1456));
        theUserList.add(new ForumUser(7, "username7", 'M', LocalDate.of(2002,12,29), 223));
        theUserList.add(new ForumUser(8, "username8", 'F', LocalDate.of(1997,7,22), 111));
        theUserList.add(new ForumUser(9, "username9", 'M', LocalDate.of(1996,8,2), 0));
        theUserList.add(new ForumUser(10, "username10", 'F', LocalDate.of(1995,8,2), 67));
    }
    public List<ForumUser> getList() { return new ArrayList<>(theUserList); }
}
