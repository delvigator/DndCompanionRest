//package com.example.checkers.controllers;
//
//
//import com.example.checkers.models.User;
//import com.example.checkers.services.UserService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//import java.util.concurrent.atomic.AtomicLong;
//
//@RestController
//@Slf4j
//@AllArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//
////    @GetMapping("/login")
////    public String login(Principal principal, Model model) {
////        model.addAttribute("user", userService.getUserByPrincipal(principal));
////        return "login";
////    }
////
////    @GetMapping("/registration")
////    public String registration() {
////        log.info("called get mapping registration");
////        return "registration";
////    }
////
////
////    @PostMapping("/registration")
////    public String createUser(User user, Model model) {
////        log.info("called post mapping registration");
//////        if (!userService.createUser(user)) {
//////            model.addAttribute("errorMessage", "Пользователь с логином: " + user.getUsername() + " уже существует");
//////            return "registration";
//////        }
////        userService.createUser(user);
////        return "redirect:/hello";
////    }
////
////    @GetMapping("/hello")
////    public String securityUrl() {
////        return "hello";
////    }
//}
//
