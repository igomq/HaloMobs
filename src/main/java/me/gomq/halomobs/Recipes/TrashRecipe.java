package me.gomq.halomobs.Recipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class TrashRecipe {
    public final static ItemStack Trash = new ItemStack(Material.BARRIER, 1);
    static {
        Trash.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ItemMeta meta = Trash.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "쓰레기");
        meta.setLore(Collections.singletonList(ChatColor.AQUA + "부-자 아조씨만 제작 가능한 쓰레기다. 아무 의미가 없다."));
        meta.addEnchant(Enchantment.DIG_SPEED, 127, true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 31, true);

        Trash.setItemMeta(meta);
    }

    public static ShapelessRecipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("r_trash"), Trash);
        recipe.addIngredient(Material.DIAMOND_BLOCK);
        recipe.addIngredient(Material.GOLD_BLOCK);
        recipe.addIngredient(Material.IRON_BLOCK);
        recipe.addIngredient(Material.STONECUTTER);

        return recipe;
    }
}
