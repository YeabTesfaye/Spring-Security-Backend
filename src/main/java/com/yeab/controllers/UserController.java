package com.yeab.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin("*")
public class UserController {

    @GetMapping("/user")
    public String helloUserController(){
        return "User access level";
    }
    
}
