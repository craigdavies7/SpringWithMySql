package com.example.SpringWithMySql.controllers;

import com.example.SpringWithMySql.models.Todo;
import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TodosController extends ApplicationController {

    //HOMEPAGE
    @RequestMapping(value = "todos/home", method = RequestMethod.GET)
    public String home(){
        //loads up Home Page
        return "todos/home";
    }

    //Login Form
    @RequestMapping(value="/todos/login", method = RequestMethod.GET)
    public String login(){
        //loads up Login Page
        return "todos/login";
    }





    //BELOW HERE IS ALL THE TODOS CODE WHICH WORKS!! DO NOT DELETE!!
    @RequestMapping(value="/todos", method= RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("todos", getDao().getTodos());
        return "todos/index";
    }

    @RequestMapping(value="/todos/{id}", method= RequestMethod.GET)
    public String show(@PathVariable("id") int id, Model model) {
        // loads up selected record
        model.addAttribute("todo", getDao().getById(id));
        return "todos/index";
    }

    @RequestMapping(value="/todos/new", method= RequestMethod.GET)
    public String newTodo() {
        return "todos/new";
    }


    @RequestMapping(value="/todos", method= RequestMethod.POST)
    public String create(
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

    @RequestMapping(value="/todos/{id}/edit", method= RequestMethod.GET)
    public String editTodo(@PathVariable("id") int id, Model model){
        model.addAttribute("todo", getDao().getById(id));
        return "todos/edit";
    }

    @RequestMapping(value="/todos/{id}", method= RequestMethod.POST)
    public String updateTodo(@ModelAttribute("todo") Todo todo,
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("description") String description, Model model,
            final RedirectAttributes redirectAttributes) {

        todo.id = id;
        todo.name = name;
        todo.description = description;
        getDao().update(todo);

        redirectAttributes.addFlashAttribute("message", "Record updated successfully.");

        model.addAttribute("todos", getDao().getTodos());

        return "redirect:/todos";
    }

    @RequestMapping(value="/todos/delete/{id}", method = RequestMethod.GET)
    public String deleteTodo(@PathVariable("id") int id, Model model,
                             final RedirectAttributes redirectAttributes){

        getDao().deleteById(id);

        model.addAttribute("todos", getDao().getTodos());

        redirectAttributes.addFlashAttribute("message", "Record Deleted Successfully.");

        return "redirect:/todos";
    }
}