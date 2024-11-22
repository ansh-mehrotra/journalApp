package com.spring.journalApp.controller;

import com.spring.journalApp.entity.User;
import com.spring.journalApp.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private userService  userService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAll(){
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin-user")
    public void createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
    }
}
