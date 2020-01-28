package com.github.startzyp.SuperEconomy;

import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Listener.event;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class main extends JavaPlugin {

    public static ComboPooledDataSource source;

    public static HashMap<UUID, EconomyEntity> onlineEconomy = new HashMap<>();

    public static Plugin plugin;

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveDefaultConfig();
        plugin = this;
        getServer().getPluginManager().registerEvents(new event(), this);



        super.onEnable();
    }
}
