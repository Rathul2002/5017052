package org.bookstore.mapper;

import javax.annotation.processing.Generated;
import org.bookstore.dto.BookDTO;
import org.bookstore.entity.Book;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T00:16:10+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setPrice( book.getPrice() );
        bookDTO.setIsbn( book.getIsbn() );

        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        book.setPrice( bookDTO.getPrice() );
        book.setIsbn( bookDTO.getIsbn() );

        return book;
    }
}
