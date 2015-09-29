package com.example.SpringWithMySql;

import com.example.SpringWithMySql.models.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        JdbcDao jdbcDao = (JdbcDao) context.getBean("jdbcDao");
//        Todo todo = jdbcDao.findTodo(1);
//        context.close();

        SpringApplication.run(Application.class, args);
    }

}