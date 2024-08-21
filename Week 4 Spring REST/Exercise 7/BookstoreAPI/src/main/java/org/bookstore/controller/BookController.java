package org.bookstore.controller;

import org.bookstore.mapper.BookMapper;
import org.bookstore.dto.BookDTO;
import org.bookstore.entity.Book;
import org.bookstore.exception.CustomException;
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
    private final BookMapper bookMapper;
    public BookController(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDTO>> getBook() {
        if(books.isEmpty())
        {
            throw new CustomException("Empty book list");
        }
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(bookMapper.toDTO(book));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Books ", String.valueOf(bookDTOs.size()));
        return new ResponseEntity<>(bookDTOs,headers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id){
                HttpHeaders headers = new HttpHeaders();
                headers.add("Book Found", "true");
                return ResponseEntity.ok().headers(headers).body(bookMapper.toDTO(book));
            }
        }
        throw new CustomException("Book with ID " + id + " not found.");
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
            throw new CustomException("No books found for the given criteria.");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Found", "true");
        return ResponseEntity.ok().headers(headers).body(newBooks);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        for (Book existingBook : books) {
            if (Objects.equals(existingBook.getId(), book.getId())) {
                throw new CustomException("Book with ID " + book.getId() + " already exists.");
            }
        }
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Added", "true");
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Book Added Successfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateBook(@RequestBody BookDTO bookDTO, @PathVariable("id") int id) {
        Book existingBook = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomException("Book with ID " + id + " not found."));

        if (bookDTO.getTitle() != null)
            existingBook.setTitle(bookDTO.getTitle());
        if (bookDTO.getAuthor() != null)
            existingBook.setAuthor(bookDTO.getAuthor());
        if (bookDTO.getPrice() != null)
            existingBook.setPrice(bookDTO.getPrice());
        if (bookDTO.getIsbn() != null)
            existingBook.setIsbn(bookDTO.getIsbn());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Updated", "true");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Book with ID " + id + " updated successfully.");
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id) {
        int index = 0;
        while (index < books.size()) {
            if (books.get(index).getId() == id) {
                books.remove(index);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Book Deleted", "true");
                return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body("Book with ID " + id + " deleted successfully.");
            }
            index++;
        }
        throw new CustomException("Book with ID " + id + " not found.");
    }
}