package com.github.startzyp.SuperEconomy.Config;

public class DataBaseConfig {
    public static String Driver;
    public static String DbType;
    public static String Database;
    public static String Host;
    public static String Port;
    public static String initialPoolSize;
    public static String maxIdleTime;
    public static String maxPoolSize;
    public static String minPoolSize;


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

    public static String getInitialPoolSize() {
        return initialPoolSize;
    }

    public static void setInitialPoolSize(String initialPoolSize) {
        DataBaseConfig.initialPoolSize = initialPoolSize;
    }

    public static String getMaxIdleTime() {
        return maxIdleTime;
    }

    public static void setMaxIdleTime(String maxIdleTime) {
        DataBaseConfig.maxIdleTime = maxIdleTime;
    }

    public static String getMaxPoolSize() {
        return maxPoolSize;
    }

    public static void setMaxPoolSize(String maxPoolSize) {
        DataBaseConfig.maxPoolSize = maxPoolSize;
    }

    public static String getMinPoolSize() {
        return minPoolSize;
    }

    public static void setMinPoolSize(String minPoolSize) {
        DataBaseConfig.minPoolSize = minPoolSize;
    }
}
