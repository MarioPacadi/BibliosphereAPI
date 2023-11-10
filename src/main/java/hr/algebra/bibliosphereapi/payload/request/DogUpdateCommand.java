package hr.algebra.bibliosphereapi.payload.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class DogUpdateCommand {

    @PositiveOrZero(message = "Id must be positive or zero")
    private Long id;
    @NotBlank(message = "Name must not be empty")
    @Size(max = 200, message = "Name can't have more than 200 characters")
    private String breedName;

    @NotNull(message = "Must be a type of DOG")
    private String breedType;
    private String breedDescription;

}
