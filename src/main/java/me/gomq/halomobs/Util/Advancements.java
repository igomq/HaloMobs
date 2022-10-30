package me.gomq.halomobs.Util;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Advancements {
    public static class MineAdv extends BaseAdvancement {
        private final ItemStack itemStack;
        private final String title;

        public MineAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull Material target, ItemStack itemStack, @NotNull Advancement parent, @Range(from = 1L, to = 2147483647L) int maxProgression) {
            super(key, display, parent, maxProgression);
            this.itemStack = itemStack;
            this.title = display.getTitle();

            // Register the event via the tab's EventManager
            registerEvent(BlockBreakEvent.class, e -> {
                Player player = e.getPlayer();
                if (isVisible(player) && e.getBlock().getType() == target) {
                    incrementProgression(player);
                }
            });
        }

        @Override
        public void giveReward(@NotNull Player player) {
            player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "HaloMobs - ") + ChatColor.RESET + ChatColor.RED + title + ChatColor.GREEN + " 클리어 보상이 지급되었습니다!");
            player.getInventory().addItem(itemStack);
        }
    }

    public static class CraftAdv extends BaseAdvancement {
        private final ItemStack itemStack;
        private final String title;

        public CraftAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull ItemStack craftedResult, @NotNull ItemStack itemStack, @NotNull Advancement parent) {
            super(key, display, parent);
            this.itemStack = itemStack;
            this.title = display.getTitle();

            // Register the event via the tab's EventManager
            registerEvent(CraftItemEvent.class, e -> {
                if (e.getRecipe().getResult().isSimilar(craftedResult)) {
                    incrementProgression((Player) e.getWhoClicked());
                }
            });
        }

        @Override
        public void giveReward(@NotNull Player player) {
            player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "HaloMobs - ") + ChatColor.RESET + ChatColor.RED + title + ChatColor.GREEN + " 클리어 보상이 지급되었습니다!");
            player.getInventory().addItem(itemStack);
        }
    }


    public static class KillAdv extends BaseAdvancement {
        private final ItemStack itemStack;
        private final String title;

        public KillAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull EntityType targetType, @NotNull ItemStack itemStack, @NotNull Advancement parent, @Range(from = 1L, to = 2147483647L) int maxProgression) {
            super(key, display, parent, maxProgression);
            this.itemStack = itemStack;
            this.title = display.getTitle();

            // Register the event via the tab's EventManager
            registerEvent(EntityDeathEvent.class, e -> {
                if (e.getEntity().getKiller() != null && e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                    if (e.getEntity().getType() == targetType) {
                        incrementProgression((Player) e.getEntity().getKiller());
                    }
                }
            });
        }

        @Override
        public void giveReward(@NotNull Player player) {
            player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "HaloMobs - ") + ChatColor.RESET + ChatColor.RED + title + ChatColor.GREEN + " 클리어 보상이 지급되었습니다!");
            player.getInventory().addItem(itemStack);
        }
    }

    public static class UseAdv extends BaseAdvancement{
        private final ItemStack itemStack;
        private final String title;

        public UseAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull ItemStack targetItem, @NotNull ItemStack itemStack, @NotNull Advancement parent, @Range(from = 1L, to = 2147483647L) int maxProgression) {
            super(key, display, parent, maxProgression);
            this.itemStack = itemStack;
            this.title = display.getTitle();

            // Register the event via the tab's EventManager
            registerEvent(PlayerInteractEvent.class, e -> {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (e.getPlayer().getInventory().getItemInMainHand().isSimilar(targetItem)) {
                        incrementProgression(e.getPlayer());
                    }
                }
            });
        }

        @Override
        public void giveReward(@NotNull Player player) {
            player.sendMessage(ChatColor.GOLD + (ChatColor.BOLD + "HaloMobs - ") + ChatColor.RESET + ChatColor.RED + title + ChatColor.GREEN + " 클리어 보상이 지급되었습니다!");
            player.getInventory().addItem(itemStack);
        }
    }
}
