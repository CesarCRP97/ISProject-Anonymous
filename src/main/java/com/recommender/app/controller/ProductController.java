package com.recommender.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recommender.app.exceptions.RecommendationNotFoundException;
import com.recommender.app.model.Product;
import com.recommender.app.model.Questionary;
import com.recommender.app.recommenders.FakeRecommender;
import com.recommender.app.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
	private final ProductService service;
	private boolean loaded;
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
		loaded = false;
		FakeRecommender.initializePrices();
		try {
			loadFromJSON();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to load json file");
		}
	}

	private void addProduct(Product product) {
		service.addProduct(product);
	}
	
	@GetMapping("")
	public String showAll(Model model) {
		model.addAttribute("products", service.getAll());
		return "index";
	}
	
	@GetMapping("recommend")
	public String showRecommendation(@ModelAttribute Questionary questionary,Model model) {
		Random rand = new Random();
		model.addAttribute("age", questionary.getAgeUser());
		String category =questionary.getHobbiesCategories().get(rand.nextInt(questionary.getHobbiesCategories().size()));
		model.addAttribute("category", category);
		List<Product> products = new ArrayList<Product>();
		String message = "Recommendation found!";
		try {
			products = FakeRecommender.recommendByCategory(questionary.isUnique(), service.getByCategory(category), questionary.getAgeUser(),category);
		} catch (RecommendationNotFoundException e) {
			// TODO Auto-generated catch block
			message = e.getMessage();
		}
		model.addAttribute("products", products);
		model.addAttribute("message", message);
		return "recommendation";
	}
	private void loadFromJSON() throws FileNotFoundException {
		if (!loaded) {
			InputStream input = null;
			
				input = new FileInputStream(new File("src/main/resources/static/products.json"));
			
			if (input != null) {
				JSONObject jo = new JSONObject(new JSONTokener(input));
				JSONObject products = jo.getJSONObject("Products");
				for(String productName:products.keySet()) {
					JSONObject product = products.getJSONObject(productName);
					this.addProduct(new Product(null, productName, product.getString("Language"), product.getString("Category"), product.getInt("Price"), product.getInt("Rating"),product.getString("Age")));
				}
				loaded = true;	
			}
			
		}
	}
}
