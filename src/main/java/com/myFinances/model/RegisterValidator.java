package com.myFinances.model;

import com.myFinances.service.UserService;
import com.myFinances.service.UserServiceImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Евгения on 11.05.14.
 */
public class RegisterValidator implements Validator {

    private UserService userService = UserServiceImpl.getInstance();

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            if (userService.getUser(user.getLogin()) != null) {
                errors.rejectValue("login", "login", "login "+user.getLogin()+" is already in use");
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }

    }
}