package com.example.gdritzys.managers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.gdritzys.models.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Double> subtotal = new MutableLiveData<>(0.0);
    private final MutableLiveData<Double> tax = new MutableLiveData<>(0.0);
    private final MutableLiveData<Double> total = new MutableLiveData<>(0.0);
    private final MutableLiveData<Integer> itemCount = new MutableLiveData<>(0);
    private static final double TAX_RATE = 0.07;

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        List<CartItem> currentItems = cartItems.getValue();
        if (currentItems == null) currentItems = new ArrayList<>();
        currentItems.add(item);
        cartItems.setValue(currentItems);
        updateTotals();
        updateItemCount();
    }

    public void removeItem(CartItem item) {
        List<CartItem> currentItems = cartItems.getValue();
        if (currentItems != null) {
            currentItems.remove(item);
            cartItems.setValue(currentItems);
            updateTotals();
            updateItemCount();
        }
    }

    public void updateItemQuantity(CartItem item, int quantity) {
        if (quantity <= 0) {
            removeItem(item);
        } else {
            item.setQuantity(quantity);
            cartItems.setValue(cartItems.getValue());
            updateTotals();
            updateItemCount();
        }
    }

    private void updateTotals() {
        double subtotalValue = 0;
        List<CartItem> items = cartItems.getValue();
        if (items != null) {
            for (CartItem item : items) {
                subtotalValue += item.getSubtotal();
            }
        }
        
        double taxValue = subtotalValue * TAX_RATE;
        double totalValue = subtotalValue + taxValue;

        subtotal.setValue(subtotalValue);
        tax.setValue(taxValue);
        total.setValue(totalValue);
    }

    private void updateItemCount() {
        int count = 0;
        List<CartItem> items = cartItems.getValue();
        if (items != null) {
            for (CartItem item : items) {
                count += item.getQuantity();
            }
        }
        itemCount.setValue(count);
    }

    public void clear() {
        cartItems.setValue(new ArrayList<>());
        updateTotals();
        updateItemCount();
    }

    public LiveData<List<CartItem>> getCartItems() { return cartItems; }
    public LiveData<Double> getSubtotal() { return subtotal; }
    public LiveData<Double> getTax() { return tax; }
    public LiveData<Double> getTotal() { return total; }
    public LiveData<Integer> getItemCount() { return itemCount; }
} 