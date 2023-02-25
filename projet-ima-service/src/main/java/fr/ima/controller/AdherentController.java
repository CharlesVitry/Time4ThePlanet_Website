package fr.ima.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ima.controller.entities.dao.Adherent;
import fr.ima.controller.entities.dao.Adresse;
import fr.ima.controller.entities.dao.services.AdherentRepositoryInterface;
import fr.ima.controller.entities.dto.Address;
import fr.ima.controller.entities.dto.Adherents;

@RestController
public class AdherentController {
	
	@Autowired
	private AdherentRepositoryInterface adherentRepository;

	
	@GetMapping("/adherent/list")
	public HttpEntity<List<Adherents>> getAdherent() {
		
		Iterable<Adherent> e = adherentRepository.findAll();
		
		List<Adherents> list = new ArrayList<Adherents>();
		for (Iterator<Adherent> iterator = e.iterator(); iterator.hasNext();) {
			list.add(createDTOFromAdherentDAO(iterator.next()));
		}
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/adherent/{email}")
	public HttpEntity<Adherents> getAdherentsDetail(@PathVariable String email) {
		
		Optional<Adherent> e = Optional.ofNullable(adherentRepository.findByEmail(email));
		// La doc sur les Optional : https://www.javatpoint.com/java-8-optional
		if (!e.isPresent()) {
			return new ResponseEntity<Adherents>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Adherents>(createDTOFromAdherentDAO(e.get()), HttpStatus.OK);


	}


	
	@PostMapping("/adherent/create")
	public HttpEntity<Adherents> createAdherents(@RequestBody Adherents adherents) {  // Créer un objet Adherents ici pour passer les valeurs
		
		
		if (!StringUtils.hasText(adherents.getLastName()) || !StringUtils.hasText(adherents.getFirstName())) {
			return new ResponseEntity<Adherents>(HttpStatus.BAD_REQUEST);
		}
		
		Adherent adherentDAO = createDAOFromAdherentsDTO(adherents);
		
		adherentRepository.save(adherentDAO);
		
		return new ResponseEntity<Adherents>(createDTOFromAdherentDAO(adherentDAO), HttpStatus.CREATED);
	}

	@DeleteMapping("/adherent/{email}")
	public ResponseEntity<Void> deleteAdherent(@PathVariable String email) {
		Optional<Adherent> adherent = Optional.ofNullable(adherentRepository.findByEmail(email));
		if (!adherent.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		adherentRepository.delete(adherent.get());
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/adherent/{email}")
	public ResponseEntity<Adherents> updateAdherent(@PathVariable String email, @RequestBody Adherents adherents) {
		Optional<Adherent> existingAdherent = Optional.ofNullable(adherentRepository.findByEmail(email));
		if (!existingAdherent.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Adherent updatedAdherent = updateAdherentFromDTO(existingAdherent.get(), adherents);
		adherentRepository.save(updatedAdherent);

		return ResponseEntity.ok(createDTOFromAdherentDAO(updatedAdherent));
	}

	private Adherent updateAdherentFromDTO(Adherent existingAdherent, Adherents updatedAdherent) {
		existingAdherent.setCivilite(updatedAdherent.getGender());
		existingAdherent.setPrenom(updatedAdherent.getFirstName());
		existingAdherent.setNom(updatedAdherent.getLastName());
		existingAdherent.setDateNaissance(updatedAdherent.getBirthDate());
		existingAdherent.setResidentFrancais(updatedAdherent.isResidentFrench());
		existingAdherent.setAffichageLister(updatedAdherent.isPrintListing());

		if (existingAdherent.getAdresse() == null) {
			existingAdherent.setAdresse(new Adresse());
		}

		existingAdherent.getAdresse().setRue(updatedAdherent.getAdress().getStreet());
		existingAdherent.getAdresse().setCodePostal(updatedAdherent.getAdress().getPostCode());
		existingAdherent.getAdresse().setVille(updatedAdherent.getAdress().getCity());
		return existingAdherent;
	}

	/**
	 * Convertit un objet Adherents DTO en Adherent DAO
	 * @param adherents
	 * @return
	 */
	public Adherent createDAOFromAdherentsDTO(Adherents adherents) {
		Adherent adherent = new Adherent();
		adherent.setPrenom(adherents.getFirstName());
		adherent.setNom(adherents.getLastName());
		adherent.setDateNaissance(adherents.getBirthDate());
		adherent.setResidentFrancais(adherents.isResidentFrench());
		adherent.setAffichageLister(adherents.isPrintListing());
		adherent.setEmail(adherents.getE_mail());
		adherent.setCivilite(adherents.getGender());

		// Créé une nouvelle instance d'adresse sans se soucier
		// de savoir si une même adresse existe déjà
		if (adherents.getAdress() != null) {
			Adresse adresseDAO = new Adresse();
			adresseDAO.setRue(adherents.getAdress().getStreet());
			adresseDAO.setCodePostal(adherents.getAdress().getPostCode());
			adresseDAO.setVille(adherents.getAdress().getCity());
			adherent.setAdresse(adresseDAO);
		}
		
		return adherent;
	}
	
	/**
	 * Convertit un objet Adherent DAO en Adherents DTO
	 * @param adherent
	 * @return
	 */
	private Adherents createDTOFromAdherentDAO(Adherent adherent) {
		
		Adherents adherents = new Adherents();
		
		adherents.setFirstName(adherent.getPrenom());
		adherents.setLastName(adherent.getNom());
		adherents.setBirthDate(adherent.getDateNaissance());
		adherents.setPrintListing(adherent.isAffichageLister());
		adherents.setResidentFrench(adherent.isResidentFrancais());
		adherents.setE_mail(adherent.getEmail());
		adherents.setGender(adherent.getCivilite());

		if (adherent.getAdresse() != null) {
			Address address = new Address();
			address.setStreet(adherent.getAdresse().getRue());
			address.setPostCode(adherent.getAdresse().getCodePostal());
			address.setCity(adherent.getAdresse().getVille());
			adherents.setAdress(address);
		}

		
		return adherents;
	}	
}
