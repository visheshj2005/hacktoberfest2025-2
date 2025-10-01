package com.jwt.example3.controllers;

import com.jwt.example3.controllers.models.User;
import com.jwt.example3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.List;


@RestController
@RequestMapping("/home")
public class HomeController {
@Autowired
private UserService userService;


//    http://localhost:8081/home/users

    @GetMapping("/users")

        List<User> getUser(){
        System.out.println("getting users");
        return this.userService.getUsers();
    }
}
