package com.example.gdritzys.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private final MutableLiveData<List<CartItem>> items = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Double> subtotal = new MutableLiveData<>(0.0);
    private final MutableLiveData<Double> tax = new MutableLiveData<>(0.0);
    private final MutableLiveData<Double> total = new MutableLiveData<>(0.0);
    private static final double TAX_RATE = 0.07; // 7% tax rate

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(MenuItem menuItem) {
        List<CartItem> currentItems = items.getValue();
        if (currentItems == null) currentItems = new ArrayList<>();

        // Check if item already exists
        boolean found = false;
        for (CartItem item : currentItems) {
            if (item.getMenuItem().getName().equals(menuItem.getName())) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            currentItems.add(new CartItem(menuItem, 1));
        }

        items.setValue(currentItems);
        updateTotals();
    }

    public void removeItem(CartItem cartItem) {
        List<CartItem> currentItems = items.getValue();
        if (currentItems != null) {
            currentItems.remove(cartItem);
            items.setValue(currentItems);
            updateTotals();
        }
    }

    public void updateQuantity(CartItem cartItem, int quantity) {
        List<CartItem> currentItems = items.getValue();
        if (currentItems != null) {
            if (quantity <= 0) {
                currentItems.remove(cartItem);
            } else {
                cartItem.setQuantity(quantity);
            }
            items.setValue(currentItems);
            updateTotals();
        }
    }

    private void updateTotals() {
        double subtotalValue = 0;
        List<CartItem> currentItems = items.getValue();
        if (currentItems != null) {
            for (CartItem item : currentItems) {
                subtotalValue += item.getSubtotal();
            }
        }
        
        double taxValue = subtotalValue * TAX_RATE;
        double totalValue = subtotalValue + taxValue;

        subtotal.setValue(subtotalValue);
        tax.setValue(taxValue);
        total.setValue(totalValue);
    }

    public void clear() {
        items.setValue(new ArrayList<>());
        updateTotals();
    }

    public LiveData<List<CartItem>> getItems() { return items; }
    public LiveData<Double> getSubtotal() { return subtotal; }
    public LiveData<Double> getTax() { return tax; }
    public LiveData<Double> getTotal() { return total; }
} 