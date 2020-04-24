package com.recommender.app.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recommender.app.model.Product;
import com.recommender.app.service.ProductService;

@Controller
public class ProductController {
	private final ProductService service;
	private boolean loaded;

	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
		loaded = false;
		loadFromJSON();
	}

	private void addProduct(Product product) {
		service.addProduct(product);
	}
	
	@RequestMapping("/")
	public String showAll(Model model) {
		model.addAttribute("products", service.getAll());
		return "index";
	}
	@RequestMapping("category/videogames")
	public String showVideogames(Model model) {
		model.addAttribute("products", service.getByCategory("Videogames"));
		return "index";
	}
	private void loadFromJSON() {
		if (!loaded) {
			InputStream input = null;
			try {
				input = new FileInputStream(new File(
						"C:/Users/pablo/Desktop/SpringApps/Gift_Recommender/src/main/resources/static/products.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("failed to find the file");
			}
			if (input != null) {
				JSONObject jo = new JSONObject(new JSONTokener(input));
				JSONObject products = jo.getJSONObject("Products");
				for(String productName:products.keySet()) {
					JSONObject product = products.getJSONObject(productName);
					this.addProduct(new Product(null, productName, product.getString("Language"), product.getString("Category"), product.getInt("Price"), product.getInt("Rating"),product.getString("Age")));
				}
					
			}
			loaded = true;
		}
	}
}
