package com.classics.webshopclassics.entities;


import javax.persistence.*;

@Entity(name = "product")
@Table(name = "product")
public class Product {

    /********************* CONSTRUCTORS ************************/
    public Product(String productcode, String productname, String productline, String productscale, String productvendor, String productdescription, String quantityinstock, float buyprice, float msrp) {
        this.productcode = productcode;
        this.productname = productname;
        this.productline = productline;
        this.productscale = productscale;
        this.productvendor = productvendor;
        this.productdescription = productdescription;
        this.quantityinstock = quantityinstock;
        this.buyprice = buyprice;
        this.msrp = msrp;
    }
    public Product() {   }

    public Product(String productcode) {
        this.productcode = productcode;
    }


    /********************* VARIABLES ************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productcode;
    private String productname;
    private String productline;
    private String productscale;
    private String productvendor;
    private String productdescription;
    private String quantityinstock;
    private float buyprice;
    private float msrp;

    @Transient
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductline() {
        return productline;
    }

    public void setProductline(String productline) {
        this.productline = productline;
    }

    public String getProductscale() {
        return productscale;
    }

    public void setProductscale(String productscale) {
        this.productscale = productscale;
    }

    public String getProductvendor() {
        return productvendor;
    }

    public void setProductvendor(String productvendor) {
        this.productvendor = productvendor;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getQuantityinstock() {
        return quantityinstock;
    }

    public void setQuantityinstock(String quantityinstock) {
        this.quantityinstock = quantityinstock;
    }

    public float getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(float buyprice) {
        this.buyprice = buyprice;
    }

    public float getMsrp() {
        return msrp;
    }

    public void setMsrp(float msrp) {
        this.msrp = msrp;
    }



}
