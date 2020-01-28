package com.github.startzyp.SuperEconomy.Util;

import com.github.startzyp.SuperEconomy.main;

public class test {
    public static void ShowConfig(){
        System.out.println(main.plugin.getConfig().getString("Mysql.DbType"));

    }
}
