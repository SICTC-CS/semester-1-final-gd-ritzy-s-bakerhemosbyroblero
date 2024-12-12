package com.gdritzys.utils;

import com.gdritzys.models.CartItem;
import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<CartItem> cartItems;
    private ArrayList<CartUpdateListener> listeners;

    public interface CartUpdateListener {
        void onCartUpdated();
    }

    private CartManager() {
        cartItems = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
        notifyListeners();
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
        notifyListeners();
    }

    public void clearCart() {
        cartItems.clear();
        notifyListeners();
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void addListener(CartUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(CartUpdateListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (CartUpdateListener listener : listeners) {
            listener.onCartUpdated();
        }
    }
} 