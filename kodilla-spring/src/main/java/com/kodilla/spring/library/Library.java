package com.kodilla.spring.library;

import java.util.ArrayList;
import java.util.List;

public final class Library {

    private final List<String> books = new ArrayList<>();
    private LibraryDbController libraryDBController;

    public Library(final LibraryDbController libraryDBController) {
        this.libraryDBController = libraryDBController;
    }

    public Library() {
        /** do nothing **/
    }

    public void saveToDb() {

        libraryDBController.saveData();
    }

    public void loadFromDb() {

        libraryDBController.loadData();
    }
}
