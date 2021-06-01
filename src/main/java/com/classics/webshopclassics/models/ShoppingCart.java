package com.classics.webshopclassics.models;


import com.classics.webshopclassics.entities.Product;


import java.io.IOException;

import java.util.List;


public class ShoppingCart {


    private List<Product> shoppingCartList;

    private static ShoppingCart shoppingCart;


    // ------- CONSTRUCTORS -------
    private ShoppingCart() throws IOException, ClassNotFoundException {}

    // ------- METHODS -------

    public static ShoppingCart getInstance() throws IOException, ClassNotFoundException {
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }


    //Getters  N Setters



    public List<Product> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<Product> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }


}
