package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/sign-up")
    public String signup(@Valid @ModelAttribute User user) {
        userService.signup(user);
        try {
            return "redirect:/sign-in";
        } catch (Exception e) {
            return "signup";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        try {
            userService.login(user.getUsername(), user.getPassword());
            return "redirect:/";
        }
        catch (Exception e){
            return "login";
        }
    }

}
