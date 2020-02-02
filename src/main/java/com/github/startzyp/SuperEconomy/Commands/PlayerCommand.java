package com.github.startzyp.SuperEconomy.Commands;

import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import com.github.startzyp.SuperEconomy.Vault.VaultAPI;
import com.github.startzyp.SuperEconomy.main;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String playername = sender.getName();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playername);
        if (args.length==0){
            if (sender instanceof Player){
                sender.sendMessage("你有"+ main.onlineEconomy.get(playername).getBalance());
            }else {
                sender.sendMessage("你不是玩家");
            }
        }else if (args.length==1){
            if (main.economy.hasAccount(args[0])){
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[0]);
                double balance = main.economy.getBalance(player);
                sender.sendMessage("玩家:"+args[0]+"有"+balance);
            }else {
                sender.sendMessage("此玩家没得信息");
            }
        }else if (args.length==3){
            double money;
            try{
                money = Double.parseDouble(args[2]);
            }catch (NumberFormatException e){
                sender.sendMessage("金钱格式不正确");
                return false;
            }
            if (args[0].equalsIgnoreCase("pay")){
                if (!(sender instanceof Player)) {
                    sender.sendMessage("你不是玩家");
                    return false;
                }
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("转账目标"+args[1]+"没有次玩家");
                    return false;
                }
                EconomyResponse economyResponse = main.economy.withdrawPlayer(offlinePlayer, money);
                if (!economyResponse.transactionSuccess()){
                    sender.sendMessage("您的余额不足以支付");
                    return false;
                }
                main.economy.depositPlayer(player,money);
                sender.sendMessage("成功");
            }else if (args[0].equalsIgnoreCase("set")){
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("金额设置"+args[1]+"没有次玩家");
                    return false;
                }
                SetBalance(args[1],money);
                sender.sendMessage("成功");
            }else if (args[0].equalsIgnoreCase("give")){
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("目标"+args[1]+"没有次玩家");
                    return false;
                }
                main.economy.depositPlayer(player,money);
                sender.sendMessage("成功");
            }else if (args[0].equalsIgnoreCase("take")){
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("目标"+args[1]+"没有次玩家");
                    return false;
                }
                EconomyResponse economyResponse = main.economy.withdrawPlayer(player, money);
                if (!economyResponse.transactionSuccess()){
                    sender.sendMessage("次玩家的余额不足");
                    return false;
                }
                sender.sendMessage("成功");
            }
        }
        return false;
    }

    public void SetBalance(String s,double v) {
        EconomyEntity economyEntity;
        if (main.onlineEconomy.containsKey(s)){
            economyEntity = main.onlineEconomy.get(s);
        }else {
            economyEntity = c3p0Util.getEconomy(s);
        }
        economyEntity.setBalance(v);
        main.onlineEconomy.put(s,economyEntity);
    }
}
