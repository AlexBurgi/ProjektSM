package com.burgholzer.shoppingapp.model;

public class Product {
    private int id;
    private String name;
    private String image;
    private Subcategory subcategory;

    public Product(int id, String name, String image, Subcategory subcategory) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.subcategory = subcategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
