package com.example.gdritzys.ui.food;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.gdritzys.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends ViewModel {
    private final MutableLiveData<List<MenuItem>> menuItems;

    public FoodViewModel() {
        menuItems = new MutableLiveData<>();
        loadMenuItems();
    }

    private void loadMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        
        // Combos
        items.add(new MenuItem("COMBO #1", "Cheeseburger, Fries and a Medium Drink", 8.99, "Combos"));
        items.add(new MenuItem("COMBO #2", "Grilled Chicken Sandwich, Fries and a Medium Drink", 9.99, "Combos"));
        items.add(new MenuItem("COMBO #3", "Double Ritz w/ Cheese, Fries and a Medium Drink", 10.99, "Combos"));
        items.add(new MenuItem("COMBO #4", "Hot Dog, Fries and a Medium Drink", 7.99, "Combos"));
        items.add(new MenuItem("RITZ COMBO BASKET", "Double Ritz w/ Cheese, Fries, Cole Slaw and a Medium Drink", 11.99, "Combos"));
        items.add(new MenuItem("COOL DEAL", "Hamburger, Fries, Medium Drink and a Scoop of Ice Cream", 10.99, "Combos"));
        items.add(new MenuItem("ITZY RITZY FOR KIDS", "Choice of a Hamburger, Cheeseburger, Grilled Cheese or Hot Dog, Small Fry, Small Drink and a Junior Scoop of Ice Cream", 7.99, "Combos"));

        // Sandwiches
        items.add(new MenuItem("Hamburger", "Lean ground beef thin seared and shaped on the grill", 4.99, "Sandwiches"));
        items.add(new MenuItem("Cheeseburger", "Our classic hamburger with cheese", 5.49, "Sandwiches"));
        items.add(new MenuItem("Double Ritz", "Twice the amount of beef with hand-shaped and seared patties", 7.99, "Sandwiches"));
        items.add(new MenuItem("Fish Sandwich", "Deep fried white fish, breaded and cooked to perfection", 5.99, "Sandwiches"));
        items.add(new MenuItem("Chicken Grill", "Real chicken breast, marinated and grilled, served on wheat bun", 6.99, "Sandwiches"));
        items.add(new MenuItem("World's Best PB&J", "Creamy peanut butter, strawberry jelly, peanuts and fresh strawberries", 5.99, "Sandwiches"));

        // Hot Dogs
        items.add(new MenuItem("All-American Hot Dog", "100% beef and grilled to perfection", 3.99, "Hot Dogs"));
        items.add(new MenuItem("Coney Dog", "Topped with special fresh chili coney sauce", 4.99, "Hot Dogs"));
        items.add(new MenuItem("Coney Dog w/ Cheese", "Coney Dog topped with cheese", 5.49, "Hot Dogs"));

        // Chili
        items.add(new MenuItem("Plain Chili", "Basic Ritzy's Chili", 4.99, "Chili"));
        items.add(new MenuItem("3-Way Chili", "Chili, Cheese, and Spaghetti", 6.99, "Chili"));
        items.add(new MenuItem("4-Way Chili", "Chili, Cheese, Spaghetti, and Onions", 7.49, "Chili"));
        items.add(new MenuItem("5-Way Chili", "Chili, Cheese, Spaghetti, Onions, and Beans", 7.99, "Chili"));
        items.add(new MenuItem("7-Way Chili", "Chili, Cheese, Spaghetti, Onions, Beans, Green Peppers, and Tomatoes", 8.99, "Chili"));

        menuItems.setValue(items);
    }

    public LiveData<List<MenuItem>> getMenuItems() {
        return menuItems;
    }
} 