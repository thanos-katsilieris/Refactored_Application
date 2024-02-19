package data;

public class Address {
	private String country;
	private String city;
	private String street;
	private int streetNumber;
	private int phoneNumber;


	public Address(String country, String city, String street, int number) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.streetNumber = number;
	}
	
	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
	public String getStreet() {
		return street;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}

