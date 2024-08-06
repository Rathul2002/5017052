package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void add(String book) {
        bookRepository.addBooks(book);
    }
    public void displayBooks()
    {
        System.out.println(bookRepository.getBooks());
    }
}
