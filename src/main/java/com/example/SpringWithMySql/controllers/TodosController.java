package com.example.SpringWithMySql.controllers;

import com.example.SpringWithMySql.models.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TodosController extends ApplicationController {

    @ModelAttribute("todos")
    public List<Todo> todos() {
        return getDao().getTodos();
    }

    @RequestMapping("/todos")
    public String index() {
        // loads the todos/index view
        return "todos/index";
    }

}