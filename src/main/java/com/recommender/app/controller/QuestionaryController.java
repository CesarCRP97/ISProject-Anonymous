package com.recommender.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recommender.app.exceptions.QuestionaryInitializationFailedException;
import com.recommender.app.model.Questionary;
import com.recommender.app.model.forms.QuestionaryForm;

@RestController
@RequestMapping("/questionary")
public class QuestionaryController {
	
	public QuestionaryController() {
		try {
			QuestionaryForm.initializeQuestionaries();
		} catch (QuestionaryInitializationFailedException e) {
			System.out.println("Questionaries failed to start.Cause: "+e.getMessage());
		}
	}
	@GetMapping("")
	public ModelAndView questionary() {
		ModelAndView model = new ModelAndView();
		model.setViewName("questionary");
		model.addObject("questionary", new QuestionaryForm());
		model.addObject("message", "Please complete this form");
		
		return model;
	}@GetMapping("/invalid")
	public ModelAndView invalidQuestionary(@RequestParam(required = true,name="message")String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("questionary");
		modelAndView.addObject("message",message);
		modelAndView.addObject("questionary", new QuestionaryForm());
		return modelAndView;
	}
	
	
}
