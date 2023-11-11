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
    private Long id;

    private String title;

    private String author;

    @Column(name = "imageUrl")
    private String imageUrl;

    // Add other fields as needed

    @OneToMany(mappedBy = "book")
    private List<Comment> comments;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;

    // Add other relationships and methods as needed
}
