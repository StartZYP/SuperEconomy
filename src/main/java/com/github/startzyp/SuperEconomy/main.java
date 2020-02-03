package com.github.startzyp.SuperEconomy;

import com.github.startzyp.SuperEconomy.Commands.PlayerCommand;
import com.github.startzyp.SuperEconomy.Config.DataBaseConfig;
import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Listener.event;
import com.github.startzyp.SuperEconomy.Util.EconomyThread;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import com.github.startzyp.SuperEconomy.Vault.VaultAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


public class main extends JavaPlugin {

    public static HashMap<String, EconomyEntity> onlineEconomy = new HashMap<>();
    public static Logger logger;
    public static Plugin plugin;
    public static Economy economy;
    private long ThreadPeroid = 60;
    private BukkitTask bukkitTask;
    public static List<String> help = new ArrayList<>();

    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        plugin = this;
        getServer().getPluginManager().registerEvents(new event(), this);
        getServer().getPluginCommand("money").setExecutor(new PlayerCommand());
        InitConfig();
        setupEconomy();
        c3p0Util.InitDao();
        bukkitTask = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new EconomyThread(), 20L, 20L * ThreadPeroid);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        bukkitTask.cancel();
        logger.info("§e§l[SuperEconomy]§2插件关闭释放数据库连接池存储数据");
        Set<String> onlineplayername = main.onlineEconomy.keySet();
        for (String name:onlineplayername){
            EconomyEntity economyEntity = main.onlineEconomy.get(name);
            c3p0Util.UpdataEconomy(economyEntity.getPlayerName(),economyEntity.getBalance());
            main.onlineEconomy.remove(name);
        }
        super.onDisable();
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
        DataBaseConfig.setTable(getConfig().getString("Data.Table"));
        DataBaseConfig.setUser(getConfig().getString("Data.Username"));
        DataBaseConfig.setPassword(getConfig().getString("Data.Password"));
        DataBaseConfig.url = "jdbc:mysql://" + getConfig().getString("Data.Host") + ":" + getConfig().getString("Data.Port")  + "/" + getConfig().getString("Data.Database") + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        ThreadPeroid = getConfig().getLong("ThreadPeroid");
        help = getConfig().getStringList("Messages.Help");
    }
}
