package com.recommender.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.recommender.app.dao.ProductDao;
import com.recommender.app.factories.Builder;
import com.recommender.app.model.Product;

@Service
public class ProductService {
	private final ProductDao productDao;
	private final Builder<Product> builder;
	private boolean loaded;

	@Autowired
	public ProductService(@Qualifier("fakeDao") ProductDao productDao,
			@Qualifier("productBuilder") Builder<Product> productBuilder) {
		this.builder = productBuilder;
		this.productDao = productDao;
		loaded = false;
		loadInDatabase();
	}

	private void loadInDatabase() {
		if (!loaded) {
			InputStream input = null;

			try {
				input = new FileInputStream(new File("src/main/resources/static/productsOut.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Cannot find json file");
			}

			if (input != null) {
				JSONObject jo = new JSONObject(new JSONTokener(input));
				JSONArray products = jo.getJSONArray("data");
				for (int i = 1; i < products.length(); ++i)
					productDao.insertProduct(builder.createInstance(jo, i));
				loaded = true;
			}
			
			
		}

	}

	public int addProduct(Product product) {
		return productDao.insertProduct(product);
	}

	public List<Product> getByCategory(String category) {
		List<Product> products = new ArrayList<Product>();
		for(Product prod:productDao.getAll()) {
			if(prod.getCategory().equals(category))
				products.add(prod);
		}
		return products;
	}

	public List<Product> getAll() {
		return productDao.getAll();
	}
}
