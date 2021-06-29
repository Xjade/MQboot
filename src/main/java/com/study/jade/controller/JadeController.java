package com.study.jade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping(value = "/test")
public class JadeController {

    @RequestMapping(value = "/home")
    public String homePage(){
        return "/home.html";
    }
}
