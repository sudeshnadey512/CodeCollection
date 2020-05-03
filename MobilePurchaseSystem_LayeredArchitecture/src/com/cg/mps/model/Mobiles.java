package com.cg.mps.model;

import java.io.Serializable;

public class Mobiles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2201328247338707424L;
	Integer mobileId;
	String name;
	Double price;
	String quantity;
	
	public Mobiles() {
	// TODO Auto-generated constructor stub
	}

	public Mobiles(Integer mobileId, String name, Double price, String quantity) {
		super();
		this.mobileId = mobileId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Integer getMobileId() {
		return mobileId;
	}

	public void setMobileId(Integer mobileId) {
		this.mobileId = mobileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Mobiles [mobileId=" + mobileId + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	

}
