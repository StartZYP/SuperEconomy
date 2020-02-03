package com.github.startzyp.SuperEconomy.Commands;

import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
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
            if (!sender.hasPermission("money.holdings")){
                sender.sendMessage("§e§l[SuperEconomy]§2你没有权限");
                return false;
            }
            if (sender instanceof Player){
                sender.sendMessage("§e§l[SuperEconomy]§2玩家" + playername+"有"+main.onlineEconomy.get(playername).getBalance());
            }else {
                sender.sendMessage("§e§l[SuperEconomy]§2控制台无法操作");
            }
        }else if (args.length==1){
            if (!sender.hasPermission("money.holdings.others")){
                sender.sendMessage("§e§l[SuperEconomy]§2你没有权限");
                return false;
            }
            if (args[0].equalsIgnoreCase("help")){
                for (String msg:main.help){
                    sender.sendMessage(msg);
                }
                return true;
            }
            if (main.economy.hasAccount(args[0])){
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[0]);
                double balance = main.economy.getBalance(player);
                sender.sendMessage("§e§l[SuperEconomy]§2玩家" + args[0]+"有"+balance);
            }else {
                sender.sendMessage("§e§l[SuperEconomy]§2此玩家未进过服务器");
            }
        }else if (args.length==3){
            double money;
            try{
                money = Double.parseDouble(args[2]);
            }catch (NumberFormatException e){
                sender.sendMessage("§e§l[SuperEconomy]§2金钱格式不正确");
                return false;
            }
            if (args[0].equalsIgnoreCase("pay")){
                if (!sender.hasPermission("money.payment")){
                    sender.sendMessage("§e§l[SuperEconomy]§2你没有权限");
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§e§l[SuperEconomy]§2控制台无法操作");
                    return false;
                }
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("§e§l[SuperEconomy]§2此玩家未进过服务器");
                    return false;
                }
                EconomyResponse economyResponse = main.economy.withdrawPlayer(offlinePlayer, money);
                if (!economyResponse.transactionSuccess()){
                    sender.sendMessage("§e§l[SuperEconomy]§2你的金额不足");
                    return false;
                }
                main.economy.depositPlayer(player,money);
                sender.sendMessage("§e§l[SuperEconomy]§2支付成功");
            }else if (args[0].equalsIgnoreCase("set")){
                if (!sender.hasPermission("money.accounts.set")){
                    sender.sendMessage("§e§l[SuperEconomy]§2你没有权限");
                    return false;
                }
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("§e§l[SuperEconomy]§2此玩家未进过服务器");
                    return false;
                }
                SetBalance(args[1],money);
                sender.sendMessage("§e§l[SuperEconomy]§2设置金额成功");
            }else if (args[0].equalsIgnoreCase("give")){
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("§e§l[SuperEconomy]§2此玩家未进过服务器");
                    return false;
                }
                main.economy.depositPlayer(player,money);
                sender.sendMessage("§e§l[SuperEconomy]§2增加金额成功");
            }else if (args[0].equalsIgnoreCase("take")){
                if (!sender.hasPermission("money.accounts.take")){
                    sender.sendMessage("§e§l[SuperEconomy]§2你没有权限");
                    return false;
                }
                OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                if (!main.economy.hasAccount(player)){
                    sender.sendMessage("§e§l[SuperEconomy]§2此玩家未进过服务器");
                    return false;
                }
                EconomyResponse economyResponse = main.economy.withdrawPlayer(player, money);
                if (!economyResponse.transactionSuccess()){
                    sender.sendMessage("§e§l[SuperEconomy]§2该玩家的金额不足");
                    return false;
                }
                sender.sendMessage("§e§l[SuperEconomy]§2增加金额成功");
            }
        }
        return false;
    }

    public void SetBalance(String s,double v) {
        EconomyEntity economyEntity;
        if (main.onlineEconomy.containsKey(s)){
            economyEntity = main.onlineEconomy.get(s);
            economyEntity.setBalance(v);
            main.onlineEconomy.put(s,economyEntity);
        }else {
            c3p0Util.UpdataEconomy(s,v);
        }
    }
}
