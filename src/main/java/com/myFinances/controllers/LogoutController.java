package com.myFinances.controllers;

/**
 * Created by user on 5/7/2014.
 */

import com.myFinances.exceptions.UserNotFoundException;
import com.myFinances.model.User;
import com.myFinances.service.UserService;
import com.myFinances.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;


@Controller
public class LogoutController {

    private UserService userService = UserServiceImpl.getInstance();

    @RequestMapping("/logout.html")
    public String logout() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession(true).invalidate(); // true == allow create
        return "redirect:login.html";
    }
}

