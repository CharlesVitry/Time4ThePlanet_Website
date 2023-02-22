package fr.ima.ihm.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import fr.ima.ihm.service.dto.Address;

public class Adherents {




	private String gender;
	private String firstName;
	private String lastName;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String birthDate; 
	
	private boolean residentFrench; 
	private boolean printListing;

	private String e_mail;


	
	private String street;
	private String postCode;
	private String city;
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
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


}
