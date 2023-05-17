package com.yeab.controllers;

import com.yeab.dto.LoginRequestDTO;
import com.yeab.models.ApplicationUser;
import com.yeab.dto.LoginResponseDTO;
import com.yeab.dto.RegistrationDTO;
import com.yeab.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body);
    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO loginResponseDTO){
        return authenticationService.loginUser(loginResponseDTO);
    }
}   
