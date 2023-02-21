package fr.ima.controller.entities.dto;

import java.util.Date;

import fr.ima.controller.entities.dao.Adresse;

public class Adherents {
	
	
	private int identifiant_adherent;



	private String gender;
	private String firstName;
	private String lastName;
	private Date birthDate; 
	
	private boolean residentFrench; 
	private boolean printListing;

	private String e_mail;

	private String hash_pass;

	
	private Address adress;

	public int getIdentifiant_adherent() {
		return identifiant_adherent;
	}

	public void setIdentifiant_adherent(int identifiant_adherent) {
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


	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getHash_pass() {
		return hash_pass;
	}

	public void setHash_pass(String hash_pass) {
		this.hash_pass = hash_pass;
	}

	
	
}
