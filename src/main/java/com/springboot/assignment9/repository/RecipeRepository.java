package com.springboot.assignment9.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.assignment9.domain.Recipe;

@Repository
public class RecipeRepository {
	List<Recipe> recipes = new ArrayList<>(100);

	public List<Recipe> getRecipes() {
		return recipes;
	}
}
