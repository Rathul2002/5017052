package com.library.service;

import com.library.repository.BookRepository;


public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void add(String book) {
        bookRepository.addBooks(book);
    }
    public void displayBooks() {
        System.out.println(bookRepository.getBooks());
    }
}
