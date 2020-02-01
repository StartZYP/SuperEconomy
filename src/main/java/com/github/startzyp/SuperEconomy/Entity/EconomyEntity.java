package com.github.startzyp.SuperEconomy.Entity;

public class EconomyEntity {
    private int id;
    private String PlayerName;
    private double balance;

    public EconomyEntity(int id, String playerName, double balance) {
        this.id = id;
        PlayerName = playerName;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "EconomyEntity{" +
                "id=" + id +
                ", PlayerName='" + PlayerName + '\'' +
                ", balance=" + balance +
                '}';
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
