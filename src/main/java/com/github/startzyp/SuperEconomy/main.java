package com.github.startzyp.SuperEconomy;

import com.github.startzyp.SuperEconomy.Config.DataBaseConfig;
import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Listener.event;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.UUID;

public class main extends JavaPlugin {

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

        c3p0Util.InitDao();


        super.onEnable();
    }

    public void InitConfig(){
        //数据库配置初始化
        DataBaseConfig.setDriver(getConfig().getString("Data.Driver"));
        DataBaseConfig.setDbType(getConfig().getString("Data.DbType"));
        DataBaseConfig.setDatabase(getConfig().getString("Data.Database"));
        DataBaseConfig.setInitialPoolSize(getConfig().getString("Data.initialPoolSize"));
        DataBaseConfig.setMaxIdleTime(getConfig().getString("Data.maxIdleTime"));
        DataBaseConfig.setMaxPoolSize(getConfig().getString("Data.maxPoolSize"));
        DataBaseConfig.setMinPoolSize(getConfig().getString("Data.minPoolSize"));


    }
}
