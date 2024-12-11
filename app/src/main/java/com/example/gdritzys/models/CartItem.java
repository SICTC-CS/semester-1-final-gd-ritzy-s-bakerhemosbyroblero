package com.example.gdritzys.models;

import java.util.ArrayList;
import java.util.List;

public class CartItem {
    private MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getSubtotal() { return menuItem.getTotalPrice() * quantity; }

    public String getCustomizationSummary() {
        StringBuilder summary = new StringBuilder();
        
        // Add selected options
        for (ToppingOption topping : menuItem.getToppings()) {
            if (topping.getSelection() != ToppingOption.NORMAL) {
                if (summary.length() > 0) summary.append(", ");
                if (topping.getSelection() == ToppingOption.NONE) {
                    summary.append("No ").append(topping.getName());
                } else {
                    summary.append("Extra ").append(topping.getName());
                }
            }
        }
        
        return summary.toString();
    }
} 