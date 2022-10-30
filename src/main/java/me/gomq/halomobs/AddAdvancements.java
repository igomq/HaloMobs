package me.gomq.halomobs;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.gomq.halomobs.Util.Advancements;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static me.gomq.halomobs.HaloMobs.api;
import static me.gomq.halomobs.Recipes.EggRecipe.Egg;
import static me.gomq.halomobs.Recipes.TrashRecipe.Trash;

public class AddAdvancements {
    private static final ItemStack rewardPickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
    static {
        rewardPickaxe.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ItemMeta pickMeta = rewardPickaxe.getItemMeta();
        pickMeta.addEnchant(Enchantment.DIG_SPEED, 6, true);
        pickMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
        pickMeta.addEnchant(Enchantment.MENDING, 1, true);
        pickMeta.addEnchant(Enchantment.DURABILITY, 3, true);

        pickMeta.setDisplayName(ChatColor.GOLD + "ㄱㅗㄱㄱㅗㅐㅇㅇㅣ");
        pickMeta.setLore(Collections.singletonList(ChatColor.GREEN + "빚쟁이가 이전에 이 곡괭이의 손잡이를 " + ChatColor.RED + "딜도" + ChatColor.GREEN + "로 사용하였다고 하는 곡괭이다."));

        rewardPickaxe.setItemMeta(pickMeta);
    }

    public static void initializeAdvancements() {
        AdvancementTab advancementTab = api.createAdvancementTab("halomobs");
        RootAdvancement rootAdvancement = new RootAdvancement(advancementTab,
                "custom",
                new AdvancementDisplay(Material.WAXED_OXIDIZED_COPPER, "HaloMobs", AdvancementFrameType.TASK, true, true, 0, 0, "The Start of the Halloween"),
                "textures/block/carved_pumpkin.png"
        );

        Advancements.CraftAdv createEgg = new Advancements.CraftAdv("create_egg_item",
                new AdvancementDisplay(Material.TURTLE_EGG, "Egg Enterance", AdvancementFrameType.TASK, true, true, 1.5f, 4.5f, "Craft an Egg Item"),
                Egg, Egg.asQuantity(16),
                rootAdvancement);

        Advancements.UseAdv useEgg = new Advancements.UseAdv("use_egg_item",
                new AdvancementDisplay(Material.TURTLE_EGG, "Egg Master", AdvancementFrameType.TASK, true, true, 3f, 4.5f, "Use an Egg Item thirty-two times"),
                Egg, new ItemStack(Material.END_CRYSTAL, 4),
                createEgg, 32);

        Advancements.KillAdv killGolem = new Advancements.KillAdv("kill_golem",
                new AdvancementDisplay(Material.IRON_BLOCK, "Golem Killer", AdvancementFrameType.TASK, true, true, 1.5f, 1.5f, "Kill Iron Golem twice"),
                EntityType.IRON_GOLEM, new ItemStack(Material.IRON_BLOCK, 16),
                rootAdvancement, 2);

        Advancements.KillAdv killBlaze = new Advancements.KillAdv("kill_blaze",
                new AdvancementDisplay(Material.BLAZE_ROD, "Blaze Killer", AdvancementFrameType.TASK, true, true, 3f, 1.5f, "Kill Blaze ten times"),
                EntityType.BLAZE, new ItemStack(Material.BLAZE_ROD, 16),
                killGolem, 10);

        Advancements.KillAdv killEnderman = new Advancements.KillAdv("kill_enderman",
                new AdvancementDisplay(Material.ENDER_PEARL, "End Slayer", AdvancementFrameType.TASK, true, true, 4.5f, 1.5f, "Kill Enderman twenty times"),
                EntityType.ENDERMAN, new ItemStack(Material.ELYTRA, 1),
                killBlaze, 20);

        Advancements.KillAdv killWitherSkell = new Advancements.KillAdv("kill_wither_skell",
                new AdvancementDisplay(Material.WITHER_SKELETON_SKULL, "Wither Skell Killer", AdvancementFrameType.TASK, true, true, 4.5f, 3f, "Kill Wither Skeleton five times"),
                EntityType.WITHER_SKELETON, new ItemStack(Material.WITHER_SKELETON_SKULL, 3),
                killBlaze, 5);

        Advancements.MineAdv mineObby = new Advancements.MineAdv("mine_obby",
                new AdvancementDisplay(Material.OBSIDIAN, "Obsidian Miner", AdvancementFrameType.TASK, true, true, 1.5f, 0, "Mine Obsidian ten times"),
                Material.OBSIDIAN, rewardPickaxe,
                rootAdvancement, 10);

        Advancements.MineAdv mineNetherite = new Advancements.MineAdv("mine_netherite",
                new AdvancementDisplay(Material.ANCIENT_DEBRIS, "Netherite Miner", AdvancementFrameType.TASK, true, true, 3f, 0, "Mine Ancient Debris five times"),
                Material.ANCIENT_DEBRIS, new ItemStack(Material.NETHERITE_BLOCK, 1),
                mineObby, 5);

        Advancements.CraftAdv craftTrash = new Advancements.CraftAdv("craft_trash",
                new AdvancementDisplay(Material.BARRIER, "I am Young AND Rich", AdvancementFrameType.TASK, true, true, 4.5f, 0, "Craft a Trash Item"),
                Trash, new ItemStack(Material.AIR, 0),
                mineNetherite);

        advancementTab.registerAdvancements(rootAdvancement, createEgg, useEgg, killGolem, killBlaze, killEnderman, killWitherSkell, mineObby, mineNetherite, craftTrash);
    }
}
