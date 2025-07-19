package com.scm.scm20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.scm20.entities.User;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.services.UserService;

import helper.Message;
import helper.MessageType;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping("/home")
    public String home(Model model) {
        logger.info("**************************Welcome to home page**************************");
        model.addAttribute("name", "Substring");
        model.addAttribute("YouTube", "Learn code with Durgesh");
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

    // contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {

        return new String("login");
    }

    @GetMapping("/singup")
    public String singup(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi dal sakte hai

        // userForm.setName("Substring");
        // userForm.setEmail("substring@gmail.com");
        // userForm.setPassword("substring");
        // userForm.setPhoneNumber("1234567890");
        // userForm.setAbout("I am a software engineer");

        userForm.setName("Substring");
        userForm.setEmail("substring@gmail.com");
        userForm.setPassword("substring");
        userForm.setPhoneNumber("1234567890");
        userForm.setAbout("I am a software engineer");

        model.addAttribute("userForm", userForm);

        return "singup";
    }

    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session) {
        System.out.println("processing register......");

        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if (rBindingResult.hasErrors()) {
            
            return "singup"; // return to the same page with errors
            
        }

        // save to database
        // userservice

        //userform se data nikal ke user me dal diya
        // User user = User.builder()
        //         .name(userForm.getName())
        //         .email(userForm.getEmail())
        //         .password(userForm.getPassword())
        //         .about(userForm.getAbout())
        //         .phoneNumber(userForm.getPhoneNumber())
        //         .profilePic("https://search.brave.com/images?q=default%20profile%20pictures")
        //         .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://search.brave.com/images?q=default%20profile%20pictures");
        user.setEnabled(true);
        user.setEmailVerified(true);

        User savedUser = userService.saveUser(user);
        System.out.println("user saved .............");
        // messege = "Registration Successful"

        //add the message
        Message message = Message.builder()
                .content("Registration Successful")
                .type(MessageType.green) // success message
                .build();

        session.setAttribute("message", message);

        // redirect home page
        return "redirect:/singup";
    }

}