package hr.algebra.bibliosphereapi.models;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    public Rating(long id, int rating) {
        this.id=id;
        this.rating=rating;
    }

    public Rating() {

    }

    // Constructors, getters, setters...
}
