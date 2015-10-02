package com.example.SpringWithMySql.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nwillia2 on 24/09/15.
 */
public class Todo {
    public long id;
    public String name;
    public String description;

    public Todo(){};

    public Todo(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static class TodoRowMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Todo todo = new Todo(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
            );
            return todo;
        }
    }
}
