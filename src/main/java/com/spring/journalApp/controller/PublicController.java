package com.spring.journalApp.controller;

import com.spring.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
class PublicController {

    @Autowired
    private com.spring.journalApp.services.userService userService;


    @GetMapping("/health-check")
    String helthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
