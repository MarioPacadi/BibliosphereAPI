package hr.algebra.bibliosphereapi.payload.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class DogCommand {

    @NotBlank(message = "Name must not be empty")
    @Size(max = 200, message = "Name can't have more than 200 characters")
    private String breedName;

    @NotNull(message = "Must be a type of DOG")
    private String breedType;
    private String breedDescription;

    //    @NotNull(message = "Type must be set to CPU, GPU, MBO, RAM, STORAGE or OTHER")
//    private Type type;
//
//    @NotBlank(message = "Code must not be empty")
//    @Size(min = 7, max = 7, message = "Code must have 7 characters")
//    private String code;
//
//    @PositiveOrZero(message = "Stock must be positive or zero")
//    @Max(value = 10000, message = "Stock can't be above 10000")
//    private long stock;
//
//    @PositiveOrZero(message = "Price must be positive or zero")
//    @Digits(integer=5, fraction=2, message = "Price can't be higher than 99999")
//    private BigDecimal price;
}
