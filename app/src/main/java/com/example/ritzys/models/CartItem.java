package com.example.ritzys.models;

public class CartItem {
    private MenuItem menuItem;
    private int quantity;
    private String customizations;
    private double itemPrice;

    public CartItem(MenuItem menuItem, int quantity, String customizations, double itemPrice) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customizations = customizations;
        this.itemPrice = itemPrice;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCustomizations() {
        return customizations;
    }

    public double getTotalPrice() {
        return itemPrice * quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }
} 