package com.recommender.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recommender.app.exceptions.QuestionaryInitializationFailedException;
import com.recommender.app.model.Questionary;
import com.recommender.app.model.forms.QuestionaryForm;

@Controller
@RequestMapping("/questionary")
public class QuestionaryController {
	
	public QuestionaryController() {
		try {
			QuestionaryForm.initializeQuestionaries();
		} catch (QuestionaryInitializationFailedException e) {
			System.out.println("Questionaries failed to start.Cause: "+e.getMessage());
		}
		System.out.println("Controller initialized");
	}
	@GetMapping(value= {"","/{message}"})
	public String questionary(@PathVariable(required = false ,name = "message")String message,Model model) {
		if(message == null)
			message = "Please complete this questionary";
		model.addAttribute("questionary", new QuestionaryForm());
		model.addAttribute("message", message);
		
		return "questionary";
	}
	
}
