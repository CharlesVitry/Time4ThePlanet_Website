package fr.ima.controller;

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

import fr.ima.controller.entities.dao.Adresse;
import fr.ima.controller.entities.dao.Etudiant;
import fr.ima.controller.entities.dao.Promotion;
import fr.ima.controller.entities.dao.services.PromotionRepositoryInterface;
import fr.ima.controller.entities.dao.services.StudentRepositoryInterface;
import fr.ima.controller.entities.dto.Address;
import fr.ima.controller.entities.dto.Student;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepositoryInterface studentRepository;

	@Autowired
	private PromotionRepositoryInterface promotionRepository;	
	
	@GetMapping("/students/{promo}")
	public HttpEntity<Student[]> getStudents(@PathVariable String promo) {
		return null;
	}
	
	@GetMapping("/student/{id}")
	public HttpEntity<Student> getStudentDetail(@PathVariable String id) {
		
		Optional<Etudiant> e = studentRepository.findById(new Long(id));

		// La doc sur les Optional : https://www.javatpoint.com/java-8-optional
		if (!e.isPresent()) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<Student>(createDTOFromEtudiantDAO(e.get()), HttpStatus.OK);
	}	
	
	@PostMapping("/student/create")
	public HttpEntity<Student> createStudent(@RequestBody Student student) {  // Créer un objet Student ici pour passer les valeurs
		
		if (student.getPromo() == null || !StringUtils.hasText(student.getPromo().getCode())) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		if (!StringUtils.hasText(student.getLastName()) || !StringUtils.hasText(student.getFirstName())) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		Etudiant etudiantDAO = createDAOFromStudentDTO(student);
		
		studentRepository.save(etudiantDAO);
		
		return new ResponseEntity<Student>(createDTOFromEtudiantDAO(etudiantDAO), HttpStatus.CREATED);
	}		
	
	@DeleteMapping("/student/delete/{id}")
	public HttpEntity deleteStudent(@PathVariable String id) {
		return null;
	}
	
	@PutMapping("/student/update")
	public HttpEntity<Student> updateStudent(@RequestBody Student student) {
		return null;
	}	

	/**
	 * Convertit un objet Student DTO en Etudiant DAO
	 * @param student
	 * @return
	 */
	private Etudiant createDAOFromStudentDTO(Student student) {
		Etudiant etudiant = new Etudiant();
		etudiant.setPrenom(student.getFirstName());
		etudiant.setNom(student.getLastName());
		etudiant.setDateNaissance(student.getBirthDate());
		etudiant.setErasmus(student.isErasmus());
		
		// Nous exécutons une requête en base de données pour récupérer une promotion
		// en BDD si celle_ci existe déjà
		Promotion promoDAO = promotionRepository.findByCode(student.getPromo().getCode());
		if (promoDAO == null) {
			promoDAO = new Promotion();
			promoDAO.setCode(student.getPromo().getCode());
			promoDAO.setLibelle(student.getPromo().getLabel());
		}
		etudiant.setPromotion(promoDAO);
		
		// Créé une nouvelle instance d'adresse en ne récupérant pas
		// une adresse déjàa existante c'est à dire en n'ayant pas une DAO Adresse avec un id renseigné
		Adresse adresseDAO = new Adresse();
		adresseDAO.setRue(student.getAddress().getStreet());
		adresseDAO.setCodePostal(student.getAddress().getPostCode());
		adresseDAO.setVille(student.getAddress().getCity());
		etudiant.setAdresse(adresseDAO);
		
		return etudiant;
	}
	
	/**
	 * Convertit un objet Etudiant DAO en Student DTO
	 * @param student
	 * @return
	 */
	private Student createDTOFromEtudiantDAO(Etudiant etudiant) {
		
		Student student = new Student();
		
		student.setFirstName(etudiant.getPrenom());
		student.setLastName(etudiant.getNom());
		student.setBirthDate(etudiant.getDateNaissance());
		student.setErasmus(etudiant.isErasmus());
		student.setINE(Long.toString(etudiant.getId().longValue()));

		fr.ima.controller.entities.dto.Promotion promoDTO = new fr.ima.controller.entities.dto.Promotion();
		promoDTO.setCode(etudiant.getPromotion().getCode());
		promoDTO.setLabel(etudiant.getPromotion().getLibelle());
		student.setPromo(promoDTO);
		
		Address address = new Address();
		address.setStreet(etudiant.getAdresse().getRue());
		address.setPostCode(etudiant.getAdresse().getCodePostal());
		address.setCity(etudiant.getAdresse().getVille());
		student.setAddress(address);
		
		return student;
	}	
}
