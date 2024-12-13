package com.example.ritzys.models;

public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String category;

    public MenuItem(String name, String description, double price, String category) { //setting up getters and setters for menu items
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
} 