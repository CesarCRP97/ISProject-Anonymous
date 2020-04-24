package com.recommender.app.dao;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.recommender.app.model.Product;

public interface ProductDao {
	int insertProduct(UUID id,Product product);
	
	default int insertProduct(Product product) {
		
		UUID id = UUID.randomUUID();
		return insertProduct(id,product);
	}
	List<Product> getProductsWithCategory(String category);
	List<Product> getAll();
}
