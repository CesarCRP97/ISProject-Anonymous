package com.recommender.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.recommender.app.exceptions.QuestionaryInitializationFailedException;
import com.recommender.app.model.Questionary;

@RestController
@RequestMapping("/questionary")
public class QuestionaryController {
	
	public QuestionaryController() {
		try {
			Questionary.initializeQuestionaries();
		} catch (QuestionaryInitializationFailedException e) {
			System.out.println("Questionaries failed to start.Cause: "+e.getMessage());
		}
	}
	@GetMapping("")
	public ModelAndView questionary() {
		ModelAndView modelAndView = new ModelAndView("questionary");
		modelAndView.addObject("questionary", new Questionary());
		return modelAndView;
	}
}
