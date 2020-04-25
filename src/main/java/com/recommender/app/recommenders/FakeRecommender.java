package com.recommender.app.recommenders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.recommender.app.exceptions.RecommendationNotFoundException;
import com.recommender.app.model.Product;

public class FakeRecommender {
	public static List<Product> recommendByCategory(boolean returnUnique,List<Product> productsOfCategory,int ageSelected) throws RecommendationNotFoundException {
		List<Product> selectedProducts = new ArrayList<Product>();
		boolean ageCorrect = false;
		for(Product prod:productsOfCategory) {
			if(Integer.parseInt(prod.getAge().trim().split(" ")[1])<=ageSelected&&(prod.getRating()>=4||prod.getPrice()<35)) {
				selectedProducts.add(prod);
				ageCorrect = true;
			}
		}
		if(!ageCorrect&&selectedProducts.size()==0)
			throw new RecommendationNotFoundException("You're too young for these items"); 
		if(returnUnique&&selectedProducts.size()>0) {
			Product selectedProduct;
			Random rand = new Random();
			selectedProduct = selectedProducts.get(rand.nextInt(selectedProducts.size()));
			selectedProducts.clear();
			selectedProducts.add(selectedProduct);
		}
		return selectedProducts;
	}
}
