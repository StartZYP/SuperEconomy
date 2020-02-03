package com.github.startzyp.SuperEconomy.Entity;

public class EconomyEntity {
    private int id;
    private String PlayerName;
    private double balance;
    private double lastbalance;

    public EconomyEntity(int id, String playerName, double balance) {
        this.id = id;
        PlayerName = playerName;
        this.balance = balance;
        lastbalance = balance;
    }

    @Override
    public String toString() {
        return "EconomyEntity{" +
                "id=" + id +
                ", PlayerName='" + PlayerName + '\'' +
                ", balance=" + balance +
                ", lastbalance=" + lastbalance +
                '}';
    }

    public double getLastbalance() {
        return lastbalance;
    }

    public void setLastbalance(double lastbalance) {
        this.lastbalance = lastbalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
