package com.shopcart.bookish;

import com.shopcart.bookish.model.Book;
import com.shopcart.bookish.repository.BookRepository;
import com.shopcart.bookish.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book(1L,"Title 1", "Author 1", "Technology", 23.2F);
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Flux.just(book));

        Flux<Book> books = bookService.getAllBooks();

        StepVerifier.create(books)
                .expectNext(book)
                .verifyComplete();
    }

    @Test
    void getBookById() {
        when(bookRepository.findById(1L)).thenReturn(Mono.just(book));

        Mono<Book> foundBook = bookService.getBookById(1L);

        StepVerifier.create(foundBook)
                .expectNext(book)
                .verifyComplete();
    }

    @Test
    void createBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(book));

        Mono<Book> savedBook = bookService.createBook(book);

        StepVerifier.create(savedBook)
                .expectNext(book)
                .verifyComplete();
    }

    @Test
    void updateBook() {
        when(bookRepository.findById(1L)).thenReturn(Mono.just(book));
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(book));

        Mono<Book> updatedBook = bookService.updateBook(1L, book);

        StepVerifier.create(updatedBook)
                .expectNext(book)
                .verifyComplete();
    }

    @Test
    void deleteBook() {
        when(bookRepository.deleteById(1L)).thenReturn(Mono.empty());

        Mono<Void> result = bookService.deleteBook(1L);

        StepVerifier.create(result)
                .verifyComplete();

        verify(bookRepository, times(1)).deleteById(1L);
    }

}
