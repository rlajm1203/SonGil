package com.jnu.mcd.ddobagi.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/signUp")
    public String signUp(){
        return "signUp.html";
    }

    @GetMapping("/index")
    public String index(){
        return "index.html";
    }


}
