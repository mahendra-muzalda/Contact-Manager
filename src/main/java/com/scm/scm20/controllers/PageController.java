package com.scm.scm20.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping("/home")
    public String home(Model model){
        logger.info("**************************Welcome to home page**************************");
        model.addAttribute("name","Substring");
        model.addAttribute("YouTube","Learn code with Durgesh");
        model.addAttribute("githubRepo", "https://github.com/");
        model.addAttribute("indexPage", "http://localhost:8081/home/index");
        return "home";
    }

    @RequestMapping("/services")
    public String services() {
        return "services";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    //contact page
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }

    @GetMapping("/login")
    public String login(){

        return new String("login");
    }

    @GetMapping("/singup")
    public String singup(){

        return "singup";
    }

    
}