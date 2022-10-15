package me.gomq.halomobs.Recipes;

import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;

public class RecipeManager {
    public static ArrayList<ShapelessRecipe> getRecipes() {
        ArrayList<ShapelessRecipe> recipes = new ArrayList<>();
        recipes.add(EggRecipe.getRecipe());

        return recipes;
    }
}
