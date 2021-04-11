package com.example.userAuthentication.controller;

import com.example.userAuthentication.model.User;
import com.example.userAuthentication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signUpView()
    {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model){

        String signUpError = "";
        if(!userService.isUsernameAvailable(user.getUsername()))
        {
            signUpError = "User already exists";
        }

        if(signUpError.isEmpty())
        {
            int rowsAdded = userService.createUser(user);
            if(rowsAdded < 0){
                signUpError = "There was an error signing you up";
            }
        }
        if(signUpError == null){
            model.addAttribute("signUpSuccess",true);
        }
        else
        {
            model.addAttribute("signUpError",signUpError);
        }
        return "signup";
    }




}
