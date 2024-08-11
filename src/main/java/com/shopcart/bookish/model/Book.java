package com.shopcart.bookish.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("books")
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Float price;

//     If you want to create a constructor without 'id' for use in the tests:
    public Book(String title, String author, String genre, Float price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }
}
