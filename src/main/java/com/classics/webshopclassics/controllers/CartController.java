package com.classics.webshopclassics.controllers;


import com.classics.webshopclassics.entities.Product;
import com.classics.webshopclassics.models.ShoppingCart;
import com.classics.webshopclassics.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("shoppingCart")
public class CartController {


    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

///////

    @GetMapping(value= "/addFromFirstPage/{productcode}")
    public String addToCartFromfirstPage(@ModelAttribute ShoppingCart shoppingCart,
                            @PathVariable(name ="productcode") String productcode ) {


        Product product = productRepository.findById(productcode).get();

        System.out.println(shoppingCart==null);

        if(shoppingCart.getShoppingCartList() == null){
            List<Product> tempList = new ArrayList<>();
            product.setQuantity(product.getQuantity() + 1);
            tempList.add(product);
            shoppingCart.setShoppingCartList(tempList);



            return "redirect:/";
        }else {
            List<Product> tempList = shoppingCart.getShoppingCartList();
            for (Product item: tempList) {

                if (item.getProductcode().equals(product.getProductcode())){

                    item.setQuantity(item.getQuantity() + 1 );

                    shoppingCart.setShoppingCartList(tempList);

                    return "redirect:/";
                }
            }

            product.setQuantity(1);
            tempList.add(product);
            shoppingCart.setShoppingCartList(tempList);
        }

        return "redirect:/";
    }
    /////////

    @GetMapping(value= "/add/{productcode}")
    public String addToCart(@ModelAttribute ShoppingCart shoppingCart,
                            @PathVariable(name ="productcode") String productcode ) {


        Product product = productRepository.findById(productcode).get();

        System.out.println(shoppingCart==null);

        if(shoppingCart.getShoppingCartList() == null){
            List<Product> tempList = new ArrayList<>();
            product.setQuantity(product.getQuantity() + 1);
            tempList.add(product);
            shoppingCart.setShoppingCartList(tempList);



            return "redirect:/products";
        }else {
            List<Product> tempList = shoppingCart.getShoppingCartList();
            for (Product item: tempList) {

                if (item.getProductcode().equals(product.getProductcode())){

                    item.setQuantity(item.getQuantity() + 1 );

                    shoppingCart.setShoppingCartList(tempList);

                    return "redirect:/products";
                }
            }

            product.setQuantity(1);
            tempList.add(product);
            shoppingCart.setShoppingCartList(tempList);
        }

        return "redirect:/products";
    }




    @GetMapping("/cart")
    public String ViewCart(@ModelAttribute ShoppingCart shoppingCart,
                           final Model model) {
        float totalPriceSum = 0;

        System.out.println("TEST122");


        if (shoppingCart.getShoppingCartList() == null) {

            System.out.println("TEST 126");
            List<Product> tempList = new ArrayList<>();
            shoppingCart.setShoppingCartList(tempList);
            return "redirect:/cart";
        }
        if (shoppingCart.getShoppingCartList().isEmpty()){
            System.out.println("test132");
            String strEmpty = "The Shoppingcart is empty";
            String strHidden = "hidden";
            model.addAttribute("cartIsEmpty",strEmpty);
            model.addAttribute("hideWhenCartIsEmpty",strHidden);

        }

        System.out.println("test143");
        List<Product> cartViewList= shoppingCart.getShoppingCartList();
        model.addAttribute("cart",cartViewList);


        for (Product cartItem : cartViewList) {
            totalPriceSum += (cartItem.getQuantity()*cartItem.getMsrp());
        }

        //
        DecimalFormat df = new DecimalFormat("###.###");
        //
        System.out.println(df.format(totalPriceSum));

        model.addAttribute("totPrice", df.format(totalPriceSum));
        return "shoppingcart";
    }




@GetMapping("/emtyCart")
    public String EmptyCart(@ModelAttribute ShoppingCart shoppingCart){

        List<Product> tempList= shoppingCart.getShoppingCartList();
        tempList.clear();
        shoppingCart.setShoppingCartList(tempList);
    return "redirect:/cart";
}


    @GetMapping("/inc/{productcode}")
    public String incQuantity(@ModelAttribute ShoppingCart shoppingCart,
                              @PathVariable(name ="productcode") String productcode ) {


        List<Product> tempList = shoppingCart.getShoppingCartList();

        for (Product item: tempList) {
            if (item.getProductcode().equals(productcode)){
                item.setQuantity(item.getQuantity()+1);
                shoppingCart.setShoppingCartList(tempList);
                return "redirect:/cart";
            }
        }
        return "redirect:/cart";
    }


    @GetMapping("/dec/{productcode}")
    public String decQuantity(@ModelAttribute ShoppingCart shoppingCart,
                              @PathVariable(name ="productcode") String productcode ){


        List<Product> tempList = shoppingCart.getShoppingCartList();

        for (Product item: tempList) {
            if (item.getProductcode().equals(productcode)){
                item.setQuantity(item.getQuantity()-1);

                if (item.getQuantity()==0){
                    tempList.remove(item);
                }
                shoppingCart.setShoppingCartList(tempList);
                return "redirect:/cart";
            }
        }

        return "redirect:/cart";
    }


    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }






}

