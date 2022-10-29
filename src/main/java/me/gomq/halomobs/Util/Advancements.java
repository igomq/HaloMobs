package me.gomq.halomobs.Util;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Advancements {
    public static class MineAdv extends BaseAdvancement {
        private final ItemStack itemStack;

        public MineAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull Material target, ItemStack itemStack, @NotNull Advancement parent, @Range(from = 1L, to = 2147483647L) int maxProgression) {
            super(key, display, parent, maxProgression);
            this.itemStack = itemStack;

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
            player.getInventory().addItem(itemStack);
        }
    }

    public static class CraftAdv extends BaseAdvancement {
        private final ItemStack itemStack;

        public CraftAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull ItemStack craftedResult, @NotNull ItemStack itemStack, @NotNull Advancement parent) {
            super(key, display, parent);
            this.itemStack = itemStack;

            // Register the event via the tab's EventManager
            registerEvent(CraftItemEvent.class, e -> {
                if (e.getRecipe().getResult().isSimilar(craftedResult)) {
                    incrementProgression((Player) e.getWhoClicked());
                }
            });
        }

        @Override
        public void giveReward(@NotNull Player player) {
            player.getInventory().addItem(itemStack);
        }
    }


    public static class KillAdv extends BaseAdvancement {
        private final ItemStack itemStack;

        public KillAdv(@NotNull String key, @NotNull AdvancementDisplay display, @NotNull EntityType targetType, @NotNull ItemStack itemStack, @NotNull Advancement parent, @Range(from = 1L, to = 2147483647L) int maxProgression) {
            super(key, display, parent, maxProgression);

            this.itemStack = itemStack;
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
            player.getInventory().addItem(itemStack);
        }
    }
}
