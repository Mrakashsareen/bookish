package com.shopcart.bookish.service;

import com.shopcart.bookish.model.Book;
import com.shopcart.bookish.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Mono<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book);
    }

    public Mono<Book> updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setGenre((bookDetails.getGenre()));
                    existingBook.setPrice(bookDetails.getPrice());
                    return bookRepository.save(existingBook);
                });
    }
    public Flux<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Flux<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    public Mono<Void> deleteBook(Long id) {
        return bookRepository.deleteById(id);
    }
}
