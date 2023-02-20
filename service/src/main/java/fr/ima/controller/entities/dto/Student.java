package fr.ima.controller.entities.dto;

import java.util.Date;

public class Student {
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	private boolean erasmus;
	private String INE;
	
	private Address address;
	private Promotion promo;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Promotion getPromo() {
		return promo;
	}
	public void setPromo(Promotion promo) {
		this.promo = promo;
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
	public boolean isErasmus() {
		return erasmus;
	}
	public void setErasmus(boolean erasmus) {
		this.erasmus = erasmus;
	}
	public String getINE() {
		return INE;
	}
	public void setINE(String iNE) {
		INE = iNE;
	}
}
