package com.recommender.app.dao;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.recommender.app.model.Product;

public interface ProductDao {
	int insertProduct(UUID id,Product product);
	int insertProduct(Product product);
	List<Product> getAll();
}
