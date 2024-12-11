package com.example.gdritzys.models;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String category;
    private List<ToppingOption> toppings;

    public MenuItem(String name, String description, double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        initializeToppings();
    }

    private void initializeToppings() {
        toppings = new ArrayList<>();
        
        // Handle each menu item type specifically
        if (name.toLowerCase().contains("chili")) {
            if (name.contains("Plain")) {
                // Plain chili - basic options
                toppings.add(new ToppingOption("Cheese", 0.49));
                toppings.add(new ToppingOption("Onions", 0.00));
                toppings.add(new ToppingOption("Crackers", 0.00));
                toppings.add(new ToppingOption("Hot Sauce", 0.00));
            } 
            else if (name.contains("3-Way")) {
                // 3-Way comes with cheese and spaghetti by default
                toppings.add(new ToppingOption("Extra Cheese", 0.49));
                toppings.add(new ToppingOption("Extra Spaghetti", 0.99));
            }
            else if (name.contains("4-Way")) {
                // 4-Way adds onions
                toppings.add(new ToppingOption("Extra Cheese", 0.49));
                toppings.add(new ToppingOption("Extra Spaghetti", 0.99));
                toppings.add(new ToppingOption("Extra Onions", 0.00));
            }
            else if (name.contains("5-Way")) {
                // 5-Way adds beans
                toppings.add(new ToppingOption("Extra Cheese", 0.49));
                toppings.add(new ToppingOption("Extra Spaghetti", 0.99));
                toppings.add(new ToppingOption("Extra Onions", 0.00));
                toppings.add(new ToppingOption("Extra Beans", 0.99));
            }
            else if (name.contains("7-Way")) {
                // 7-Way has everything
                toppings.add(new ToppingOption("Extra Cheese", 0.49));
                toppings.add(new ToppingOption("Extra Spaghetti", 0.99));
                toppings.add(new ToppingOption("Extra Onions", 0.00));
                toppings.add(new ToppingOption("Extra Beans", 0.99));
                toppings.add(new ToppingOption("Extra Green Peppers", 0.00));
                toppings.add(new ToppingOption("Extra Tomatoes", 0.00));
            }
            // Common options for all chili types
            toppings.add(new ToppingOption("Hot Sauce", 0.00));
            toppings.add(new ToppingOption("Crackers", 0.00));
        }
        else if (name.toLowerCase().contains("burger")) {
            addBurgerToppings();
        }
        else if (name.toLowerCase().contains("hot dog")) {
            addHotDogToppings();
        }
        else if (name.toLowerCase().contains("fries")) {
            addFryToppings();
        }
        // ... other specific item types
    }

    private void addBurgerToppings() {
        toppings.add(new ToppingOption("Lettuce", 0.00));
        toppings.add(new ToppingOption("Tomato", 0.00));
        toppings.add(new ToppingOption("Onion", 0.00));
        toppings.add(new ToppingOption("Pickle", 0.00));
        toppings.add(new ToppingOption("Mayo", 0.00));
        toppings.add(new ToppingOption("Mustard", 0.00));
        toppings.add(new ToppingOption("Ketchup", 0.00));
        toppings.add(new ToppingOption("Extra Cheese", 0.49));
        toppings.add(new ToppingOption("Add Bacon", 1.49));
        toppings.add(new ToppingOption("Extra Patty", 2.49));
    }

    private void addHotDogToppings() {
        toppings.add(new ToppingOption("Mustard", 0.00));
        toppings.add(new ToppingOption("Ketchup", 0.00));
        toppings.add(new ToppingOption("Onions", 0.00));
        toppings.add(new ToppingOption("Relish", 0.00));
        toppings.add(new ToppingOption("Add Cheese", 0.49));
        toppings.add(new ToppingOption("Add Chili", 0.99));
        toppings.add(new ToppingOption("Add Sauerkraut", 0.49));
    }

    private void addFryToppings() {
        toppings.add(new ToppingOption("Extra Salt", 0.00));
        toppings.add(new ToppingOption("Extra Pepper", 0.00));
        toppings.add(new ToppingOption("Add Cheese", 0.99));
        toppings.add(new ToppingOption("Add Chili", 1.49));
        toppings.add(new ToppingOption("Add Bacon", 1.49));
        toppings.add(new ToppingOption("Add Ranch", 0.49));
        toppings.add(new ToppingOption("Add Cajun Seasoning", 0.00));
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public List<ToppingOption> getToppings() { return toppings; }

    public double getTotalPrice() {
        double total = price;
        for (ToppingOption topping : toppings) {
            total += topping.getCurrentCost();
        }
        return total;
    }

    public MenuItem createCustomizedCopy() {
        MenuItem copy = new MenuItem(name, description, price, category);
        for (int i = 0; i < toppings.size(); i++) {
            copy.getToppings().get(i).setSelection(
                this.toppings.get(i).getSelection());
        }
        return copy;
    }
} 