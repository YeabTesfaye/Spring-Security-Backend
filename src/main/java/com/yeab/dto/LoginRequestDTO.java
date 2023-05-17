package com.yeab.dto;


public class LoginRequestDTO {

    public String username;
    public String password;

    public void setPassword(String password){
        this.password = password;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}

