package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.Config.DataBaseConfig;
import com.github.startzyp.SuperEconomy.main;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class c3p0Util {
    public static ComboPooledDataSource source = null;

    public c3p0Util() throws PropertyVetoException {
        source = new ComboPooledDataSource();
        source.setDriverClass(DataBaseConfig.getDriver());
        source.setJdbcUrl("jdbc:"+DataBaseConfig.getDbType()+"://"+main.plugin.getConfig().getString("Mysql.Host")+":"+main.plugin.getConfig().getString("Mysql.Port")+"/"+main.plugin.getConfig().getString("Mysql.Database")+"?useUnicode=true&characterEncoding=utf-8");
        source.setUser(main.plugin.getConfig().getString("Mysql.DbType"));
        source.setPassword(main.plugin.getConfig().getString("Mysql.Database"));
        source.setInitialPoolSize(main.plugin.getConfig().getInt("Mysql.initialPoolSize"));
        source.setMaxIdleTime(main.plugin.getConfig().getInt("Data.maxIdleTime"));
        source.setMaxPoolSize(main.plugin.getConfig().getInt("Mysql.maxPoolSize"));
        source.setMinPoolSize(main.plugin.getConfig().getInt("Mysql.minPoolSize"));
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

    public static boolean hasEconmy(){
        try{
            Connection connection = main.source.getConnection();
            String sql = "SELECT id FROM " + t + " WHERE username=";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"UUID");
            statement.setString(2,"大王");
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void CreateEconomy(){
        try{
            Connection connection = main.source.getConnection();
            String sql = "";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"UUID");
            statement.setString(2,"大王");
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
