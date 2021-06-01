package com.classics.webshopclassics.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("cart")
public class FrontController {


    @GetMapping("/")
    public RedirectView homePage() {
        return new RedirectView("home");
    }


}
