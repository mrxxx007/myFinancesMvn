package main.com.myFinances.exceptions;

/**
 * Created by Admin on 01.05.14.
 */
public class UserNotFoundException extends DAOException {
    public UserNotFoundException(String userName) {
        super("User \"" + userName + "\" not yet registered");
    }
}
