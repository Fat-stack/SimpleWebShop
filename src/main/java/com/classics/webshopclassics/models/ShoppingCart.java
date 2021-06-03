package com.classics.webshopclassics.models;


import com.classics.webshopclassics.entities.Product;



import java.util.List;
import java.util.Objects;


public class ShoppingCart {


    private List<Product> shoppingCartList;



    // ------- CONSTRUCTORS -------
    //NYTT



    //NYTT
   /*
    private ShoppingCart(){}
*/
    // ------- METHODS -------




    //Getters  N Setters



    public List<Product> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<Product> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }



    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartList=" + shoppingCartList +
                '}';


    }
}
