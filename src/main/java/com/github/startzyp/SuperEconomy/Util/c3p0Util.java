package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.Config.DataBaseConfig;
import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;


import java.sql.*;

public class c3p0Util {

    public static void InitDao(){
        try{
            Connection connection = DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.User, DataBaseConfig.Password);
            String sql = "CREATE TABLE IF NOT EXISTS `iConomy` (\n" +
                    "  `id` int(255) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(32) NOT NULL,\n" +
                    "  `balance` double(64,2) NOT NULL,\n" +
                    "  `status` int(2) NOT NULL DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `username` (`username`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean hasEconmy(String PlayerName){
        PlayerName = PlayerName.toLowerCase();
        try{
            Connection connection = DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.User, DataBaseConfig.Password);
            System.out.println(DataBaseConfig.url);
            System.out.println(DataBaseConfig.Table);
            String sql = "SELECT id FROM "+ DataBaseConfig.Table +" WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,PlayerName);
            ResultSet resultSet = statement.executeQuery();
            boolean next = resultSet.next();
            statement.close();
            connection.close();
            return next;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void CreateEconomy(String PlayerName){
        PlayerName = PlayerName.toLowerCase();
        try{
            Connection connection = DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.User, DataBaseConfig.Password);
            String sql = "INSERT INTO "+ DataBaseConfig.Table +" (username, balance, status) values (?, 0, 0)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,PlayerName);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static EconomyEntity getEconomy(String PlayerName){
        PlayerName = PlayerName.toLowerCase();
        EconomyEntity economyEntity = new EconomyEntity(0, PlayerName, 0);
        try{
            Connection connection = DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.User, DataBaseConfig.Password);
            String sql = "SELECT * FROM "+ DataBaseConfig.Table +" WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,PlayerName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                economyEntity.setId(resultSet.getInt("id"));
                economyEntity.setBalance(resultSet.getDouble("balance"));
            }
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return economyEntity;
    }

    public static void UpdataEconomy(String PlayerName,double balance){
        PlayerName = PlayerName.toLowerCase();
        try{
            Connection connection = DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.User, DataBaseConfig.Password);
            String sql = "UPDATE "+ DataBaseConfig.Table +" SET balance = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1,balance);
            statement.setString(2,PlayerName);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
