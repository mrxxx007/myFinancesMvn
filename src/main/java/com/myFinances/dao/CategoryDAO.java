package com.myFinances.dao;

import com.myFinances.exceptions.CategoryNotFoundException;
import com.myFinances.model.Category;

import java.util.List;

/**
 * Created by Sergey Popov on 4/30/2014.
 */
public interface CategoryDAO {
    public Category getCategory(int id) throws CategoryNotFoundException;

    public List<Category> getAllCategories();

    public void saveCategory(Category category, int userId);

    public void deleteCategory(int id);
}
