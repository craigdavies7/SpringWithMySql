package com.example.SpringWithMySql.controllers;

import com.example.SpringWithMySql.JdbcDao;
import com.example.SpringWithMySql.models.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodosController {

    @ModelAttribute("todos")
    public List<Todo> todos() {
        return new JdbcDao().getTodos();
    }

    @RequestMapping("/todos")
    public String index() {
        return "greeting";
    }

}