package com.example.ritzys.models;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> items;

    private CartManager() {
        items = new ArrayList<>();
    }

    public static CartManager getInstance() {// to prevent multiple instances
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        items.add(item);
    } //actually add item to the array

    public double getSubtotal() {//caclulate subtotal
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    } //clear the array if the cart is cleared

    public void removeItem(int position) {//remove the item from the array
        if (position >= 0 && position < items.size()) {
            items.remove(position);
        }
    }
} 