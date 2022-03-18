package com.hebronworks.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class LoginController {

    @GetMapping("login")
    public String getLogInPage(){
        return "login";
    }

    @GetMapping("courses")
    public String getHomePageView(){
        return "courses";
    }



}
