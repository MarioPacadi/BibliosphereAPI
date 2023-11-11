package hr.algebra.bibliosphereapi.models;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    // Add other fields as needed
}
