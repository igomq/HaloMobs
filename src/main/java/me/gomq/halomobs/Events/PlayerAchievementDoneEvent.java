package me.gomq.halomobs.Events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.ArrayList;

public class PlayerAchievementDoneEvent implements Listener {
    @EventHandler
    public void onPlayerAchievementDone (PlayerAdvancementDoneEvent e) {
        if (e.getAdvancement().getKey().getKey().equals("adventure/kill_all_mobs")) {
            Player player = e.getPlayer();
            player.setGameMode(GameMode.SPECTATOR);

            ArrayList<Player> playerList = (ArrayList<Player>) player.getWorld().getPlayers();
            // foreach playerList, show Title
            for (Player p : playerList) {
                p.resetTitle();
                p.sendTitle(
                        ChatColor.GREEN + ( ChatColor.BOLD + player.getName() ) + ChatColor.RESET + ChatColor.GREEN + "클리어!",
                        "게임 클리어자 탄생!",
                        1, 50, 1
                );
            }
        }
    }
}
