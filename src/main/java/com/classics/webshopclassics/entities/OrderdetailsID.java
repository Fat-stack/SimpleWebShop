package com.classics.webshopclassics.entities;


import java.io.Serializable;

public class OrderdetailsID implements Serializable {


    private Long ordernumber;

    private String productcode;

    public OrderdetailsID() {}

    public OrderdetailsID(Long ordernumber, String productcode) {
        this.ordernumber = ordernumber;
        this.productcode = productcode;
    }
}
