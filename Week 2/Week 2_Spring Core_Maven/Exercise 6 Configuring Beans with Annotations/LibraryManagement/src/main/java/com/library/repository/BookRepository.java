package com.library.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private ArrayList<String> books = new ArrayList<>();

    public void addBooks(String book) {
        books.add(book);
    }
    public List<String> getBooks() {
        return books;
    }
}
