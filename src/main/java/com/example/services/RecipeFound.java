package com.example.services;

import com.example.models.Recipe;

import java.util.TreeSet;

class RecipeFound {
    Recipe recipe;
    TreeSet<Integer> closestDays = new TreeSet<>();

    RecipeFound(Recipe recipe) {
        this.recipe = recipe;
    }
}