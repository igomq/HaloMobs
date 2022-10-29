package me.gomq.halomobs.Events;

import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static me.gomq.halomobs.HaloMobs.api;

public class PlayerServerEnterEvent implements Listener {
    @EventHandler
    public void onPlayerJoin (PlayerLoadingCompletedEvent e) {
        Player p = e.getPlayer();

        Objects.requireNonNull(api.getAdvancementTab("halomobs")).updateAdvancementsToTeam(p);
    }
}
