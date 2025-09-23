package com.morrisco.net.store.onlineStoreSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/") //Providing an end Point
    public String index(){
        return "index.html";
    }

    @RequestMapping("/hello") //Providing an end Point
    public String helloView(){
        return "index.html";
    }
}
