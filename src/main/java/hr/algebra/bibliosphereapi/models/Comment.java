package hr.algebra.bibliosphereapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    public Comment(long id, String text) {
        this.id=id;
        this.text=text;
    }

    public Comment() {

    }

    // Constructors, getters, setters...
}
