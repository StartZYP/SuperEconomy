package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.main;
import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.Set;

public class EconomyThread implements Runnable {

    @Override
    public void run() {
        main.logger.info("§e§l[SuperEconomy]§2插件周期存储开始");
        Set<String> tmpstr = new HashSet<>();
        Set<String> onlineplayername = main.onlineEconomy.keySet();
        for (String name:onlineplayername){
            if (!Bukkit.getServer().getOfflinePlayer(name).isOnline()){
                EconomyEntity economyEntity = main.onlineEconomy.get(name);
                c3p0Util.UpdataEconomy(economyEntity.getPlayerName(),economyEntity.getBalance());
                tmpstr.add(name);
            }
        }
        for (String str:tmpstr){
            main.onlineEconomy.remove(str);
        }
    }
}
