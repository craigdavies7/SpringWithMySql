package com.example.SpringWithMySql.models;

/**
 * Created by nwillia2 on 24/09/15.
 */
public class Todo {
    public long id;
    public String name;
    public String description;

    public Todo(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
