package com.github.startzyp.SuperEconomy;

import com.github.startzyp.SuperEconomy.Config.DataBaseConfig;
import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Listener.event;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import com.github.startzyp.SuperEconomy.Vault.VaultAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;


public class main extends JavaPlugin {

    public static HashMap<String, EconomyEntity> onlineEconomy = new HashMap<>();
    public static Logger logger;
    public static Plugin plugin;
    public static Economy economy;

    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        plugin = this;
        getServer().getPluginManager().registerEvents(new event(), this);
        InitConfig();
        setupEconomy();
        c3p0Util.InitDao();
        super.onEnable();
    }

    private void setupEconomy()
    {
        getServer().getServicesManager().register(Economy.class, new VaultAPI(), this, ServicePriority.Normal);
        economy =new VaultAPI();
        logger.info("§e§l[SuperEconomy]§2Vault经济对接成功");
    }

    private void InitConfig(){
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveDefaultConfig();
        //数据库配置初始化
        DataBaseConfig.setDriver(getConfig().getString("Data.Driver"));
        DataBaseConfig.setDbType(getConfig().getString("Data.DbType"));
        DataBaseConfig.setDatabase(getConfig().getString("Data.Database"));
        DataBaseConfig.setTable(getConfig().getString("Data.Table"));
        DataBaseConfig.setInitialPoolSize(getConfig().getInt("Data.initialPoolSize"));
        DataBaseConfig.setMaxIdleTime(getConfig().getInt("Data.maxIdleTime"));
        DataBaseConfig.setMaxPoolSize(getConfig().getInt("Data.maxPoolSize"));
        DataBaseConfig.setMinPoolSize(getConfig().getInt("Data.minPoolSize"));

    }
}
