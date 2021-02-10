package com.springboot.assignment9.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.springboot.assignment9.domain.Recipe;
import com.springboot.assignment9.repository.RecipeRepository;

@Service
public class FileService {
	@Autowired
	private RecipeRepository recipeRepo;
	private List<Recipe> ingestAllRecipies() {
		if(recipeRepo.getRecipes().size() == 0) {
			CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',')
						 .withEscape('\\')
						 .withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes", "Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian")
						 .withSkipHeaderRecord()
						 .withIgnoreSurroundingSpaces();
			
			try (Reader in = new FileReader("recipes.txt")) {
				Iterable<CSVRecord> records = csvFormat.parse(in);
				
				for (CSVRecord record : records) {
					Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
					Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
					Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
					String instructions = record.get("Instructions");
					Double preparationMinutes = Double.parseDouble(record.get("Preparation Minutes"));
					Double pricePerServing = Double.parseDouble(record.get("Price Per Serving"));
					Integer readyInMinutes = Integer.parseInt(record.get("Ready In Minutes"));
					Integer servings = Integer.parseInt(record.get("Servings"));
					Double spoonacularScore = Double.parseDouble(record.get("Spoonacular Score"));
					String title = record.get("Title");
					Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
					Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));
					
					Recipe recipe = new Recipe();
					recipe.setCookingMinutes(cookingMinutes);
					recipe.setDairyFree(dairyFree);
					recipe.setGlutenFree(glutenFree);
					recipe.setInstructions(instructions);
					recipe.setPreparationMinutes(preparationMinutes);
					recipe.setPricePerServing(pricePerServing);
					recipe.setReadyInMinutes(readyInMinutes);
					recipe.setServings(servings);
					recipe.setSpoonacularScore(spoonacularScore);
					recipe.setTitle(title);
					recipe.setVegan(vegan);
					recipe.setVegetarian(vegetarian);
					
					recipeRepo.getRecipes().add(recipe);
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not Found");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO Exception");
				e.printStackTrace();
			}	
		}
		return recipeRepo.getRecipes();
	}
	
	public List<Recipe> getAllRecipes () {
		if(recipeRepo.getRecipes().size() == 0) {
			ingestAllRecipies();
		}
		return recipeRepo.getRecipes();
	}
	
	public List<Recipe> getGlutenFreeRecipes() {
		return getAllRecipes() 
				.stream()
				.filter(Recipe::getGlutenFree)
				.collect(Collectors.toList());
	}
	
	public List<Recipe> getVeganRecipes() {
		return getAllRecipes() 
				.stream()
				.filter(Recipe::getVegan)
				.collect(Collectors.toList());
	}
	
	public List<Recipe> getVeganAndGlutenFreeRecipes() {
		return getAllRecipes() 
				.stream()
				.filter(recipe -> recipe.getVegan() && recipe.getGlutenFree() )
				.collect(Collectors.toList());
	}
	
	public List<Recipe> getVegetarianRecipes() {
		return getAllRecipes() 
				.stream()
				.filter(Recipe::getVegetarian)
				.collect(Collectors.toList());
	}
	
}
