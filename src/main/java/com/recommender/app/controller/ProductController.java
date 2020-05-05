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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



import com.recommender.app.exceptions.RecommendationNotFoundException;
import com.recommender.app.model.Product;
import com.recommender.app.model.Questionary;
import com.recommender.app.model.forms.QuestionaryForm;
import com.recommender.app.recommenders.FakeRecommender;
import com.recommender.app.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	private final ProductService service;
	private boolean loaded;

	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
		loaded = false;
		try {
			loadFromJSON();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to load json file");
		}
	}

	@GetMapping("")
	public ModelAndView showAll() {
		ModelAndView modelAndView = new ModelAndView();
		//model.addAttribute("products", service.getAll());
		modelAndView.setViewName("index");
		modelAndView.addObject("products",service.getAll());
		return modelAndView;
	}
	@GetMapping("/json")
	public List<Product> showAllJson() {
		return service.getAll();
	}
	@GetMapping("/categories/{category}/json")
	public List<Product> showCategoryJson(@PathVariable(required = true)String category) {
		return service.getByCategory(category);

	}

	@GetMapping("recommend")

	public ModelAndView showRecommendation(@ModelAttribute QuestionaryForm questionary) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("recommendation");
		if (questionary.isValid()) {
			modelAndView.addObject("age", questionary.getAgeUser());
			modelAndView.addObject("category", questionary.getHobbiesCategories().toString());
			List<Product> products = new ArrayList<Product>();
			for (String category : questionary.getHobbiesCategories())
				products.addAll(service.getByCategory(category));
			List<Product> recommended = new ArrayList<Product>();
			String message = "Recommendation found!";
			try {
				recommended = FakeRecommender.recommendSeveralCategories(questionary.isUnique(), products,
						questionary.getAgeUser(), questionary.getUpperBound(), questionary.getLowerBound());
			} catch (RecommendationNotFoundException e) {
				// TODO Auto-generated catch block
				message = e.getMessage();
			}
			modelAndView.addObject("products", recommended);
			modelAndView.addObject("message", message);
			return modelAndView;
		}
		else {
			modelAndView.setViewName("redirect:/questionary/invalid");
			modelAndView.addObject("message", "Invalid form");
			modelAndView.addObject("questionary",questionary);
			return modelAndView;
		}

	}

	private void loadFromJSON() throws FileNotFoundException {
		if (!loaded) {
			InputStream input = null;

			input = new FileInputStream(new File("src/main/resources/static/products.json"));

			if (input != null) {
				JSONObject jo = new JSONObject(new JSONTokener(input));
				JSONObject products = jo.getJSONObject("Products");
				for (String productName : products.keySet()) {
					JSONObject product = products.getJSONObject(productName);

					service.addProduct(new Product(null, productName, product.getString("Language"), product.getString("Category"), product.getInt("Price"), product.getInt("Rating"),product.getString("Age")));

				}
				loaded = true;
			}

		}
	}
}
