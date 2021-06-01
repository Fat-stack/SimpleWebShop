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
        System.out.println(" CustomerController / new");
        return "new-customer";
    }

/*

    @PostMapping(value = "/saveAndRegister")
    public String saveCostumerAndPassCustNr(@ModelAttribute("costumers") Customers customers){
        System.out.println("customerController / saveAndRegister");
        customerRepository.save(customers);



        //TEST
        long ordNr = customers.getCustomernumber();

        //TEST


        return "redirect:/create-new-order/" +"{" + ordNr + "}";
    }

 */

    /***TEST AREA****/
    @PostMapping(value = "/saveAndRegister")
    public RedirectView PostRG(
            HttpServletRequest request,
            @ModelAttribute Customers customers,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("customers", customers);


        System.out.println("customerController / saveAndRegister");

        customerRepository.save(customers);

        System.out.println(customers.getCustomernumber());
    //TEST

        return new RedirectView("/create-new-order", true);


}


    /***TEST AREA****/


/*    @PostMapping(value = "/save")
    public String saveCustomer(@Valid Customers customers, BindingResult result) {
        if (result.hasErrors()){
            return "new-customer";
        }
        customerRepository.save(customers);
        return "redirect:/thank-you-page";
    }

 */
}