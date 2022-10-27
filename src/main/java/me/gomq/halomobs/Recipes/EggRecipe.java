package me.gomq.halomobs.Recipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class EggRecipe {
    public static final ItemStack Egg;
    static {
        ItemStack temp = new ItemStack(Material.TURTLE_EGG, 1);
        ItemMeta meta = temp.getItemMeta();

        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.setDisplayName(ChatColor.GOLD + "스폰알 가챠알");
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "우클릭하여 사용할 수 있다"));
        temp.setItemMeta(meta);

        Egg = temp;
    }

    public static ShapelessRecipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("r_egg"), Egg);
        recipe.addIngredient(Material.EGG);
        recipe.addIngredient(Material.IRON_INGOT);
        recipe.addIngredient(Material.WHITE_DYE);

        return recipe;
    }
}
