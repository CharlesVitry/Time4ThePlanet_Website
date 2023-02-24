package fr.ima.controller.entities.dao.services;

import org.springframework.data.repository.CrudRepository;

import fr.ima.controller.entities.dao.Adherent;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdherentRepositoryInterface extends CrudRepository<Adherent, String> {


    Adherent findByEmail(@Param("email") String email);
}
