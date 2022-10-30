package me.gomq.halomobs.Events;

import org.bukkit.entity.EntityType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

import static me.gomq.halomobs.Recipes.EggRecipe.Egg;

public class EntityDeadEvent implements Listener {
    @EventHandler
    public void onEntityDeath (EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();

        if (killer != null && killer.getType() == EntityType.PLAYER) {
            Random rand = new Random();
            int bound = rand.nextInt(100) + 1;

            if (bound <= 25) {
                killer.getInventory().addItem(Egg);
            }
        }
    }
}