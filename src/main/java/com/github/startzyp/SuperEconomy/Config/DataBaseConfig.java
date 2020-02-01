package com.github.startzyp.SuperEconomy.Config;

public class DataBaseConfig {

    public static String Driver;
    public static String DbType;
    public static String Database;
    public static String Host;
    public static String Port;
    public static String Table;
    public static int initialPoolSize;
    public static int maxIdleTime;
    public static int maxPoolSize;
    public static int minPoolSize;

    public static String getTable() {
        return Table;
    }

    public static void setTable(String table) {
        Table = table;
    }

    public static int getInitialPoolSize() {
        return initialPoolSize;
    }

    public static void setInitialPoolSize(int initialPoolSize) {
        DataBaseConfig.initialPoolSize = initialPoolSize;
    }

    public static int getMaxIdleTime() {
        return maxIdleTime;
    }

    public static void setMaxIdleTime(int maxIdleTime) {
        DataBaseConfig.maxIdleTime = maxIdleTime;
    }

    public static int getMaxPoolSize() {
        return maxPoolSize;
    }

    public static void setMaxPoolSize(int maxPoolSize) {
        DataBaseConfig.maxPoolSize = maxPoolSize;
    }

    public static int getMinPoolSize() {
        return minPoolSize;
    }

    public static void setMinPoolSize(int minPoolSize) {
        DataBaseConfig.minPoolSize = minPoolSize;
    }
    public static String getDriver() {
        return Driver;
    }

    public static void setDriver(String driver) {
        Driver = driver;
    }

    public static String getDbType() {
        return DbType;
    }

    public static void setDbType(String dbType) {
        DbType = dbType;
    }

    public static String getDatabase() {
        return Database;
    }

    public static void setDatabase(String database) {
        Database = database;
    }


    public static String getHost() {
        return Host;
    }

    public static void setHost(String host) {
        Host = host;
    }

    public static String getPort() {
        return Port;
    }

    public static void setPort(String port) {
        Port = port;
    }
}
