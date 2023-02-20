package fr.ima.controller.entities.dao;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity // cette classe a son reflet dans une BDD
public class Etudiant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private boolean erasmus;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adresse_id")
	private Adresse adresse;
	
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
	public boolean isErasmus() {
		return erasmus;
	}
	public void setErasmus(boolean erasmus) {
		this.erasmus = erasmus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
