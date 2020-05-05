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
	private static void initializeLanguages() throws QuestionaryInitializationFailedException {
		Questionary.LANGUAGES = new HashSet<String>();
		try(FileReader input = new FileReader(new File("src/main/resources/static/languages.txt"));BufferedReader reader = new BufferedReader(input)){
			int numLanguages = Integer.parseInt(reader.readLine());
			for(int i = 0;i<numLanguages;++i)
				Questionary.LANGUAGES.add(reader.readLine());
		} catch (IOException|NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new QuestionaryInitializationFailedException("Languages could not be loaded");
		}
		
	}
	private static void initializeConverter() throws QuestionaryInitializationFailedException {
		// TODO Auto-generated method stub
		Questionary.HOBBIES_CATEGORIES_CONVERTER = new HashMap<String,String>();
		try(FileReader input = new FileReader(new File("src/main/resources/static/converterHobbies.txt"));BufferedReader reader = new BufferedReader(input)){
			int numCategories = Integer.parseInt(reader.readLine());
			for(int i = 0;i<numCategories;++i) {
				String hobbieCategory = reader.readLine();
				Questionary.HOBBIES_CATEGORIES_CONVERTER.put(hobbieCategory,hobbieCategory);
				int hobbiesInCategory = Integer.parseInt(reader.readLine());
				for(int j = 0;j<hobbiesInCategory;++j) {
					Questionary.HOBBIES_CATEGORIES_CONVERTER.put(reader.readLine(), hobbieCategory);
				}
			}
		} catch (IOException|NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new QuestionaryInitializationFailedException("Converter could not be initialized");
		}
	}
	private static void initializeHobbies() throws QuestionaryInitializationFailedException {
		Questionary.HOBBIES = new HashSet<String>();
		try(FileReader input = new FileReader(new File("src/main/resources/static/hobbies.txt"));BufferedReader reader = new BufferedReader(input)){
			int numHobbies = Integer.parseInt(reader.readLine());
			for(int i = 0;i<numHobbies;++i)
				Questionary.HOBBIES.add(reader.readLine());
		} catch (IOException|NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new QuestionaryInitializationFailedException("Hobbies could not be loaded");
		}
		
	}
	public static void initializeQuestionaries() throws QuestionaryInitializationFailedException {
		
		if(!loaded) {
			initializeLanguages();
			initializeHobbies();
			initializeConverter();
			loaded = true;
		}
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
		
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(int upperBound) {
		if(upperBound>0)
			this.upperBound = upperBound;
		
	}
	
}
