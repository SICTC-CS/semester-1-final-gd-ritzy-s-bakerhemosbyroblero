package com.gdritzys.models;

import java.util.ArrayList;

public class CartItem {
    private MenuItem menuItem;
    private ArrayList<String> selectedCustomizations;
    private int quantity;
    private double totalPrice;

    public CartItem(MenuItem menuItem, ArrayList<String> customizations, int quantity) {
        this.menuItem = menuItem;
        this.selectedCustomizations = customizations;
        this.quantity = quantity;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = menuItem.getPrice() * quantity;
        // Add extra charges for premium customizations if needed
    }

    public MenuItem getMenuItem() { return menuItem; }
    public ArrayList<String> getSelectedCustomizations() { return selectedCustomizations; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPrice();
    }
} 