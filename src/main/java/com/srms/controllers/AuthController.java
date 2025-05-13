package com.srms.controllers;

import com.srms.entities.User;
import com.srms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired 
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";  // Loads register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/auth/login"; // Redirects to login page
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Loads login.html
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user.getRole().equals("admin") ? "redirect:/admin/home" : "redirect:/student/home";
        } else {
            return "redirect:/auth/login?error"; // Redirects to login with error
        }
    }
}
