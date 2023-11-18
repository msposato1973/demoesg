package com.esgglobal.demo.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Customer {

	  
	private @Id @GeneratedValue Long id;
	  private String customerRef;
      private String customerName;
      private String addressLine1;
      private String addressLine2;
      private String town;
      private String county;
      private String country;
      private String postcode;
 
      public Customer() {
  		super();
  	 }
      
      public Long getId() {
  		return id;
  	}

  	public String getCustomerRef() {
  		return customerRef;
  	}

  	public String getCustomerName() {
  		return customerName;
  	}

  	public String getAddressLine1() {
  		return addressLine1;
  	}

  	public String getAddressLine2() {
  		return addressLine2;
  	}

  	public String getTown() {
  		return town;
  	}

  	public String getCounty() {
  		return county;
  	}

  	public String getCountry() {
  		return country;
  	}

  	public String getPostcode() {
  		return postcode;
  	}

  	public void setId(Long id) {
  		this.id = id;
  	}

  	public void setCustomerRef(String customerRef) {
  		this.customerRef = customerRef;
  	}

  	public void setCustomerName(String customerName) {
  		this.customerName = customerName;
  	}

  	public void setAddressLine1(String addressLine1) {
  		this.addressLine1 = addressLine1;
  	}

  	public void setAddressLine2(String addressLine2) {
  		this.addressLine2 = addressLine2;
  	}

  	public void setTown(String town) {
  		this.town = town;
  	}

  	public void setCounty(String county) {
  		this.county = county;
  	}

  	public void setCountry(String country) {
  		this.country = country;
  	}

  	public void setPostcode(String postcode) {
  		this.postcode = postcode;
  	}

  	
  	
	public Customer(String customerRef, String customerName, String addressLine1, 
			String addressLine2, String town,
			String county, String country, String postcode) {
		super();
		this.customerRef = customerRef;
		this.customerName = customerName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.town = town;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, country, county, customerName, customerRef, id, postcode, town);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(country, other.country) && Objects.equals(county, other.county)
				&& Objects.equals(customerName, other.customerName) && Objects.equals(customerRef, other.customerRef)
				&& Objects.equals(id, other.id) && Objects.equals(postcode, other.postcode)
				&& Objects.equals(town, other.town);
	}

  	

}
