package com.gurjeet.userloginproductsdetails;

public class Product {
    private String name;
    private double price;
    private int img;
    private String description;

    public Product(String name, double price, int img, String description) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }
}
