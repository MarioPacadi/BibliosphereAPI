package hr.algebra.bibliosphereapi.dto;

import hr.algebra.bibliosphereapi.models.DogBreed;
import lombok.Getter;

@Getter
public class DogDTO {

    private final Long id;
    private final String breedName;
    private final String breedType;
    private final String breedDescription;

    public DogDTO(DogBreed dog) {
        this.id=dog.getId();
        this.breedName=dog.getBreedName();
        this.breedType=dog.getBreedType();
        this.breedDescription= dog.getBreedDescription();
    }

    public DogDTO(String breedName,DogBreed dog) {
        this.id=dog.getId();
        this.breedName=breedName;
        this.breedType=dog.getBreedType();
        this.breedDescription= dog.getBreedDescription();
    }

    @Override
    public String toString() {
        return "DogDTO{" +
                "breedName='" + breedName + '\'' +
                ", breedType='" + breedType + '\'' +
                ", breedDescription='" + breedDescription + '\'' +
                '}';
    }
}
