package com.github.startzyp.SuperEconomy.Listener;


import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import com.github.startzyp.SuperEconomy.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class event implements Listener {

    @EventHandler
    public void PlayerJoingame(PlayerJoinEvent event){
        String name = event.getPlayer().getName();
        if (!c3p0Util.hasEconmy(name)){
            c3p0Util.CreateEconomy(name);
        }
        EconomyEntity economy = c3p0Util.getEconomy(name);
        main.onlineEconomy.put(name,economy);
    }
    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        String name = event.getPlayer().getName();
        EconomyEntity economyEntity = main.onlineEconomy.get(name);
        if (economyEntity.getBalance()!=economyEntity.getLastbalance()){
            c3p0Util.UpdataEconomy(name,economyEntity.getBalance());
        }
        main.onlineEconomy.remove(name);
    }

}
