package com.shopcart.bookish.repository;

import com.shopcart.bookish.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Flux<Book> findByTitleContainingIgnoreCase(String title);

    Flux<Book> findByAuthorContainingIgnoreCase(String author);
}
