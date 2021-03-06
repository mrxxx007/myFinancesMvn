package com.myFinances.dao.templates;

import com.myFinances.dao.UserDAO;
import com.myFinances.dao.mappers.UserMapper;
import com.myFinances.exceptions.UserNotFoundException;
import com.myFinances.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * Created by Sergey Popov on 4/29/2014.
 */
public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(int id) {
        String SQL = "select * from user where id = ?";
        try {
            User user = jdbcTemplate.queryForObject(SQL,new Object[]{id}, new UserMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUser(String login) throws UserNotFoundException {
        String SQL = "select * from user where login = ?";
        try {
            System.out.println("login = \"" + login + "\"");
            User user = jdbcTemplate.queryForObject(SQL, new Object[]{login}, new UserMapper());
            if (user != null) {
                return user;
            }
            throw new UserNotFoundException(login);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(login);
        }
    }

    @Override
    public void saveUser(User user) {
        User fromDB;
        try {
            fromDB = getUser(user.getLogin());
        } catch (UserNotFoundException e) {
            addNewUser(user);
            return;
        }
        user.setId(fromDB.getId());
        updateUser(user);
    }

    @Override
    public void deleteUser(String login) {
        final String SQL = "delete from user where login = ?";
        jdbcTemplate.update(SQL, login);
    }

    private void addNewUser(User user) {
        final String SQL = "insert into user (login, password, balance) values (?, ?, ?)";
        jdbcTemplate.update(SQL, user.getLogin(), user.getPassword(), user.getBalance());
    }

    private void updateUser(User user) {
        final String SQL = "update user set login = ?, password = ?, balance = ? where id = ?";
        jdbcTemplate.update(SQL,user.getLogin(), user.getPassword(), user.getBalance(), user.getId());
    }
}
