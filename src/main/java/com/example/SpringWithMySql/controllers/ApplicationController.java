package com.example.SpringWithMySql.controllers;

import com.example.SpringWithMySql.Dao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by daviesc8 on 29/09/2015.
 */
public class ApplicationController {
    public Dao getDao(){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Dao dao = (Dao) context.getBean("dao");
        return dao;
    }
}
