package hr.algebra.bibliosphereapi.models;

import hr.algebra.bibliosphereapi.payload.request.BookUpdateCommand;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "image_url")
    private String imageUrl;

    public Book(){}

    public Book(String title,String author){
        this.title=title;
        this.author=author;
    }

    public Book(BookUpdateCommand bookUpdateCommand) {
        this.title=bookUpdateCommand.getTitle();
        this.author=bookUpdateCommand.getAuthor();
        this.imageUrl=bookUpdateCommand.getImage_url();
    }

    public Book(Long id, BookUpdateCommand bookUpdateCommand) {
        this.id=id;
        this.title=bookUpdateCommand.getTitle();
        this.author=bookUpdateCommand.getAuthor();
        this.imageUrl=bookUpdateCommand.getImage_url();
    }

}
