
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

import javax.validation.Valid;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {

    private UserService userService = UserServiceImpl.getInstance();

    @RequestMapping("/register.html")
    public String login(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        System.out.println("++++++++++++++++++++++++++++++++++++ user login password :" + user.getLogin() +" "+ user.getPassword());
        RegisterValidator registerValidator = new RegisterValidator();
        registerValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if(user.getLogin() == null){
            return "register";
        }

        if(user.getPassword() == null){
            return "register";
        }

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(user.getPassword().getBytes(), 0, user.getPassword().length());
            String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);
            if (hashedPass.length() < 32) {
                hashedPass = "0" + hashedPass;
            }

            user.setPassword(hashedPass);
            userService.saveUser(user);

            System.out.println("Password: "+ user.getPassword());

            User databaseUser = userService.getUser(user.getLogin());

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            attr.getRequest().getSession(true).setAttribute("user", databaseUser); // true == allow create

        } catch (NoSuchAlgorithmException nsa) {
            System.out.println("No such algoritm");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return "redirect:home.html";
    }
}