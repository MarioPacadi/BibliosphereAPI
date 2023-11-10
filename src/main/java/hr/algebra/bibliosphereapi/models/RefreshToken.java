package hr.algebra.bibliosphereapi.models;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity()
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private Account user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiryDate", nullable = false)
    private Instant expiryDate;

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

}
