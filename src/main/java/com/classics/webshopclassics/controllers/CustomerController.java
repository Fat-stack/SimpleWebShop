package com.classics.webshopclassics.controllers;

import com.classics.webshopclassics.entities.Customers;

import com.classics.webshopclassics.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/new")
    public String addNewCustomer(Model model) {
        Customers customers = new Customers();
        model.addAttribute("customers", customers);
        return "new-customer";
    }



    @PostMapping(value = "/saveAndRegister")
    public RedirectView PostRG(
            HttpServletRequest request,
            @ModelAttribute Customers customers,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("customers", customers);

        customerRepository.save(customers);

        //TRACKING
        System.out.println("customerController / saveAndRegister");
        System.out.println(customers.getCustomernumber());
        //TRACKING

        return new RedirectView("/create-new-order", true);
    }
}