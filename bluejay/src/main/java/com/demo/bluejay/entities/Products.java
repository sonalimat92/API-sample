package com.demo.bluejay.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Products {
	
	@Id
	private long id;
	private String product;
	private int price;
	
	public Products(long id, String product, int price) {
		super();
		this.id = id;
		this.product = product;
		this.price = price;
	}

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Courses [id=" + id + ", product=" + product + ", price=" + price + "]";
	}
	 

}
