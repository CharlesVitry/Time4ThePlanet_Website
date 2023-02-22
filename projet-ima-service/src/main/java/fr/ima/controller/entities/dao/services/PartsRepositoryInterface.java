package fr.ima.controller.entities.dao.services;

import fr.ima.controller.entities.dao.Adherent;
import fr.ima.controller.entities.dao.Parts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartsRepositoryInterface extends CrudRepository<Parts, Long> {
    // Requete de type HQL
    // @Query permet de définir une requete HQL dans l'interface et évite de définir une classe d'implémentation
    @Query("select part from fr.ima.controller.entities.dao.Parts part where part.id = ?1")
    public Parts findByCode(int code);

    List<Parts> findByAdherent(Adherent adherent);
}
