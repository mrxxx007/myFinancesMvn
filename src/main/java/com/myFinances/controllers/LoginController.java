package com.myFinances.controllers;

/**
 * Created by admin on 05.05.14.
 */

import com.myFinances.exceptions.UserNotFoundException;
import com.myFinances.model.*;
import com.myFinances.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigInteger;
import java.security.MessageDigest;

@Controller
public class LoginController {

    private UserService userService = UserServiceImpl.getInstance();

    @RequestMapping("")
    public String index() {
        return "login";
    }

    @RequestMapping("/login.html")
    public String login(@ModelAttribute("user") User user, BindingResult bindingResult) {


        if (user.getLogin() == null) {
            return "login";
        }

        LoginValidator loginValidator = new LoginValidator();
        loginValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            User databaseUser = userService.getUser(user.getLogin());

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            attr.getRequest().getSession(true).setAttribute("user", databaseUser); // true == allow create

        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        return "redirect:home.html";
    }
}
