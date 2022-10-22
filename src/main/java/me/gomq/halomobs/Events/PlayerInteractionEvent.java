package me.gomq.halomobs.Events;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

import static me.gomq.halomobs.Recipes.EggRecipe.Egg;
import static me.gomq.halomobs.Util.Eggs.SPAWN_EGGS;

public class PlayerInteractionEvent implements Listener {
    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player interacted = e.getPlayer();
            if (interacted.getInventory().getItemInMainHand().isSimilar(Egg)) {
                e.setCancelled(true);

                Random rand = new Random();
                interacted.getInventory().removeItem(Egg);
                interacted.getInventory().addItem(new ItemStack(SPAWN_EGGS[rand.nextInt(SPAWN_EGGS.length)], 1));
            }
        }
    }
}
