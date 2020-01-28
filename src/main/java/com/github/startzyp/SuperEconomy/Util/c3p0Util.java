package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.main;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class c3p0Util {
    public c3p0Util() throws PropertyVetoException {
        main.source = new ComboPooledDataSource();
        main.source.setDriverClass("com.mysql.jdbc.Driver");
        main.source.setJdbcUrl("jdbc:"+main.plugin.getConfig().getString("Mysql.DbType")+"://"+main.plugin.getConfig().getString("Mysql.Host")+":"+main.plugin.getConfig().getString("Mysql.Port")+"/"+main.plugin.getConfig().getString("Mysql.Database")+"?useUnicode=true&characterEncoding=utf-8");
        main.source.setUser(main.plugin.getConfig().getString("Mysql.DbType"));
        main.source.setPassword(main.plugin.getConfig().getString("Mysql.Database"));
        main.source.setInitialPoolSize(main.plugin.getConfig().getInt("Mysql.initialPoolSize"));
        main.source.setMaxIdleTime(main.plugin.getConfig().getInt("Mysql.maxIdleTime"));
        main.source.setMaxPoolSize(main.plugin.getConfig().getInt("Mysql.maxPoolSize"));
        main.source.setMinPoolSize(main.plugin.getConfig().getInt("Mysql.minPoolSize"));
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
