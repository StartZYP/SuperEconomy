package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.Config.DataBaseConfig;
import com.github.startzyp.SuperEconomy.Entity.EconomyEntity;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class c3p0Util {
    public static ComboPooledDataSource source = null;

    public c3p0Util() throws PropertyVetoException {
        source = new ComboPooledDataSource();
        source.setDriverClass(DataBaseConfig.getDriver());
        source.setJdbcUrl("jdbc:"+DataBaseConfig.getDbType()+"://"+DataBaseConfig.getHost()+":"+DataBaseConfig.getPort()+"/"+DataBaseConfig.getDatabase()+"?useUnicode=true&characterEncoding=utf-8");
        source.setUser(DataBaseConfig.getDbType());
        source.setPassword(DataBaseConfig.getDatabase());
        source.setInitialPoolSize(DataBaseConfig.getInitialPoolSize());
        source.setMaxIdleTime(DataBaseConfig.getMaxIdleTime());
        source.setMaxPoolSize(DataBaseConfig.getMaxPoolSize());
        source.setMinPoolSize(DataBaseConfig.getMinPoolSize());
    }

    public static void InitDao(){
        try{
            Connection connection = source.getConnection();
            String sql = "CREATE TABLE IF NOT EXISTS `iConomy` (\n" +
                    "  `id` int(255) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(32) NOT NULL,\n" +
                    "  `balance` double(64,2) NOT NULL,\n" +
                    "  `status` int(2) NOT NULL DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `username` (`username`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=32063 DEFAULT CHARSET=utf8;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean hasEconmy(String PlayerName){
        try{
            Connection connection = source.getConnection();
            String sql = "SELECT id FROM "+ DataBaseConfig.Table +" WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,PlayerName.toLowerCase());
            boolean aBoolean = statement.execute();
            statement.close();
            connection.close();
            return aBoolean;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void CreateEconomy(String PlayerName){
        try{
            Connection connection = source.getConnection();
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
        EconomyEntity economyEntity = new EconomyEntity(0, PlayerName, 0);
        try{
            Connection connection = source.getConnection();
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
        try{
            Connection connection = source.getConnection();
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

}
