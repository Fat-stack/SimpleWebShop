package com.classics.webshopclassics.controllers;


import com.classics.webshopclassics.entities.Product;
import com.classics.webshopclassics.models.ShoppingCart;
import com.classics.webshopclassics.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {

    private float totalPriceSum;



    // istället för @Autowired ! avgör vad som är bäst senare.
    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /******** ADD PRODUCT TO CART******/
    @GetMapping(value= "/add/{productcode}")
    public String addToCart(@PathVariable(name ="productcode") String productcode ) throws IOException, ClassNotFoundException {

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
                        System.out.println("EQUALS");
                    item.setQuantity(item.getQuantity() + 1 );
                        System.out.println(item.getQuantity());
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
    public String ViewCart(Model model) throws IOException, ClassNotFoundException {
        totalPriceSum = 0;

        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        if (shoppingCart.getShoppingCartList() == null) {
            List<Product> tempList = new ArrayList<>();
            shoppingCart.setShoppingCartList(tempList);
            return "redirect:/cart";
        }
        if (shoppingCart.getShoppingCartList().isEmpty()){
            System.out.println("CART EMPTY");
            String strEmpty =" Your Cart contains no Items";
            model.addAttribute("cartIsEmpty",strEmpty);

        }
        if (!(shoppingCart.getShoppingCartList().isEmpty())){
            System.out.println("CART Not emty");
            String strNotEmpty =" Your Cart contains Items";
            model.addAttribute("cartIsNotEmpty",strNotEmpty);

        }

        List<Product> cartViewList= shoppingCart.getShoppingCartList();
        model.addAttribute("cart",cartViewList);


        for (Product cartItem : cartViewList) {
            totalPriceSum += (cartItem.getQuantity()*cartItem.getMsrp());
            }

        model.addAttribute("totPrice", totalPriceSum);
        return "shoppingcart";
    }

@GetMapping("/emtyCart")
    public String EmptyCart() throws IOException, ClassNotFoundException {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        List<Product> tempCart= shoppingCart.getShoppingCartList();
        tempCart.clear();
        shoppingCart.setShoppingCartList(tempCart);
    return "redirect:/cart";
}


    @GetMapping("/inc/{productcode}")
    public String incQuantity(@PathVariable(name ="productcode") String productcode ) throws IOException, ClassNotFoundException {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

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
    public String decQuantity(@PathVariable(name ="productcode") String productcode ) throws IOException, ClassNotFoundException {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

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



}

