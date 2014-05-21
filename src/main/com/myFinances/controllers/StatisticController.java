package main.com.myFinances.controllers;

/**
 * Created by admin on 05.05.14.
 */
import main.com.myFinances.exceptions.UserNotFoundException;
import main.com.myFinances.model.Category;
import main.com.myFinances.model.Notes;
import main.com.myFinances.model.Param;
import main.com.myFinances.model.User;
import main.com.myFinances.service.UserService;
import main.com.myFinances.service.UserServiceImpl;
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
import java.util.List;


@Controller
public class StatisticController {

    private UserService userService = UserServiceImpl.getInstance();;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public ModelAndView get() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user"); // true == allow create
        if(user == null)return new ModelAndView("redirect:login.html");

        List<Category> categories = UserServiceImpl.getInstance().getAllCategories();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("statistic");
        mav.addObject("categories", categories);
        mav.addObject("param", new Param());

        return mav;
    }

    @RequestMapping(value = "/statistic",method = RequestMethod.POST)
    public String filter(@ModelAttribute("param") Param param, Model model) {

        /*// dummy(заглушка)
        User user = new User("test1", "123");
        user.setId(1);*/

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user"); // true == allow create

        String login = user.getLogin();

        List<Category> categories = UserServiceImpl.getInstance().getAllCategories();
        model.addAttribute("categories", categories);

        try {
            //List<Notes> filterednotes = userService.filter(user.getId(), param.getCategoryID(), param.getFromdate(), param.getTodate(), param.getMinamount());
            List<Notes> filterednotes = userService.getNotes(login, param.getFromdate(), param.getTodate(), param.getCategoryID(), param.getMinamount());
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++filterednotes.length = " + filterednotes.size());
            model.addAttribute("filterednotes",filterednotes);
        } /*catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        } */catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        return "statistic";
    }
}
