package com.github.startzyp.SuperEconomy.Listener;


import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import com.github.startzyp.SuperEconomy.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class event implements Listener {

    @EventHandler
    public void PlayerJoingame(PlayerJoinEvent event){
        Player player = event.getPlayer().getPlayer();
        if (c3p0Util.hasEconmy(player.getName())){
            EconomyEntity economy = c3p0Util.getEconomy(player.getName());
            main.onlineEconomy.put(player.getName(),economy);
        }else {
            c3p0Util.CreateEconomy(player.getName());
        }
    }

}
