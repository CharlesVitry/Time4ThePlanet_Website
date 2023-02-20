package fr.ima.ihm.service;

import java.util.List;

import fr.ima.ihm.beans.Student;


/**
 * Interface d'accès aux données des étudiants
 * @author florance
 *
 */
public interface EtudiantService {
	
	/**
	 * Liste les étudiants enregistrés
	 * @return
	 */
	public List<Student> lister();

	/**
	 * Permet de créer un étudiant
	 * @param student
	 * @return
	 */
	public Student creer(Student student);

}
