package fr.ima.ihm.beans;

import java.util.Date;
import java.util.Date;

public class Adherents {

	private String gender;
	private String firstName;
	private String lastName;
	private Date birthDate;

	private boolean residentFrench;
	private boolean printListing;

	private String e_mail;



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
