package hr.algebra.bibliosphereapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
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

    // Constructors, getters, setters...
}
