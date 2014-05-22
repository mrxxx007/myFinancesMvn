package com.myFinances.dao.mappers;

import com.myFinances.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 01.05.14.
 */
public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category(resultSet.getString("name"));
        category.setId(resultSet.getInt("id"));

        return category;
    }
}
