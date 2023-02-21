package fr.ima.ihm.service.dto;

/**
 * Objet de type ima-service pour appeler le service
 * @author florance
 *
 */
public class Address {
	
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
}
