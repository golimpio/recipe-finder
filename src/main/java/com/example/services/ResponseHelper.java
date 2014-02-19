package com.example.services;

import com.example.models.FridgeItem;
import com.example.models.Recipe;
import com.example.models.RecipeResponse;
import com.example.repository.FridgeRepository;
import com.example.repository.RecipesRepository;

import java.util.List;

final class ResponseHelper {

    private ResponseHelper() {}

    public static RecipeResponse getRecipe() {
        List<FridgeItem> fridgeItems = FridgeRepository.instance().get();
        if (fridgeItems.isEmpty())
            return fridgeIsEmpty();

        if (RecipesRepository.instance().get().isEmpty())
            return takeOut();

        return findRecipe(fridgeItems);
    }

    private static RecipeResponse fridgeIsEmpty() {
        return new RecipeResponse("Fridge is empty");
    }

    private static RecipeResponse takeOut() {
        return new RecipeResponse("Order Takeout");
    }

    private static RecipeResponse findRecipe(List<FridgeItem> fridgeItems) {
        Recipe recipe = RecipesRepository.instance().find(fridgeItems);
        return recipe == null ? takeOut()
                              : new RecipeResponse("Recommendation for what to cook tonight", recipe);
    }
}
