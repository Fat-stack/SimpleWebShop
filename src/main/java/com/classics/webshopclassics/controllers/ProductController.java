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

    /********************* FÖRSTASIDA ************************/

    @GetMapping("/home")
    public String viewFrontTop10(Model model){
        List<Product> product = productRepository.findTop10ByOrderByProductcodeAsc();
        model.addAttribute("product", product);
        System.out.println("Productcontroller viewtop10");
        return "index";
    }

    /********************* ALLA PRODUKTER FRPN DB************************/

    @GetMapping("/products")
    public String viewAllProducts(Model model){
        List<Product> product = productRepository.findAll();
        model.addAttribute("product", product);
        System.out.println("viewallProducts");
        return "all-products";
    }

    // Varukorg som visar max en produkt och möjligheten att öka/minska, samt gå vidare
//    @GetMapping("/cart")
//    public String showNewProductPage(Model model) {
//        Products products = new Products();
//        model.addAttribute("products", products);
//        return "shopping-cart";
//    }

    /* PRODUKTSIDA - DETALJER
     *  Vi behöver ordna så att vi efter anv dirigerats till detaljsidan inte dirigeras fel tillbaka
     * */
    @GetMapping("/details/{productcode}")
    public ModelAndView showProductPage(@PathVariable(name = "productcode") String productCode) {
        ModelAndView modelAndView = new ModelAndView("product-details");
        Product product = productRepository.findById(productCode).get();
        modelAndView.addObject("product", product);
        System.out.println("productController/ (/details/{productcode})");
        return modelAndView;
    }

    /* LÄGGA TILL I VARUKORG (broken)
     * Vi behöver en funktion som lägger till varan i varukorgen men låter användaren stanna kvar på sidan
     *
    @GetMapping("/add/{productcode}")
    public ModelAndView addToCart(@PathVariable(name = "productcode") String productcode) {
        ModelAndView modelAndView = new ModelAndView("shopping-cartTEMP");
        Product product = productRepository.findById(productcode).get();
        modelAndView.addObject("product", product);
        return modelAndView;
    }
*/


}