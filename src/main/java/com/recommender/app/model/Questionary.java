package com.recommender.app.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.recommender.app.exceptions.QuestionaryInitializationFailedException;

public class Questionary {
	private static Set<String> HOBBIES;
	private static Set<String> LANGUAGES;
	private static Map<String,String> HOBBIES_CATEGORIES_CONVERTER;
	private static boolean loaded = false;
	private int ageUser;
	private String hobbies;
	private String language;
	private boolean unique;
	private int lowerBound;
	private int upperBound;
	public Questionary(){
		ageUser = 18;
		hobbies = "Videogames,Electronics,Movies";
		language = "English";
		lowerBound = 30;
		upperBound = 60;
		unique = false;
	}
	
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	public int getAgeUser() {
		return ageUser;
	}
	public void setAgeUser(int ageUser) {
		if(ageUser>0&&ageUser<150)
			this.ageUser = ageUser;
		else
			throw new IllegalArgumentException("Invalid age");
	}
	public List<String> getHobbiesCategories() {
		List<String> output = new ArrayList<String>();
		for(String hobbie:hobbies.trim().split(",")) {
			output.add(Questionary.HOBBIES_CATEGORIES_CONVERTER.get(hobbie.trim()));
		}
		return output;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		if(validHobbies(hobbies))
			this.hobbies = hobbies;
		//else
			//throw new IllegalArgumentException("Your hobbies cannot be found");
	}
	private boolean validHobbies(String hobbies) {
		String [] formattedHobbies = hobbies.trim().split(",");
		int i = 0;
		boolean succesfull = true;
		while(succesfull&&i<formattedHobbies.length) {
			succesfull = Questionary.HOBBIES.contains(formattedHobbies[i].trim());
			++i;
		}
		return succesfull;
	}
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		if(validLanguage(language))
			this.language = language;
		//else
			//throw new IllegalArgumentException("Invalid language");
	}
	private boolean validLanguage(String language) {
		// TODO Auto-generated method stub
		return Questionary.LANGUAGES.contains(language.trim());
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(int lowerBound) {
		if(lowerBound>0)
			this.lowerBound = lowerBound;
		else
			throw new IllegalArgumentException("Lower bound price should be greater than 0");
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(int upperBound) {
		if(upperBound<Integer.MAX_VALUE)
			this.upperBound = upperBound;
		else
			throw new IllegalArgumentException("Upper bound cannot be greater than "+((Integer)Integer.MAX_VALUE).toString());
	}
	
}
