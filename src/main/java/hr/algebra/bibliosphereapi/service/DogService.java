package hr.algebra.bibliosphereapi.service;

import hr.algebra.bibliosphereapi.payload.request.DogCommand;
import hr.algebra.bibliosphereapi.payload.request.DogUpdateCommand;
import hr.algebra.bibliosphereapi.dto.DogDTO;

import java.util.List;
import java.util.Optional;

public interface DogService {

    List<DogDTO> findAll();

    Optional<DogDTO> findByName(String dogName);

    Optional<DogDTO> save(DogCommand DogCommand);

    Optional<DogDTO> update(String dogName, DogUpdateCommand updatedDogCommand);

    void deleteByName(String dogName);

}
