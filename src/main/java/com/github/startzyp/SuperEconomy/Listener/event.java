package com.github.startzyp.SuperEconomy.Listener;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class event implements Listener {

    @EventHandler
    public void PlayerJoingame(PlayerJoinEvent event){
        Player player = event.getPlayer().getPlayer();

    }

}
