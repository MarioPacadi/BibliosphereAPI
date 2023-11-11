package hr.algebra.bibliosphereapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    private String author;

    private String imageUrl; // book cover URL

    // Other book details...

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public Book(long id, String title, String author, String imageUrl) {
        this.id=id;
        this.title=title;
        this.author=author;
        this.imageUrl=imageUrl;
    }

    public Book() {

    }

    // Constructors, getters, setters...
}
