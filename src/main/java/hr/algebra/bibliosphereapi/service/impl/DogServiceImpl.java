package hr.algebra.bibliosphereapi.service.impl;

import hr.algebra.bibliosphereapi.payload.request.DogCommand;
import hr.algebra.bibliosphereapi.payload.request.DogUpdateCommand;
import hr.algebra.bibliosphereapi.models.DogBreed;
import hr.algebra.bibliosphereapi.dto.DogDTO;
import hr.algebra.bibliosphereapi.repository.DogRepository;
import hr.algebra.bibliosphereapi.service.DogService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository DogRepository) {
        this.dogRepository = DogRepository;
    }

    @Override
    public List<DogDTO> findAll() {
        return dogRepository.findAll().stream().map(DogDTO::new).toList();
    }

    @Override
    public Optional<DogDTO> findByName(String dogName) {
        return dogRepository.findByName(dogName).map(DogDTO::new);
    }

    @Override
    public Optional<DogDTO> save(DogCommand DogCommand) {
        return dogRepository
                .save(new DogBreed(DogCommand))
                .map(DogDTO::new);
    }

    @Override
    public Optional<DogDTO> update(String dogName, DogUpdateCommand updatedDogCommand) {
        return dogRepository
                .update(dogName, new DogBreed(updatedDogCommand))
                .map(Dog -> new DogDTO(dogName, Dog));
    }

    @Transactional
    @Override
    public void deleteByName(String dogName) {
        dogRepository.deleteByName(dogName);
    }
}
