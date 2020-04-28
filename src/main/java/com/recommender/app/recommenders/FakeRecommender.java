package com.recommender.app.recommenders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.recommender.app.exceptions.RecommendationNotFoundException;
import com.recommender.app.model.Product;

public class FakeRecommender {
	private static HashMap<String, Integer> goodPrices = new HashMap<String, Integer>();

	public static void initializePrices() {
		goodPrices.clear();
		goodPrices.put("Electronics", 200);
		goodPrices.put("Movies", 5);
		goodPrices.put("Beer", 1);
		goodPrices.put("Photography", 50);
		goodPrices.put("Videogames", 20);
		goodPrices.put("Books", 10);
	}

	public static List<Product> recommendSeveralCategories(boolean returnUnique, List<Product> products, int age,
			int upperBound, int lowerBound) throws RecommendationNotFoundException {
		List<Product> selectedProducts = new ArrayList<Product>();
		boolean ageCorrect = false;
		boolean priceCorrect = false;
		for (Product prod : products) {
			if (Integer.parseInt(prod.getAge().trim().split(" ")[1]) <= age)
				ageCorrect = true;
			if(prod.getPrice()>=lowerBound&&prod.getPrice() <= upperBound)
				priceCorrect = true;
			if (Integer.parseInt(prod.getAge().trim().split(" ")[1]) <= age
					&& (prod.getRating() >= 4 &&( prod.getPrice()>=lowerBound&&prod.getPrice() <= upperBound))) {
				selectedProducts.add(prod);
			}
		}
		if (selectedProducts.size() == 0) {
			if (!ageCorrect&&!priceCorrect)
				throw new RecommendationNotFoundException("You're too young and you should consider your budget");
			else if (!priceCorrect)
				throw new RecommendationNotFoundException("You have to modify your budget");
			else
				throw new RecommendationNotFoundException("You're too young for these items");
		}
		if (returnUnique && selectedProducts.size() > 0) {
			Product selectedProduct;
			Random rand = new Random();
			selectedProduct = selectedProducts.get(rand.nextInt(selectedProducts.size()));
			selectedProducts.clear();
			selectedProducts.add(selectedProduct);
		}
		return selectedProducts;
	}

	public static List<Product> recommendByCategory(boolean returnUnique, List<Product> productsOfCategory,
			int ageSelected, String category) throws RecommendationNotFoundException {
		List<Product> selectedProducts = new ArrayList<Product>();
		boolean ageCorrect = false;
		for (Product prod : productsOfCategory) {
			if (Integer.parseInt(prod.getAge().trim().split(" ")[1]) <= ageSelected
					&& (prod.getRating() >= 4 || prod.getPrice() <= goodPrices.get(category))) {
				selectedProducts.add(prod);
				ageCorrect = true;
			}
		}
		if (!ageCorrect && selectedProducts.size() == 0)
			throw new RecommendationNotFoundException("You're too young for these items");
		if (returnUnique && selectedProducts.size() > 0) {
			Product selectedProduct;
			Random rand = new Random();
			selectedProduct = selectedProducts.get(rand.nextInt(selectedProducts.size()));
			selectedProducts.clear();
			selectedProducts.add(selectedProduct);
		}
		return selectedProducts;
	}
}
