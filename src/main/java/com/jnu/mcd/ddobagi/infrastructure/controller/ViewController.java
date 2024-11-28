package com.jnu.mcd.ddobagi.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    private static final String VIEW = "/view";

    @GetMapping
    public String main(){
        return "index";
    }

    @GetMapping(VIEW+"/signup")
    public String signUp(){
        return "signUp.html";
    }

    @GetMapping(VIEW+"/index")
    public String index(){
        return "index.html";
    }

    @GetMapping(VIEW+"/elderly-signup")
    public String elderSignup(){
        return "signUp-elderly.html";
    }

    @GetMapping(VIEW+"/helper-signup")
    public String helperSignup(){
        return "signUp-helper.html";
    }

    @GetMapping(VIEW+"/login")
    public String login(){
        return "login.html";
    }


}
