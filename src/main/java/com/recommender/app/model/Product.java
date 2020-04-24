package com.recommender.app.model;

import java.util.UUID;

public class Product {
	private final UUID id;
	private final String name;
	private final String category;
	private final String language;
	private int price;
	private int rating;
	private String age;
	public Product(UUID id,String name,String language,String category,int price,int rating,String age) {
		this.id = id;
		this.language = language;
		this.category = category;
		this.price = price;
		this.rating = rating;
		this.age = age;
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age ="+ " +((Integer)age).toString();
	}
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public String getLanguage() {
		return language;
	}
	
}
