package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="shipping_addresses")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ShippingAddress extends PersistenceEntity {

	private static final long serialVersionUID = -8152930414651177366L;
	
	private String street;
	private String number;
	private String city;
	private String province;
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
