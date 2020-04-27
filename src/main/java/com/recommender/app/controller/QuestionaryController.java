package com.recommender.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recommender.app.exceptions.QuestionaryInitializationFailedException;
import com.recommender.app.model.Questionary;

@Controller
@RequestMapping("/questionary")
public class QuestionaryController {
	
	public QuestionaryController() {
		try {
			Questionary.initializeQuestionaries();
		} catch (QuestionaryInitializationFailedException e) {
			System.out.println("Questionaries failed to start.Cause: "+e.getMessage());
		}
		System.out.println("Controller initialized");
	}
	@GetMapping("")
	public String questionary(Model model) {
		//Questionary defaultQuestionary = new Questionary();
		//model.addAttribute("ageUser", defaultQuestionary.getAgeUser());
		//model.addAttribute("language", defaultQuestionary.getLanguage());
		//model.addAttribute("hobbies", defaultQuestionary.getHobbies());
		//model.addAttribute("upperBound", defaultQuestionary.getUpperBound());
		//model.addAttribute("lowerBound", defaultQuestionary.getLowerBound());
		//model.addAttribute("unique", defaultQuestionary.isUnique());
		model.addAttribute("questionary", new Questionary());
		return "questionary";
	}
}
