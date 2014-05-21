package main.com.myFinances.dao;

import main.com.myFinances.exceptions.UserNotFoundException;
import main.com.myFinances.model.User;
import javax.sql.DataSource;

/**
 * Created by Sergey Popov on 4/29/2014.
 */
public interface UserDAO {
    public void setDataSource(DataSource ds);

    public User getUser(int id);

    public User getUser(String login) throws UserNotFoundException;

    public void saveUser(User user);

    public void deleteUser(String login);
}
