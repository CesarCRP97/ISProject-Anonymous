package com.recommender.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.recommender.app.dao.ProductDao;
import com.recommender.app.model.Product;
@Service
public class ProductService {
	private final ProductDao productDao;
	
	@Autowired
	public ProductService(@Qualifier("fakeDao")ProductDao productDao) {
		this.productDao = productDao;
	}
	public int addProduct(Product product) {
		return productDao.insertProduct(product);
	}
	public List<Product> getByCategory(String category){
		return productDao.getProductsWithCategory(category);
	}
	public List<Product> getAll() {
		return productDao.getAll();
	}
}
