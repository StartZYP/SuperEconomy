package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.main;

import java.util.HashMap;
import java.util.Set;

public class EconomyThread implements Runnable {

    @Override
    public void run() {
        main.logger.info("§e§l[SuperEconomy]§2插件周期存储开始");
        Set<String> onlineplayername = main.onlineEconomy.keySet();
        for (String name:onlineplayername){
            EconomyEntity economyEntity = main.onlineEconomy.get(name);
            if (economyEntity.getBalance()!=economyEntity.getLastbalance()){
                c3p0Util.UpdataEconomy(name,economyEntity.getBalance());
                economyEntity.setLastbalance(economyEntity.getBalance());
                main.onlineEconomy.put(name,economyEntity);
            }
        }
    }
}
