package com.example.services;

import com.example.models.FridgeItem;
import com.example.models.Item;
import com.example.models.Recipe;
import com.example.models.RecipeResponse;
import com.example.repository.FridgeRepository;
import com.example.repository.RecipesRepository;
import org.joda.time.LocalDate;

import java.util.List;

final class ResponseHelper {

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

    private static RecipeResponse findRecipe() {
        List<FridgeItem> fridgeItems = FridgeRepository.instance().get();

//        Recipe recipe; = lookup(fridgeItems);

        for (Recipe recipe : RecipesRepository.instance().get()) {

            for (Item ingredient : recipe.getIngredients()) {

                FridgeItem fridgeItem = FridgeRepository.instance().getItem(ingredient.getItem());
                if (!canUse(fridgeItem))
                    continue;


                || fridgeItem.getUseBy().getTime() < System.currentTimeMillis())
            }




        }


        return recipe == null ? takeOut()
                              : new RecipeResponse("Recommendation for what to cook tonight", recipe);
    }

    private static boolean canUse(FridgeItem item) {
        if (item == null)
            return false;

        LocalDate useBy = new LocalDate(item.getUseBy());
        return useBy.isBefore(new LocalDate()) ? false : true;
    }

    private FridgeItem getFridgeItem(List<FridgeItem> fridgeItems, String name) {

    }

}
