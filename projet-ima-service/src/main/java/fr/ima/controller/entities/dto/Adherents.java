package fr.ima.controller.entities.dto;

import java.util.Date;

import fr.ima.controller.entities.dao.Adresse;

public class Adherents {
	
	
	private String identifiant_adherent;
	private String firstName;
	private String lastName;
	private Date birthDate; 
	
	private boolean residentFrench; 
	private boolean printListing;
	
	private int numberParts; 
	
	private Address adress;

	public String getIdentifiant_adherent() {
		return identifiant_adherent;
	}

	public void setIdentifiant_adherent(String identifiant_adherent) {
		this.identifiant_adherent = identifiant_adherent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isResidentFrench() {
		return residentFrench;
	}

	public void setResidentFrench(boolean residentFrench) {
		this.residentFrench = residentFrench;
	}

	public boolean isPrintListing() {
		return printListing;
	}

	public void setPrintListing(boolean printListing) {
		this.printListing = printListing;
	}

	public int getNumberParts() {
		return numberParts;
	}

	public void setNumberParts(int numberParts) {
		this.numberParts = numberParts;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	
	
}
