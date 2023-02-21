package fr.ima.controller.entities.dao;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Adherent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String civilite;
	private String prenom;
	private String nom;
	private Date dateNaissance; 
	
	private boolean residentFrancais; 
	private boolean affichageLister;
	
	private String email;

	private String hash_mdp;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adresse_id")
	private Adresse adresse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public boolean isResidentFrancais() {
		return residentFrancais;
	}

	public void setResidentFrancais(boolean residentFrancais) {
		this.residentFrancais = residentFrancais;
	}

	public boolean isAffichageLister() {
		return affichageLister;
	}

	public void setAffichageLister(boolean affichageLister) {
		this.affichageLister = affichageLister;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHash_mdp() {
		return hash_mdp;
	}

	public void setHash_mdp(String hash_mdp) {
		this.hash_mdp = hash_mdp;
	}
	
	
}
