package com.example.SpringWithMySql.controllers;

import com.example.SpringWithMySql.models.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TodosController extends ApplicationController {
    @RequestMapping(value="/todos", method= RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("todos", getDao().getTodos());
        return "todos/index";
    }

    @RequestMapping(value="/todos/new", method= RequestMethod.GET)
    public String newTodo() {
        return "todos/new";
    }

    @RequestMapping(value="/todos/edit", method= RequestMethod.GET)
    public String editTodo() {
        return "todos/new";
    }

    @RequestMapping(value="/todos", method= RequestMethod.POST)
    public String createTodo(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            final RedirectAttributes redirectAttributes) {

        Todo todo = new Todo();
        todo.name = name;
        todo.description = description;
        getDao().save(todo);

        redirectAttributes.addFlashAttribute("message", "Record added successfully.");

        return "redirect:/todos";
    }
}