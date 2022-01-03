package me.oglass.hotslicerrpg.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import javax.sound.sampled.Line;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

public class CraftingTable {
    public static HashMap<HashMap<Integer, String>, ItemStack> customRecipes;
    public static void initRecipies() {
        customRecipes = new HashMap<>();
    }
    private static void BoomerangRecipe() {
        HashMap<Integer, String> recipe = new HashMap<>();
        recipe.put(0, "STRING");
        recipe.put(1, "BONE_FRAGMENT");
        recipe.put(2, "STRING");

        recipe.put(3, "STRING");
        recipe.put(4, "BONE_FRAGMENT");
        recipe.put(5, "STRING");

        recipe.put(6, "STRING");
        recipe.put(7, "BONE_FRAGMENT");
        recipe.put(8, "STRING");
        customRecipes.put(recipe, ItemManager.Boomerang);
    }
    /*
    public static HashMap<HashMap<Integer, ItemStack>, ItemStack> recipes;
    public static HashMap<Integer, ItemStack> table;
    public static void initRecipes() {
        recipes = new HashMap<>();
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if(recipe instanceof ShapelessRecipe) {
                //handle shapeless recipe
            } else if(recipe instanceof ShapedRecipe) {
                Map<Character, ItemStack> ingmap = ((ShapedRecipe) recipe).getIngredientMap();
                String[] str = ((ShapedRecipe) recipe).getShape();
                char[] chars = Arrays.toString(str).toCharArray();
                HashMap<Integer, ItemStack> stackMap = new HashMap<>();

                for (int i = 0; i < chars.length; i++) {
                    stackMap.put(i, ingmap.get(chars[i]));
                }
                if (recipe.getResult().getType().equals(Material.WORKBENCH)) {
                    table = stackMap;
                }
                recipes.put(stackMap, recipe.getResult());
            }
        }
    }
    */
}
