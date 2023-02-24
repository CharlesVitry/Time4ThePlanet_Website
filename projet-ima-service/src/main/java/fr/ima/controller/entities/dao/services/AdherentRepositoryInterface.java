package fr.ima.controller.entities.dao.services;

import org.springframework.data.repository.CrudRepository;

import fr.ima.controller.entities.dao.Adherent;

import java.util.Optional;

public interface AdherentRepositoryInterface extends CrudRepository<Adherent, String> {


    Adherent findByEmail(String email);
}
