package com.kodilla.patterns.prototype.library;

import java.time.LocalDate;
import java.util.HashSet;

public class Book {

    final String title;
    final String author;
    final LocalDate publicationDate;
    public HashSet<Object> books;

    public Book(final String title, final String author, final LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        return "\tBook [" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ']';
    }
}
