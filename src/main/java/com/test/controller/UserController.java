package com.test.controller;

import com.test.model.Order;
import com.test.model.User;
import com.test.service.ProductService;
import com.test.service.UserService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private User user;

    @Autowired
    private ProductService productService;

    ArrayList<Order> orderList = new ArrayList<Order>();

    @RequestMapping(method = RequestMethod.GET)
    public String startPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginAccount(ModelMap model, HttpServletRequest request, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("print", "Access denied.");
            return "index";
        }
        try {
            if (userService.checkAccess(user)) {
                this.user = userService.getUserByLogin(user.getLogin());
                HttpSession session = request.getSession();
                request.setAttribute("user", this.user);
//                session.setAttribute("user", this.user);
                if (user.getLogin().equals("admin")) {
                    model.addAttribute("print", "Hi admin. Make a choise");
                    return "/adminPage";
                } else {
                    session.setAttribute("orderList", orderList);
                    return "redirect:/clientPage/Hi " + this.user.getName() + ". Please make an order.";
                }
            } else {
                model.addAttribute("print", "Access denied.");
                return "index";
            }
        } catch (Exception e) {
            model.addAttribute("print", "Sorry. Try again.");
            return "index";
        }
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String returnToAdminPage(ModelMap model) {
        return "adminPage";
    }

    @RequestMapping(value = "/clientPage/{print}", method = RequestMethod.GET)
    public String returnToClientPage(@PathVariable("print") String print, ModelMap model, HttpSession session) {
        model.addAttribute("print", print);
        model.addAttribute("listProducts", productService.listProducts());
        model.addAttribute("order", new Order());
        return "clientPage";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registrationUser(ModelMap model, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            if (userService.checkUserCreated(user.getLogin())) {
                model.addAttribute("print", "User with login " + user.getLogin() + " is currently created. Please try again.");
                return "registration";
            }
            this.user = user;
            userService.addUserToDataBase(user);
        } catch (Exception e) {
            model.addAttribute("print", "Sorry. Try again");
            return "registration";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/users/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/edit/{id}")
    public String editUser(@PathVariable("id") int id) {
        User user = userService.getUserByID(id);
        user.setAddedToBlackList(true);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/edit2/{id}")
    public String deleteFromBlackList(@PathVariable("id") int id) {
        User user = userService.getUserByID(id);
        user.setAddedToBlackList(false);
        userService.updateUser(user);
        return "redirect:/usersBlackList";
    }

    @RequestMapping(value = "/usersBlackList", method = RequestMethod.GET)
    public String showUserBlackList(ModelMap model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "usersBlackList";
    }
}
