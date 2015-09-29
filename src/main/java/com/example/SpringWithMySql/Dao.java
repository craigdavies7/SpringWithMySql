package com.example.SpringWithMySql;

import com.example.SpringWithMySql.models.Todo;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nwillia2 on 24/09/15.
 */
public class Dao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Todo findTodo(int id){
        String sql = "SELECT * FROM TODOS WHERE ID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            Todo todo = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                todo = new Todo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
            rs.close();
            ps.close();
            return todo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public List<Todo> getTodos(){
        String sql = "SELECT * FROM TODOS";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Todo todo = null;
            ArrayList<Todo> todos = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                todo = new Todo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                todos.add(todo);
            }
            rs.close();
            ps.close();
            return todos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}
