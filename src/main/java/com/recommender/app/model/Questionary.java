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
	private int ageUser;
	private List<String> hobbies;
	private String language;
	private boolean unique;
	private int lowerBound;
	private int upperBound;
	public Questionary(){
		ageUser = 18;
		hobbies = new ArrayList<String>();
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
	
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
			this.hobbies = hobbies;
		//else
			//throw new IllegalArgumentException("Your hobbies cannot be found");
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
			this.language = language;
		//else
			//throw new IllegalArgumentException("Invalid language");
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(int lowerBound) {
			this.lowerBound = lowerBound;
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(int upperBound) {
			this.upperBound = upperBound;
	}
	
}
