package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.DogBreed;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class MockDogRepository implements DogRepository {

    private final Set<DogBreed> MOCKED_DOGS = Stream.of(
            new DogBreed(1L, "Afador", "Mixed Breed Dogs", "The Afador is a hybrid dog composed of an Afghan Hound and a Labrador Retriever that originated in Alaska around the year 2000.", "black, brown, gray, red, fawn", "Alaska", 20, 29, 50, 75, 10, 12, "https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg", null, null, false),
            new DogBreed(2L, "Labrador Retriever", "Retrievers", "The Labrador Retriever is a medium to large-sized breed of retriever dog that originated in Canada.", "black, yellow, chocolate", "Canada", 21, 24, 55, 80, 10, 12, "https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg", null, null, false),
            new DogBreed(3L, "German Shepherd", "Herding Dogs", "The German Shepherd is a breed of working dog known for its intelligence and versatility. It originated in Germany and is often used as a police, service, and search and rescue dog.", "black, tan, sable, black and tan, black and red", "Germany", 22, 26, 55, 90, 9, 13, "https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg", null, null, false),
            new DogBreed(4L, "Golden Retriever", "Retrievers", "The Golden Retriever is a large-sized breed of retriever dog known for its friendly and intelligent nature. It originated in Scotland and is often used as a hunting and service dog.", "golden, cream", "Scotland", 21, 24, 55, 75, 10, 12, "https://s3.us-west-004.backblazeb2.com/encurate/static/dogbreed/dog-default.jpg", null, null, false)
    ).collect(Collectors.toSet());

    @Override
    public Set<DogBreed> findAll() {
        return MOCKED_DOGS;
    }

    @Override
    public Optional<DogBreed> findByName(String dogName) {
        return MOCKED_DOGS.stream().filter(dog -> Objects.equals(dog.getBreedName(), dogName)).findAny();
    }

    @Override
    public Optional<DogBreed> save(DogBreed dog) {
        if (!MOCKED_DOGS.contains(dog)) {
            MOCKED_DOGS.add(dog);
            return Optional.of(dog);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DogBreed> update(String dogName, DogBreed updatedDog) {
        boolean exists = MOCKED_DOGS.removeIf(dog -> Objects.equals(dog.getBreedName(), dogName));

        if (exists) {
            MOCKED_DOGS.add(updatedDog);
            return Optional.of(updatedDog);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByName(String dogName) {
        MOCKED_DOGS.removeIf(dog -> Objects.equals(dog.getBreedName(), dogName));
    }
}
