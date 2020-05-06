package com.recommender.app.factories;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.recommender.app.model.Product;

@Component("productBuilder")
public class ProductBuilder extends Builder<Product> {
	public ProductBuilder() {
		super("Products");
	}

	@Override
	protected Product createTheInstance(JSONObject product) {
		
		return new Product(UUID.randomUUID(),product.getString("Name"), product.getString("Language"), product.getString("Category"),
				product.getInt("Price"), product.getInt("Rating"), product.getString("Age"));

	}
}
