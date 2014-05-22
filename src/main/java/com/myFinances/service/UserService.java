package com.myFinances.service;

import com.myFinances.exceptions.CategoryNotFoundException;

import com.myFinances.exceptions.*;
import com.myFinances.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 29.04.14.
 */
public interface UserService {


    public void saveUser(User user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    public User getUser(String login) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UserNotFoundException;

    public void deleteUser(String login);

    public Notes getNote(int id) throws NoteNotFoundException;

    public int saveNote(User user, Notes note) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    public List<Notes> getAllNotesByUser(User user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    public List<Notes> getNotes(String login, Date beginDate, Date endDate, int categoryId) throws UserNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    public List<Notes> getNotes(String login, Date beginDate, Date endDate, int categoryId, double amount) throws UserNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    public void deleteNote(int id);

    public Category getCategory(int id) throws CategoryNotFoundException;

    public List<Category> getAllCategories();

    public void saveCategory(Category category, int userId);

    public void deleteCategory(int id);

    public double getSumByMonth(User user);

    void initDB();
}
