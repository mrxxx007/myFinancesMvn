package com.myFinances.dao.templates;

import com.myFinances.dao.CategoryDAO;
import com.myFinances.dao.mappers.CategoryMapper;
import com.myFinances.exceptions.CategoryNotFoundException;
import com.myFinances.model.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Admin on 01.05.14.
 */
public class CategoryJDBCTemplate implements CategoryDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Category getCategory(int id) throws CategoryNotFoundException {
        String SQL = "select * from category where id = ?";
        try {
            Category category = jdbcTemplate.queryForObject(SQL,new Object[]{id}, new CategoryMapper());
            return category;
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        String SQL = "select * from category";
        return jdbcTemplate.query(SQL, new CategoryMapper());
    }

    @Override
    public void saveCategory(Category category, int userId) {
        if (category.getId() > 0) {
            updateCategory(category, userId);
        } else {
            insertCategory(category, userId);
        }
    }

    @Override
    public void deleteCategory(int id) {
        String SQL = "delete from category where id = ?;";
        jdbcTemplate.update(SQL, id);
    }

    private void insertCategory(Category category, int userId) {
        String SQL = "insert into category (name, user_id) values (?, ?)";
        jdbcTemplate.update(SQL, category.getName(), userId);
    }

    private void updateCategory(Category category, int userId) {
        String SQL = "update category set name = ?, user_id = ? where id = ?";
        jdbcTemplate.update(SQL, category.getName(), userId, category.getId());
    }

}
