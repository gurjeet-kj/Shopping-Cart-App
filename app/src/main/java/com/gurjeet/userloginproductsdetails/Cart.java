package com.gurjeet.userloginproductsdetails;

public class Cart {
    private String prodName;
    private int qty;
    private double total;

    public Cart(String prodName, int qty, double total) {
        this.prodName = prodName;
        this.qty = qty;
        this.total = total;
    }

    public String getProdName() {
        return prodName;
    }

    public int getQty() {
        return qty;
    }

    public double getTotal() {
        return total;
    }
}
