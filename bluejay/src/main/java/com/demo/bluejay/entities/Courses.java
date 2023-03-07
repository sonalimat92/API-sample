package com.demo.bluejay.entities;


public class Courses {
	private long id;
	private String product;
	private int price;
	
	public Courses(long id, String product, int price) {
		super();
		this.id = id;
		this.product = product;
		this.price = price;
	}

	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductString() {
		return product;
	}

	public void setProductString(String product ) {
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
