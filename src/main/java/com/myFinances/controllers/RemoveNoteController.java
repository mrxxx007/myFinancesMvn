package com.myFinances.controllers;

import com.myFinances.exceptions.NoteNotFoundException;
import com.myFinances.model.Notes;
import com.myFinances.model.User;
import com.myFinances.service.UserService;
import com.myFinances.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Евгения on 11.05.14.
 */
@Controller
public class RemoveNoteController {

    private UserService userService = UserServiceImpl.getInstance();

    @RequestMapping(value = "/removeNote{id}", method = RequestMethod.GET)
    public String removeNote(@PathVariable Integer id) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user");

        try {
            user.addToBalance(userService.getNote(id).getAmount());
            userService.deleteNote(id);
            userService.saveUser(user);
        } catch (NoteNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "redirect:home.html";

    }
}