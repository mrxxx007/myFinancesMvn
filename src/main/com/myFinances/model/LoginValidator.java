package main.com.myFinances.model;

/**
 * Created by user on 5/12/2014.
 */

import main.com.myFinances.exceptions.UserNotFoundException;
import main.com.myFinances.service.UserService;
import main.com.myFinances.service.UserServiceImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import main.com.myFinances.service.UserService;
import main.com.myFinances.service.UserServiceImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Евгения on 11.05.14.
 */
public class LoginValidator implements Validator {

    private UserService userService = UserServiceImpl.getInstance();

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        System.out.println("In LoginValidator!!");
        User user = (User) target;
        try {

            User databaseUser = userService.getUser(user.getLogin());

            if (databaseUser == null) {
                errors.rejectValue("login", "login", "login "+user.getLogin()+" is incorrect");
            }

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(user.getPassword().getBytes(), 0, user.getPassword().length());
            String hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);

            System.out.println("MD5");
            System.out.println("Login: " + databaseUser.getLogin());
            System.out.println("Password: " + databaseUser.getPassword());
            System.out.println("hashedPass: " + hashedPass);

            if (!databaseUser.getPassword().equals(hashedPass)) {
                errors.rejectValue("password", "password", "password is incorrect");
            }

        }catch (UserNotFoundException e){
            errors.rejectValue("login", "login", "login "+user.getLogin()+" is incorrect");
        }

        catch (Exception e) {
            System.out.println("Exception");
        }

    }
}
