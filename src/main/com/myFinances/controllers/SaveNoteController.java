package main.com.myFinances.controllers;
import main.com.myFinances.model.*;
import main.com.myFinances.service.UserService;
import main.com.myFinances.service.UserServiceImpl;
import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 06.05.14.
 */

@Controller
public class SaveNoteController {
    private UserService userService = UserServiceImpl.getInstance();

    @RequestMapping(value = "/saveNote{id}", method = RequestMethod.GET)
    public ModelAndView editNote(@PathVariable String id) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user"); // true == allow create
        if(user == null)return new ModelAndView("redirect:login.html");

        List<Category> categories = UserServiceImpl.getInstance().getAllCategories();
        Notes note = new Notes();

        if(id != null && !id.equals("")) {

            int noteID = Integer.parseInt(id);
            try {

                // проверка, что изменяемая запись принадлежит текущему пользователю
                List<Notes> notesList = userService.getAllNotesByUser(user);
                boolean f = false;
                for(Notes n : notesList){
                    if(n.getId() == noteID) {f =true; break;}
                }
                if (!f) {
                    return new ModelAndView("redirect:home.html");
                }


                note = userService.getNote(noteID);
                attr.setAttribute("oldAmount", note.getAmount(), RequestAttributes.SCOPE_SESSION);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        } else {
            attr.setAttribute("showButtons", "true", RequestAttributes.SCOPE_REQUEST);
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("saveNote");
        mav.addObject("categories", categories);
        mav.addObject("note", note);

        return mav;
    }

    @RequestMapping(value = "/saveNote{id}", method = RequestMethod.POST)
    public String saveNote1( @ModelAttribute("note") Notes note, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user");

        System.out.println("+++++++++++++++++++++++++++++ Try to add note: ");
        System.out.println(note.getId());
        System.out.println(note.getAmount());
        System.out.println(note.getDate());
        System.out.println(note.getCategory().getName() + " " +note.getCategory().getName());
        System.out.println(note.getInfo());

        Double oldAmount = (Double)attr.getRequest().getSession().getAttribute("oldAmount");
        if (oldAmount == null) {
            oldAmount = 0.0;
        }
        attr.getRequest().getSession().removeAttribute("oldAmount");

        double newBalance = oldAmount.doubleValue() - note.getAmount();

        try {
            userService.saveNote(user, note);
            user.addToBalance(newBalance);
            userService.saveUser(user);
            attr.setAttribute("user", user, RequestAttributes.SCOPE_SESSION);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "redirect:home.html";
    }

    @RequestMapping(value = "/saveSpent", method = RequestMethod.POST)
    public String addSpent(@RequestParam("amount") double amount) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User)attr.getRequest().getSession(true).getAttribute("user");
        user.addToBalance(amount);
        try {
            userService.saveUser(user);
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
