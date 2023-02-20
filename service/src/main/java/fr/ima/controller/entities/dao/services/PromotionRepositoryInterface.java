package fr.ima.controller.entities.dao.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.ima.controller.entities.dao.Promotion;

public interface PromotionRepositoryInterface extends CrudRepository<Promotion, Long> {

	// Requete de type HQL
	// @Query permet de définir une requete HQL dans l'interface et évite de définir une classe d'implémentation
	@Query("select promo from fr.ima.controller.entities.dao.Promotion promo where promo.code = ?1")
	public Promotion findByCode(String code);	
	
	
}
