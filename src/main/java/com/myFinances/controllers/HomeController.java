package com.myFinances.controllers;

import com.myFinances.exceptions.UserNotFoundException;
import com.myFinances.model.*;
import com.myFinances.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 04.05.14.
 */
@Controller
public class HomeController {
    private UserService userService = UserServiceImpl.getInstance();


    @RequestMapping(value = "/home")
    public ModelAndView home() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user"); // true == allow create
        if(user == null)return new ModelAndView("redirect:login.html");

        List<Notes> notes = null;
        try {

            notes = userService.getAllNotesByUser(user);
            System.out.println("++++++++++++++++++++++++++++++++++++ notes.size() = " + notes.size());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return new ModelAndView("home", "notes", notes);
    }
}