package com.classics.webshopclassics.controllers;


import com.classics.webshopclassics.entities.Product;
import com.classics.webshopclassics.models.ShoppingCart;
import com.classics.webshopclassics.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("shoppingCart")
public class CartController {

    private float totalPriceSum;


    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /******** ADD PRODUCT TO CART******/
    @GetMapping(value= "/add/{productcode}")
    public String addToCart(final Model model,
                            @ModelAttribute ShoppingCart shoppingCart,
                            @PathVariable(name ="productcode") String productcode ) {

        //NYTT
        Product product = productRepository.findById(productcode).get();

        System.out.println(shoppingCart==null);

        if(shoppingCart.getShoppingCartList() == null){
            ShoppingCart cart = new ShoppingCart();
            System.out.println("IF");
            List<Product> tempList = new ArrayList<>();
            product.setQuantity(product.getQuantity() + 1);
            tempList.add(product);
            shoppingCart.setShoppingCartList(tempList);
            System.out.println("49" + shoppingCart.getShoppingCartList().toString());


            return "redirect:/products";
        }else {
            System.out.println("ELSE");
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
            System.out.println(shoppingCart.getShoppingCartList().toString());
            shoppingCart.setShoppingCartList(tempList);
        }

        return "redirect:/products";
    }



        //NYTT
    /*

        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        Product product = productRepository.findById(productcode).get();

        if (shoppingCart.getShoppingCartList() == null) {
            List<Product> tempList = new ArrayList<>();
            product.setQuantity(product.getQuantity() + 1);
            tempList.add(product);
            shoppingCart.setShoppingCartList(tempList);

            return "redirect:/products";

        } else {
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


*/

    //NYTT
    @GetMapping("/cart")
    public String ViewCart(@ModelAttribute ShoppingCart shoppingCart,
                           final Model model) {
        totalPriceSum = 0;

        System.out.println("TEST122");


        if (shoppingCart.getShoppingCartList() == null) {

            System.out.println("TEST 126");
            List<Product> tempList = new ArrayList<>();
            shoppingCart.setShoppingCartList(tempList);
            return "redirect:/cart";
        }
        if (shoppingCart.getShoppingCartList().isEmpty()){
            System.out.println("test132");
            String strEmpty =" Your Cart contains no Items";
            model.addAttribute("cartIsEmpty",strEmpty);

        }
        if (!(shoppingCart.getShoppingCartList().isEmpty())){
            System.out.println("test138");
            String strNotEmpty =" Your Cart contains Items";
            model.addAttribute("cartIsNotEmpty",strNotEmpty);

        }
        System.out.println("test143");
        List<Product> cartViewList= shoppingCart.getShoppingCartList();
        model.addAttribute("cart",cartViewList);


        for (Product cartItem : cartViewList) {
            totalPriceSum += (cartItem.getQuantity()*cartItem.getMsrp());
        }

        model.addAttribute("totPrice", totalPriceSum);
        return "shoppingcart";
    }
    //NYTT



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

