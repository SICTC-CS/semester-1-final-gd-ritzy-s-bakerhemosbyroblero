package com.gdritzys.models;

import java.util.ArrayList;

public class MenuItem {
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private ArrayList<String> customizationOptions;
    private String imageUrl;

    public MenuItem(String id, String name, String description, double price, 
                   String category, ArrayList<String> customizationOptions, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.customizationOptions = customizationOptions;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public ArrayList<String> getCustomizationOptions() { return customizationOptions; }
    public String getImageUrl() { return imageUrl; }
} 