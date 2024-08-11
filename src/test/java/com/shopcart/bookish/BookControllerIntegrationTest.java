package com.shopcart.bookish;

import com.shopcart.bookish.model.Book;
import com.shopcart.bookish.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@SpringBootTest
@AutoConfigureWebTestClient
public class BookControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll().block(); // Clear the database before each test
        book = new Book("Integration Title", "Integration Author", "Tech",34F);
    }

    @Test
    void testCreateBook() {
        webTestClient.post().uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(book))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .value(createdBook -> {
                    assert createdBook.getId() != null;
                    assert createdBook.getTitle().equals(book.getTitle());
                    assert createdBook.getAuthor().equals(book.getAuthor());
                    assert createdBook.getGenre().equals(book.getGenre());
                    assert createdBook.getPrice().equals(book.getPrice());
                });
    }

    @Test
    void testGetBookById() {
        Book savedBook = bookRepository.save(book).block();
        assert savedBook != null;

        webTestClient.get().uri("/api/books/{id}", savedBook.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(foundBook -> {
                    assert foundBook.getId().equals(savedBook.getId());
                    assert foundBook.getTitle().equals(savedBook.getTitle());
                    assert foundBook.getAuthor().equals(savedBook.getAuthor());
                    assert foundBook.getGenre().equals(savedBook
                            .getGenre());
                    assert foundBook.getPrice().equals(savedBook.getPrice());

                });
    }

    @Test
    void testGetAllBooks() {
        Book savedBook = bookRepository.save(book).block();
        assert savedBook != null;

        webTestClient.get().uri("/api/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .value(books -> {
                    assert books.size() == 1;
                    assert books.get(0).getId().equals(savedBook.getId());
                    assert books.get(0).getTitle().equals(savedBook.getTitle());
                    assert books.get(0).getAuthor().equals(savedBook.getAuthor());
                    assert books.get(0).getGenre().equals(savedBook.getGenre());
                    assert books.get(0).getPrice().equals(savedBook.getPrice());
                });
    }

    @Test
    void testUpdateBook() {
        Book createNewBook = new Book("Updated Title", "Updated Author", "tech",24F);
        Book savedBooks = bookRepository.save(createNewBook).block();
        Book updatedBook =  new Book("Title", "Updated Author", "architecture",24F);
        assert savedBooks != null;
        webTestClient.put().uri("/api/books/{id}", savedBooks.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(updatedBook))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(book -> {
                    assert book.getTitle().equals(updatedBook.getTitle());
                    assert book.getAuthor().equals(updatedBook.getAuthor());
                });
    }

    @Test
    void testDeleteBook() {
        Book savedBook = bookRepository.save(book).block();

        assert savedBook != null;
        webTestClient.delete().uri("/api/books/{id}", savedBook.getId())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get().uri("/api/books/{id}", savedBook.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
}
