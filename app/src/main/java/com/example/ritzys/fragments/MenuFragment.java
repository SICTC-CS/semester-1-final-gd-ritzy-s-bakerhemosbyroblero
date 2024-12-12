package com.example.ritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.example.ritzys.adapters.MenuAdapter;
import com.example.ritzys.models.MenuItem;
import java.util.ArrayList;
import java.util.List;
import com.example.ritzys.dialogs.CustomizationDialog;
import com.example.ritzys.models.MenuCategory;

public class MenuFragment extends Fragment {
    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view);
        setupMenu();
        
        return view;
    }

    private void setupMenu() {
        List<MenuCategory> categories = new ArrayList<>();
        
        // Group items by category
        List<MenuItem> combos = new ArrayList<>();
        List<MenuItem> sandwiches = new ArrayList<>();
        List<MenuItem> sides = new ArrayList<>();
        List<MenuItem> shakes = new ArrayList<>();
        List<MenuItem> desserts = new ArrayList<>();
        List<MenuItem> drinks = new ArrayList<>();
        List<MenuItem> kids = new ArrayList<>();

        // Combos
        combos.add(new MenuItem("COMBO #1", "Cheeseburger, Fries and a Medium Drink", 8.99, "combos"));
        combos.add(new MenuItem("COMBO #2", "Grilled Chicken Sandwich, Fries and a Medium Drink", 9.99, "combos"));
        combos.add(new MenuItem("COMBO #3", "Double Ritz w/ Cheese, Fries and a Medium Drink", 10.99, "combos"));
        combos.add(new MenuItem("COMBO #4", "Fish Sandwich, Fries and a Medium Drink", 9.99, "combos"));
        combos.add(new MenuItem("COMBO #5", "World's Best PB&J, Fries and a Medium Drink", 9.99, "combos"));

        // Sandwiches
        sandwiches.add(new MenuItem("Hamburger", "Fresh lean ground beef seared to perfection", 4.99, "sandwiches"));
        sandwiches.add(new MenuItem("Cheeseburger", "Our classic hamburger topped with American cheese", 5.49, "sandwiches"));
        sandwiches.add(new MenuItem("Double Ritz", "Two beef patties with cheese", 7.49, "sandwiches"));
        sandwiches.add(new MenuItem("Triple Ritz", "Three beef patties with cheese", 8.99, "sandwiches"));
        sandwiches.add(new MenuItem("Fish Sandwich", "Hand-breaded white fish fillet with tartar sauce", 6.49, "sandwiches"));
        sandwiches.add(new MenuItem("Grilled Chicken", "Marinated chicken breast with lettuce and mayo", 6.99, "sandwiches"));
        sandwiches.add(new MenuItem("World's Best PB&J", "Two giant pieces of thick bread, creamy peanut butter, strawberry jelly, peanuts and fresh strawberries", 6.99, "sandwiches"));

        // Sides
        sides.add(new MenuItem("World Famous Shoestring Fries", "Crispy thin-cut fries", 2.99, "sides"));
        sides.add(new MenuItem("Large Fries", "Bigger portion of our famous fries", 3.99, "sides"));
        sides.add(new MenuItem("Onion Rings", "Crispy breaded onion rings", 3.99, "sides"));
        sides.add(new MenuItem("Side Salad", "Fresh garden salad with choice of dressing", 3.49, "sides"));

        // Shakes
        shakes.add(new MenuItem("Vanilla Shake", "Rich and creamy vanilla shake", 4.99, "shakes"));
        shakes.add(new MenuItem("Chocolate Shake", "Rich and creamy chocolate shake", 4.99, "shakes"));
        shakes.add(new MenuItem("Strawberry Shake", "Rich and creamy strawberry shake", 4.99, "shakes"));
        shakes.add(new MenuItem("Oreo Shake", "Vanilla shake blended with Oreo cookies", 5.49, "shakes"));
        shakes.add(new MenuItem("Banana Shake", "Rich and creamy banana shake", 4.99, "shakes"));

        // Desserts
        desserts.add(new MenuItem("Hot Fudge Sundae", "Vanilla ice cream topped with hot fudge", 4.49, "desserts"));
        desserts.add(new MenuItem("Strawberry Sundae", "Vanilla ice cream topped with strawberry sauce", 4.49, "desserts"));
        desserts.add(new MenuItem("Caramel Sundae", "Vanilla ice cream topped with caramel sauce", 4.49, "desserts"));

        // Drinks
        drinks.add(new MenuItem("Fountain Drink Small", "16oz fountain drink", 1.99, "drinks"));
        drinks.add(new MenuItem("Fountain Drink Medium", "20oz fountain drink", 2.29, "drinks"));
        drinks.add(new MenuItem("Fountain Drink Large", "32oz fountain drink", 2.69, "drinks"));
        drinks.add(new MenuItem("Fresh Brewed Tea", "Sweet or unsweet tea", 2.29, "drinks"));
        drinks.add(new MenuItem("Coffee", "Fresh brewed coffee", 1.99, "drinks"));
        drinks.add(new MenuItem("Bottled Water", "16.9oz bottled water", 1.99, "drinks"));

        // Kids Meals
        kids.add(new MenuItem("Kids Hamburger Meal", "Kid-sized hamburger, small fries, small drink", 5.99, "kids"));
        kids.add(new MenuItem("Kids Cheeseburger Meal", "Kid-sized cheeseburger, small fries, small drink", 6.49, "kids"));
        kids.add(new MenuItem("Kids PB&J Meal", "Kid-sized PB&J sandwich, small fries, small drink", 5.99, "kids"));
        kids.add(new MenuItem("Kids Grilled Cheese Meal", "Grilled cheese sandwich, small fries, small drink", 5.99, "kids"));

        // Create categories
        categories.add(new MenuCategory("Combos", combos));
        categories.add(new MenuCategory("Sandwiches", sandwiches));
        categories.add(new MenuCategory("Sides", sides));
        categories.add(new MenuCategory("Shakes", shakes));
        categories.add(new MenuCategory("Desserts", desserts));
        categories.add(new MenuCategory("Drinks", drinks));
        categories.add(new MenuCategory("Kids Meals", kids));
        
        menuAdapter = new MenuAdapter(categories, item -> showCustomizationDialog(item));
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        menuRecyclerView.setAdapter(menuAdapter);
    }

    private void showCustomizationDialog(MenuItem item) {
        // Show customization dialog based on item type
        CustomizationDialog dialog = new CustomizationDialog(getContext(), item);
        dialog.show();
    }
} 