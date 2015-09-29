package com.example.SpringWithMySql;

import com.example.SpringWithMySql.models.Todo;

import java.util.List;

/**
 * Created by nwillia2 on 29/09/2015.
 */
public interface JdbcDao {
    public Todo findTodo(int id);
    public List<Todo> getTodos();
}
