package com.github.startzyp.SuperEconomy.Vault;

import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.github.startzyp.SuperEconomy.Util.c3p0Util;
import com.github.startzyp.SuperEconomy.main;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.List;

public class VaultAPI extends AbstractEconomy {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return "SuperEconomy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return String.valueOf(v);
    }

    @Override
    public String currencyNamePlural() {
        return "";
    }

    @Override
    public String currencyNameSingular() {
        return "";
    }

    @Override
    public boolean hasAccount(String s) {
        if (main.onlineEconomy.containsKey(s)){
            return true;
        }else {
            return c3p0Util.hasEconmy(s);
        }
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return hasAccount(s);
    }


    @Override
    public double getBalance(String s) {
        EconomyEntity economyEntity;
        if (main.onlineEconomy.containsKey(s)){
            economyEntity = main.onlineEconomy.get(s);
        }else {
            economyEntity = c3p0Util.getEconomy(s);
        }
        return economyEntity.getBalance();
    }

    @Override
    public double getBalance(String s, String s1) {
        return getBalance(s);
    }

    @Override
    public boolean has(String s, double v) {
        return getBalance(s) > v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return has(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        if (!hasAccount(s)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "The player does not have an account!");
        }
        double balance = getBalance(s);
        if (balance < v){
            return new EconomyResponse(0.0D, balance, EconomyResponse.ResponseType.FAILURE, "The value is more than the player's balance!");
        }
        balance -= v;
        EconomyEntity economyEntity;
        if (main.onlineEconomy.containsKey(s)){
            economyEntity =main.onlineEconomy.get(s);
            economyEntity.setBalance(balance);
            main.onlineEconomy.put(s,economyEntity);
        }else {
            c3p0Util.UpdataEconomy(s,balance);
        }
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return withdrawPlayer(s,v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        if (!hasAccount(s)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "The player does not have an account!");
        }
        if (v<0){
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Value is less than zero!");
        }
        double balance = getBalance(s);
        balance += v;
        EconomyEntity economyEntity;
        if (main.onlineEconomy.containsKey(s)){
            economyEntity =main.onlineEconomy.get(s);
            economyEntity.setBalance(balance);
            main.onlineEconomy.put(s,economyEntity);
        }else {
            c3p0Util.UpdataEconomy(s,balance);
        }
        return new EconomyResponse(v, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return depositPlayer(s,v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        if (!hasAccount(s)){
            c3p0Util.CreateEconomy(s);
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return createPlayerAccount(s);
    }
}
