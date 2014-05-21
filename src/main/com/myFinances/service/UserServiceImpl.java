package main.com.myFinances.service;

import main.com.myFinances.dao.templates.*;

import main.com.myFinances.exceptions.*;

import main.com.myFinances.model.*;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 29.04.14.
 */
public class UserServiceImpl implements UserService {

    private ApplicationContext context;

    private UserServiceImpl() {}

    public static UserServiceImpl instance;
    public static UserServiceImpl getInstance(){
        if (instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public void _init(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void saveUser(User user)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
        userJDBCTemplate.saveUser(user);
    }

    @Override
    public User getUser(String login)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UserNotFoundException {
        UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
        return userJDBCTemplate.getUser(login);
    }

    @Override
    public void deleteUser(String login) {
        UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
        userJDBCTemplate.deleteUser(login);
    }


    @Override
    public int saveNote(User user, Notes note)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        NotesJDBCTemplate notesJDBCTemplate = (NotesJDBCTemplate)context.getBean("notesJDBCTemplate");
        return notesJDBCTemplate.saveNote(note, user.getId());
    }

    @Override
    public List<Notes> getAllNotesByUser(User user)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        NotesJDBCTemplate notesJDBCTemplate = (NotesJDBCTemplate)context.getBean("notesJDBCTemplate");

        return notesJDBCTemplate.getNotesForUser(user.getId());
    }

    @Override
    public List<Notes> getNotes(String login, Date beginDate, Date endDate, int categoryId) throws UserNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        NotesJDBCTemplate notesJDBCTemplate = (NotesJDBCTemplate)context.getBean("notesJDBCTemplate");
        return notesJDBCTemplate.getNotes(login, beginDate, endDate, categoryId, null );
    }

    @Override
    public List<Notes> getNotes(String login, Date beginDate, Date endDate, int categoryId, double amount) throws UserNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        NotesJDBCTemplate notesJDBCTemplate = (NotesJDBCTemplate)context.getBean("notesJDBCTemplate");
        return notesJDBCTemplate.getNotes(login, beginDate, endDate, categoryId, amount );
    }

    @Override
    public void deleteNote(int id) {
        NotesJDBCTemplate notesJDBCTemplate = (NotesJDBCTemplate)context.getBean("notesJDBCTemplate");
        notesJDBCTemplate.deleteNote(id);
    }

    @Override
    public Notes getNote(int id) throws NoteNotFoundException {
        NotesJDBCTemplate notesJDBCTemplate = (NotesJDBCTemplate)context.getBean("notesJDBCTemplate");
        return notesJDBCTemplate.getNote(id);
    }

    @Override
    public Category getCategory(int id) throws CategoryNotFoundException {
        CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
        return categoryJDBCTemplate.getCategory(id);
    }

    @Override
    public List<Category> getAllCategories() {
        CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
        return categoryJDBCTemplate.getAllCategories();
    }

    @Override
    public void saveCategory(Category category, int userId) {
        CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
        categoryJDBCTemplate.saveCategory(category, userId);
    }

    @Override
    public void deleteCategory(int id) {
        CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
        categoryJDBCTemplate.deleteCategory(id);
    }

    @Override
    public double getSumByMonth(User user) {
        return 0;
    }
}
