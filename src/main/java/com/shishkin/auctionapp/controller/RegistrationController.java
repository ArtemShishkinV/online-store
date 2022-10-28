package com.shishkin.auctionapp.controller;

import com.shishkin.auctionapp.model.Category;
import com.shishkin.auctionapp.model.User;
import com.shishkin.auctionapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;

    @GetMapping
    public String index(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping
    public String registration(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.add(user);
        return "redirect:/login";
    }
}
