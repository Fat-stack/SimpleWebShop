package com.classics.webshopclassics.controllers;




import com.classics.webshopclassics.entities.Product;
import com.classics.webshopclassics.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/home")
    public String viewFrontTop10(Model model){
        List<Product> product = productRepository.findTop10ByOrderByProductcodeAsc();
        model.addAttribute("product", product);
        System.out.println("Productcontroller viewtop10");
        return "index";
    }



    @GetMapping("/products")
    public String viewAllProducts(Model model){
        List<Product> product = productRepository.findAll();
        model.addAttribute("product", product);
        System.out.println("viewallProducts");
        return "all-products";
    }


    @GetMapping("/details/{productcode}")
    public ModelAndView showProductPage(@PathVariable(name = "productcode") String productCode) {
        ModelAndView modelAndView = new ModelAndView("product-details");
        Product product = productRepository.findById(productCode).get();
        modelAndView.addObject("product", product);
        System.out.println("productController/ (/details/{productcode})");
        return modelAndView;
    }


}