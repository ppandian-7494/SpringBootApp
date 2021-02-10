package com.springboot.assignment9.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment9.domain.Recipe;
import com.springboot.assignment9.service.FileService;

@RestController
public class RecipeController {
	@Autowired
	private FileService fileService;
	
	@GetMapping("/gluten-free")
	public List<Recipe> getGlutenFree() {
		return fileService.getGlutenFreeRecipes();
	}
	
	@GetMapping("/vegan")
	public List<Recipe> getVegan() {
		return fileService.getVeganRecipes();
	}
	
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGlutenFree() {
		return fileService.getVeganAndGlutenFreeRecipes();
	}
	
	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian() {
		return fileService.getVegetarianRecipes();
	}
	
	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipes() {
		return fileService.getAllRecipes();
	}

}
