package hr.algebra.bibliosphereapi.models;

import hr.algebra.bibliosphereapi.payload.request.DogCommand;
import hr.algebra.bibliosphereapi.payload.request.DogUpdateCommand;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "dog_breed")
public class DogBreed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "breed_name")
    private String breedName;

    @Column(name = "breed_type")
    private String breedType;

    @Column(name = "breed_description")
    private String breedDescription;

    @Column(name = "fur_color")
    private String furColor;

    @Column(name = "origin")
    private String origin;

    @Column(name = "min_height_inches")
    private double minHeightInches;

    @Column(name = "max_height_inches")
    private double maxHeightInches;

    @Column(name = "min_weight_pounds")
    private double minWeightPounds;

    @Column(name = "max_weight_pounds")
    private double maxWeightPounds;

    @Column(name = "min_life_span")
    private int minLifeSpan;

    @Column(name = "max_life_span")
    private int maxLifeSpan;

    @Column(name = "img_thumb")
    private String imgThumb;

    @Column(name = "img_sourceurl")
    private String imgSourceURL;

    @Column(name = "img_attribution")
    private String imgAttribution;

    @Column(name = "img_creative_commons")
    private boolean imgCreativeCommons;

    public DogBreed(Long id, String breedName, String breedType, String breedDescription, String furColor, String origin, double minHeightInches, double maxHeightInches, double minWeightPounds, double maxWeightPounds, int minLifeSpan, int maxLifeSpan, String imgThumb, String imgSourceURL, String imgAttribution, boolean imgCreativeCommons) {
        this.id = id;
        this.breedName = breedName;
        this.breedType = breedType;
        this.breedDescription = breedDescription;
        this.furColor = furColor;
        this.origin = origin;
        this.minHeightInches = minHeightInches;
        this.maxHeightInches = maxHeightInches;
        this.minWeightPounds = minWeightPounds;
        this.maxWeightPounds = maxWeightPounds;
        this.minLifeSpan = minLifeSpan;
        this.maxLifeSpan = maxLifeSpan;
        this.imgThumb = imgThumb;
        this.imgSourceURL = imgSourceURL;
        this.imgAttribution = imgAttribution;
        this.imgCreativeCommons = imgCreativeCommons;
    }

    public DogBreed(DogCommand dog) {
        this.breedName = dog.getBreedName();
        this.breedType = dog.getBreedType();
        this.breedDescription = dog.getBreedDescription();
    }

    public DogBreed(DogUpdateCommand dog) {
        this.id=dog.getId();
        this.breedName = dog.getBreedName();
        this.breedType = dog.getBreedType();
        this.breedDescription = dog.getBreedDescription();
    }

    public DogBreed() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breedName, breedType, breedDescription, furColor, origin, minHeightInches,
                maxHeightInches, minWeightPounds, maxWeightPounds, minLifeSpan, maxLifeSpan, imgThumb,
                imgSourceURL, imgAttribution, imgCreativeCommons);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DogBreed other = (DogBreed) obj;
        return Objects.equals(id, other.id) && Objects.equals(breedName, other.breedName) && Objects.equals(breedType, other.breedType)
                && Objects.equals(breedDescription, other.breedDescription) && Objects.equals(furColor, other.furColor)
                && Objects.equals(origin, other.origin) && minHeightInches == other.minHeightInches
                && maxHeightInches == other.maxHeightInches && minWeightPounds == other.minWeightPounds
                && maxWeightPounds == other.maxWeightPounds && minLifeSpan == other.minLifeSpan
                && maxLifeSpan == other.maxLifeSpan && Objects.equals(imgThumb, other.imgThumb)
                && Objects.equals(imgSourceURL, other.imgSourceURL) && Objects.equals(imgAttribution, other.imgAttribution)
                && imgCreativeCommons == other.imgCreativeCommons;
    }

    @Override
    public String toString() {
        return "DogBreeds{" +
                "id=" + id +
                ", breedName='" + breedName + '\'' +
                ", breedType='" + breedType + '\'' +
                ", breedDescription='" + breedDescription + '\'' +
                ", furColor='" + furColor + '\'' +
                ", origin='" + origin + '\'' +
                ", minHeightInches=" + minHeightInches +
                ", maxHeightInches=" + maxHeightInches +
                ", minWeightPounds=" + minWeightPounds +
                ", maxWeightPounds=" + maxWeightPounds +
                ", minLifeSpan=" + minLifeSpan +
                ", maxLifeSpan=" + maxLifeSpan +
                ", imgThumb='" + imgThumb + '\'' +
                ", imgSourceURL='" + imgSourceURL + '\'' +
                ", imgAttribution='" + imgAttribution + '\'' +
                ", imgCreativeCommons=" + imgCreativeCommons +
                '}';
    }
}

