package com.example.services;

import com.example.models.Recipe;

import java.util.TreeSet;

class RecipeFound {
    final Recipe recipe;
    final TreeSet<Integer> closestDays = new TreeSet<>();

    RecipeFound(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "RecipeFound{" +
                "recipe=" + recipe +
                ", closestDays=" + closestDays +
                '}';
    }
}