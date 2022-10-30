package me.gomq.halomobs.Events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerAchievementDoneEvent implements Listener {
    @EventHandler
    public void onPlayerAchievementDone (PlayerAdvancementDoneEvent e) {
        Player pd = e.getPlayer();

        if (e.getAdvancement().getKey().getKey().equals("adventure/kill_all_mobs")) {
            Player player = e.getPlayer();
            player.setGameMode(GameMode.SPECTATOR);

            ArrayList<Player> playerList = (ArrayList<Player>) player.getWorld().getPlayers();
            for (Player p : playerList) {
                p.resetTitle();
                p.sendTitle(
                        ChatColor.GREEN + ( ChatColor.BOLD + player.getName() ) + ChatColor.RESET + ChatColor.GREEN + "클리어!",
                        "게임 클리어자 탄생!",
                        1, 50, 1
                );
            }
        } else if (e.getAdvancement().getKey().getKey().equals("adventure/kill_a_mob")) {
            Player player = e.getPlayer();
            Inventory inv = player.getInventory();

            ItemStack support_chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack support_pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
            ItemStack support_axe = new ItemStack(Material.IRON_AXE, 1);
            ItemStack support_shield = new ItemStack(Material.SHIELD, 1);

            ItemMeta support_chest_meta = support_chest.getItemMeta();
            ItemMeta support_pickaxe_meta = support_pickaxe.getItemMeta();
            ItemMeta support_axe_meta = support_axe.getItemMeta();
            ItemMeta support_shield_meta = support_shield.getItemMeta();

            support_chest_meta.setDisplayName(ChatColor.GREEN + "초보자 지원 [갑옷]");
            support_pickaxe_meta.setDisplayName(ChatColor.GREEN + "초보자 지원 [곡괭이]");
            support_axe_meta.setDisplayName(ChatColor.GREEN + "초보자 지원 [도끼]");
            support_shield_meta.setDisplayName(ChatColor.GREEN + "초보자 지원 [방패]");

            support_chest_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            support_chest_meta.addEnchant(Enchantment.DURABILITY, 1, true);

            support_pickaxe_meta.addEnchant(Enchantment.DIG_SPEED, 4, true);
            support_pickaxe_meta.addEnchant(Enchantment.DURABILITY, 1, true);

            support_axe_meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
            support_axe_meta.addEnchant(Enchantment.DIG_SPEED, 4, true);
            support_axe_meta.addEnchant(Enchantment.DURABILITY, 1, true);

            support_shield_meta.addEnchant(Enchantment.DURABILITY, 1, true);

            support_chest.setItemMeta(support_chest_meta);
            support_pickaxe.setItemMeta(support_pickaxe_meta);
            support_axe.setItemMeta(support_axe_meta);
            support_shield.setItemMeta(support_shield_meta);

            inv.addItem(support_chest);
            inv.addItem(support_pickaxe);
            inv.addItem(support_axe);
            inv.addItem(support_shield);
            inv.addItem(new ItemStack(Material.GOLDEN_CARROT, 31));

            player.sendMessage(ChatColor.GOLD + "[몬스터 1회 킬] " + ChatColor.GREEN + "업적을 달성하여 초보자 지원 아이템을 지급받았습니다.");
        } else if (e.getAdvancement().getKey().getKey().equals("adventure/hero_of_the_village")) {
            Player p = e.getPlayer();
            p.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK, 31));

            ItemStack hero_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta hero_sword_meta = hero_sword.getItemMeta();

            hero_sword_meta.setDisplayName(ChatColor.BOLD + (ChatColor.DARK_RED + "마을을 지킨 자"));
            hero_sword_meta.setLore(Collections.singletonList(ChatColor.GRAY + "마을을 지킨 자의 검이다."));

            hero_sword_meta.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
            hero_sword_meta.addEnchant(Enchantment.DURABILITY, 4, true);
            hero_sword_meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            hero_sword_meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
            hero_sword_meta.addEnchant(Enchantment.MENDING, 1, true);
            hero_sword_meta.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);

            hero_sword_meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("generic.maxHealth", 20, AttributeModifier.Operation.ADD_NUMBER));
            hero_sword_meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier("generic.attackDamage", 0.05, AttributeModifier.Operation.ADD_NUMBER));
            hero_sword_meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", 8, AttributeModifier.Operation.ADD_NUMBER));

            hero_sword_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            hero_sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            hero_sword.setItemMeta(hero_sword_meta);
            p.getInventory().addItem(hero_sword);

            p.sendMessage(ChatColor.GOLD + "[마을을 지킨 자] " + ChatColor.GREEN + "업적을 달성하여 마을을 지킨 자의 검을 지급받았습니다.");
        }
    }
}
