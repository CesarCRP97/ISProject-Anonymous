package com.recommender.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.recommender.app.model.Product;
@Repository("fakeDao")
public class FakeProductDataAccessService implements ProductDao{
	private static List<Product> DB = new ArrayList<Product>();
	@Override
	public int insertProduct(UUID id, Product product) {
		DB.add(new Product(id,product.getName(),product.getLanguage(),product.getCategory(),product.getPrice(),product.getRating(),product.getAge()));
		return 1;
	}
	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		for(Product prod:DB) {
			products.add(prod);
		}
		return products;
	}
	
	@Override
	public int insertProduct(Product product) {
		// TODO Auto-generated method stub
		DB.add(product);
		return 1;
	}

}
