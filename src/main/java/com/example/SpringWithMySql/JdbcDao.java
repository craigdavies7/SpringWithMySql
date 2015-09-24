package com.example.SpringWithMySql;

import com.example.SpringWithMySql.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nwillia2 on 24/09/15.
 */
@Repository
public class JdbcDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Todo> getTodos(){
        jdbcTemplate.setIgnoreWarnings(false);

        return jdbcTemplate.query(
                "SELECT id, name, description " +
                        "FROM todos " +
                        "ORDER BY name",
                new RowMapper<Todo>() {
                    @Override
                    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Todo(rs.getLong("id"), rs.getString("name"), rs.getString("description"));
                    }
                }
        );
    }
}
