package com.example.services;

import com.example.models.FridgeItem;
import com.example.models.Item;
import com.example.models.Recipe;
import com.example.models.RecipeResponse;
import com.example.repository.FridgeRepository;
import com.example.repository.RecipesRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ResponseHelper {

    private ResponseHelper() {}

    public static RecipeResponse getRecipe() {
        return FridgeRepository.instance().isEmpty() ? fridgeIsEmpty()
                : RecipesRepository.instance().isEmpty() ? takeOut()
                : findRecipe();
    }

    private static RecipeResponse fridgeIsEmpty() {
        return new RecipeResponse("Fridge is empty");
    }

    private static RecipeResponse takeOut() {
        return new RecipeResponse("Order Takeout");
    }

    private static RecipeResponse suggestedRecipe(Recipe recipe) {
        return new RecipeResponse("Suggestion for dinner", recipe);
    }

    private static RecipeResponse findRecipe() {
        List<RecipeFound> recipesFound = new ArrayList<>();
        for (Recipe recipe : RecipesRepository.instance().get()) {
            boolean foundRecipe = true;
            RecipeFound recipeFound = new RecipeFound(recipe);

            for (Item ingredient : recipe.getIngredients()) {
                FridgeItem fridgeItem = FridgeRepository.instance().getItem(ingredient.getItem());
                if (!isValid(fridgeItem, recipeFound)) {
                    foundRecipe = false;
                    break;
                }
            }

            if (foundRecipe)
                recipesFound.add(recipeFound);
        }

        return recipesFound.isEmpty() ? takeOut()
                : recipesFound.size() == 1 ? suggestedRecipe(recipesFound.get(0).recipe)
                : findBestMatch(recipesFound);
    }

    private static boolean isValid(FridgeItem item, RecipeFound recipeFound) {
        if (item == null)
            return false;

        LocalDate today = new LocalDate();
        LocalDate useBy = new LocalDate(item.getUseBy());
        if (useBy.isBefore(today))
            return false;

        recipeFound.closestDays.add(Days.daysBetween(today, useBy).getDays());
        return true;
    }

    private static RecipeResponse findBestMatch(List<RecipeFound> recipesFound) {
        Iterator<RecipeFound> it = recipesFound.iterator();
        return suggestedRecipe(findBestMatch(it.next(), it).recipe);
    }

    private static RecipeFound findBestMatch(RecipeFound current, Iterator<RecipeFound> it) {
        if (!it.hasNext())
            return current;

        RecipeFound next = it.next();

        Iterator<Integer> currentIt = current.closestDays.iterator();
        Iterator<Integer> nextIt = next.closestDays.iterator();

        while (currentIt.hasNext()) {
            if (!nextIt.hasNext())
                return findBestMatch(next, it);

            int result = currentIt.next().compareTo(nextIt.next());
            if (result == -1)
                return findBestMatch(current, it);
            if (result == 1)
                return findBestMatch(next, it);
        }

        return findBestMatch(current, it);
    }
}
