package fr.ima.ihm.service;

import java.util.List;

import fr.ima.ihm.beans.Adherents;

/**
 * Interface d'accès aux données des adhérents
 * @author florance
 *
 */
public interface AdherentService {
	
	/**
	 * Liste les adhérents enregistrés
	 * @return
	 */
	public List<Adherents> lister();

	/**
	 * Permet de créer un adhérent
	 * @param student
	 * @return
	 */
	public Adherents creer(Adherents adherent);

    Adherents findByEmail(String email);
}
