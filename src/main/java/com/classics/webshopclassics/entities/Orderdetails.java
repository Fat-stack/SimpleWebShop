package com.classics.webshopclassics.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(OrderdetailsID.class)
public class Orderdetails {

    /********************* VARIABLES ************************/
    @Id
    private Long ordernumber;
    @Id
    private String productcode;
    private Long quantityordered;
    private float priceeach;
    private Long orderlinenumber;

    public Long getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Long ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public Long getQuantityordered() {
        return quantityordered;
    }

    public void setQuantityordered(Long quantityordered) {
        this.quantityordered = quantityordered;
    }

    public float getPriceeach() {
        return priceeach;
    }

    public void setPriceeach(float priceeach) {
        this.priceeach = priceeach;
    }

    public Long getOrderlinenumber() {
        return orderlinenumber;
    }

    public void setOrderlinenumber(Long orderlinenumber) {
        this.orderlinenumber = orderlinenumber;
    }


    @Override
    public String toString() {
        return "Orderdetails{" +
                "ordernumber=" + ordernumber +
                ", productcode='" + productcode + '\'' +
                ", quantityordered=" + quantityordered +
                ", priceeach=" + priceeach +
                ", orderlinenumber=" + orderlinenumber +
                '}';
    }
}
