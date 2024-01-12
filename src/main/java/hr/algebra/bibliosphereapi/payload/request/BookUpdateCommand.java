package hr.algebra.bibliosphereapi.payload.request;

import hr.algebra.bibliosphereapi.models.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookUpdateCommand {

    @NotBlank(message = "Title must not be empty")
    private String title;

    @NotBlank(message = "Author must not be empty")
    private String author;

    private String image_url;
}
