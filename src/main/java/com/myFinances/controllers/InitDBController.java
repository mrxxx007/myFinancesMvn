package com.myFinances.controllers;

import com.myFinances.service.UserService;
import com.myFinances.service.UserServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

/**
 * Created by user on 5/23/2014.
 */
@Controller
public class InitDBController {
    private UserService userService = UserServiceImpl.getInstance();

    @RequestMapping(value = "/initdb.html")
    public String initDB() {
        userService.initDB();
        return "login";
    }
}
