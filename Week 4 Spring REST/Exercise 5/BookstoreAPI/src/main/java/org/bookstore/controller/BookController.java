package org.bookstore.controller;

import org.bookstore.entity.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books= new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Book>> getBook() {
        if(books.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Books ", String.valueOf(books.size()));
        return new ResponseEntity<>(books,headers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id){
                HttpHeaders headers = new HttpHeaders();
                headers.add("Book Found", "true");
                return ResponseEntity.ok().headers(headers).body(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Book>> getBookByTitle(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "author", required = false) String author) {
        List<Book> newBooks = new ArrayList<>();
        boolean isTitleEmpty = Objects.isNull(title);
        boolean isAuthorEmpty = Objects.isNull(author);
        for (Book book : books) {
            if(!isTitleEmpty && !isAuthorEmpty) {
                if(book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                    newBooks.add(book);
                }
            }
            else{
                if(book.getTitle().equalsIgnoreCase(title)) {
                    newBooks.add(book);
                }
                else if(book.getAuthor().equalsIgnoreCase(author)) {
                    newBooks.add(book);
                }
            }
        }
        if(newBooks.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Found", "true");
        return ResponseEntity.ok().headers(headers).body(newBooks);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        for (Book book1 : books) {
            if (Objects.equals(book1.getId(), book.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Added", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        Book oldBook = getBookById(id).getBody();
        if (oldBook != null) {
            if(book.getTitle() != null)
                oldBook.setTitle(book.getTitle());
            if(book.getAuthor() != null)
                oldBook.setAuthor(book.getAuthor());
            if(book.getPrice() != null)
                oldBook.setPrice(book.getPrice());
            if (book.getIsbn() != null)
                oldBook.setIsbn(book.getIsbn());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Book Updated", "true");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        int index = 0;
        while(index < books.size()) {
            if (books.get(index).getId() == id) {
                books.remove(index);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Book Deleted", "true");
                return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
            }
            index++;
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}