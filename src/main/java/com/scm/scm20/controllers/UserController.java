package com.scm.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
// This controller handles user-related operations
public class UserController {

    //user dashboard
    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        return "user/dashboard"; // Return the view for user dashboard
    }

    //user profile page
    @RequestMapping(value = "/profile")
    public String userProfile() {
        return "user/profile"; // Return the view for user profile
    }

    //user add contact page

    //user view contact page

    //user edit contact page

    //user delete contact page
}
