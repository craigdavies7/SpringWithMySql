package com.example.SpringWithMySql;

import com.example.SpringWithMySql.models.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by daviesc8 on 24/09/15.
 */
public class Dao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean save(Todo todo) {
        String query = "INSERT into TODOS (name, description) values (?,?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Object[] args = new Object[] {todo.name, todo.description};

        int out = jdbcTemplate.update(query, args);

        return (out != 0);
    }

    public Todo getById(int id) {
        String query =
                "SELECT id, name, description " +
                "from TODOS " +
                "where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Todo todo = jdbcTemplate.queryForObject(query, new Object[]{id}, new Todo.TodoRowMapper());

        return todo;
    }

    public boolean update(Todo todo) {
        String query =
                "UPDATE TODOS set name = ?, description = ? " +
                "where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[] {todo.name, todo.description, todo.id};

        int out = jdbcTemplate.update(query, args);
        return (out != 0);
    }

    public boolean deleteById(int id) {

        String query = "DELETE from TODOS where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int out = jdbcTemplate.update(query, id);
        return (out != 0);
    }

    public List<Todo> getTodos(){
        String sql = "SELECT * FROM TODOS";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Todo> todos = jdbcTemplate.query(sql, new Todo.TodoRowMapper());
        return todos;
    }
}
