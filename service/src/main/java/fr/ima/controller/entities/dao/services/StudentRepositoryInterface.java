package fr.ima.controller.entities.dao.services;

import org.springframework.data.repository.CrudRepository;

import fr.ima.controller.entities.dao.Etudiant;

public interface StudentRepositoryInterface extends CrudRepository<Etudiant, Long> {


}
