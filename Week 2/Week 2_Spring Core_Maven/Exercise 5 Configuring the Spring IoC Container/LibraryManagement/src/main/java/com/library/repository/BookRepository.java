package com.library.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private ArrayList<String> books = new ArrayList<>();

    public void addBooks(String book) {
        books.add(book);
    }
    public List<String> getBooks() {
        return books;
    }
}
