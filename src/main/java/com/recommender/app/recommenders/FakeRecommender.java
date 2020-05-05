package com.recommender.app.recommenders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.recommender.app.exceptions.RecommendationNotFoundException;
import com.recommender.app.model.Product;

public class FakeRecommender  implements Recommender{

	private int age;
	private int lowerBound;
	private int upperBound;
	private boolean unique;
	public FakeRecommender(int age,int lowerBound,int upperBound,boolean unique) {
		this.age = age;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.unique = unique;
	}
	@Override
	public List<Product> recommend(List<Product> products) {
		List<Product> output = new ArrayList<Product>();
		for(Product product:products) {
			if(product.getRating()>3&&Integer.parseInt(product.getAge().trim().split(" ")[1])<=age&&product.getPrice()>=lowerBound&&product.getPrice()<=upperBound) {
				output.add(product);
			}
		}
		if(output.size()!=0&&unique) {
			Random rand = new Random();
			Product selected = output.get(rand.nextInt(output.size()));
			output.clear();
			output.add(selected);
		}
		return output;
	}

	

	

	
}
