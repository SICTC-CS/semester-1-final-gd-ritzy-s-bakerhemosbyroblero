package com.example.gdritzys.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.gdritzys.models.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuViewModel extends ViewModel {
    private final MutableLiveData<List<MenuItem>> allMenuItems;
    private final MutableLiveData<MenuItem> selectedItem = new MutableLiveData<>();

    public MenuViewModel() {
        allMenuItems = new MutableLiveData<>(loadMenuItems());
    }

    public LiveData<List<MenuItem>> getMenuItemsForCategory(MenuCategories category) {
        return new MutableLiveData<>(
            allMenuItems.getValue().stream()
                .filter(item -> item.getCategory().equals(category.name()))
                .collect(Collectors.toList())
        );
    }

    public void showCustomizationDialog(MenuItem item) {
        selectedItem.setValue(item);
    }

    public LiveData<MenuItem> getSelectedItem() {
        return selectedItem;
    }

    private List<MenuItem> loadMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        
        // COMBOS
        items.add(new MenuItem("COMBO #1", "Cheeseburger, Fries and a Medium Drink", 8.99, "COMBOS"));
        items.add(new MenuItem("COMBO #2", "Grilled Chicken Sandwich, Fries and a Medium Drink", 9.99, "COMBOS"));
        items.add(new MenuItem("COMBO #3", "Double Ritz w/ Cheese, Fries and a Medium Drink", 10.99, "COMBOS"));
        items.add(new MenuItem("COMBO #4", "Hot Dog, Fries and a Medium Drink", 7.99, "COMBOS"));
        items.add(new MenuItem("RITZ COMBO BASKET", "Double Ritz w/ Cheese, Fries, Cole Slaw and a Medium Drink", 11.99, "COMBOS"));
        items.add(new MenuItem("COOL DEAL", "Hamburger, Fries, Medium Drink and a Scoop of Ice Cream", 10.99, "COMBOS"));
        items.add(new MenuItem("ITZY RITZY FOR KIDS", "Choice of a Hamburger, Cheeseburger, Grilled Cheese or Hot Dog, Small Fry, Small Drink and a Junior Scoop of Ice Cream", 7.99, "COMBOS"));

        // BURGERS
        items.add(new MenuItem("HAMBURGER", "Fresh ground beef patty with lettuce, tomato, onion, pickle", 4.99, "BURGERS"));
        items.add(new MenuItem("CHEESEBURGER", "Fresh ground beef patty with American cheese, lettuce, tomato, onion, pickle", 5.49, "BURGERS"));
        items.add(new MenuItem("DOUBLE RITZ", "Two beef patties with lettuce, tomato, onion, pickle", 6.99, "BURGERS"));
        items.add(new MenuItem("DOUBLE RITZ W/ CHEESE", "Two beef patties with American cheese, lettuce, tomato, onion, pickle", 7.49, "BURGERS"));
        items.add(new MenuItem("BACON CHEESEBURGER", "Fresh ground beef patty with bacon, American cheese, lettuce, tomato, onion, pickle", 6.49, "BURGERS"));
        items.add(new MenuItem("DOUBLE BACON CHEESEBURGER", "Two beef patties with bacon, American cheese, lettuce, tomato, onion, pickle", 8.49, "BURGERS"));

        // SANDWICHES
        items.add(new MenuItem("GRILLED CHICKEN", "Grilled chicken breast with lettuce, tomato", 5.99, "SANDWICHES"));
        items.add(new MenuItem("FISH SANDWICH", "Hand-breaded cod fillet with lettuce, tartar sauce", 5.99, "SANDWICHES"));
        items.add(new MenuItem("GRILLED CHEESE", "Melted American cheese on grilled Texas toast", 3.99, "SANDWICHES"));
        items.add(new MenuItem("BLT", "Bacon, lettuce, tomato on Texas toast", 4.99, "SANDWICHES"));

        // HOT DOGS
        items.add(new MenuItem("HOT DOG", "All-beef hot dog on a steamed bun", 3.99, "HOT_DOGS"));
        items.add(new MenuItem("CHILI DOG", "Hot dog topped with our homemade chili", 4.99, "HOT_DOGS"));
        items.add(new MenuItem("CHILI CHEESE DOG", "Hot dog topped with chili and melted cheese", 5.49, "HOT_DOGS"));
        items.add(new MenuItem("SLAW DOG", "Hot dog topped with cole slaw", 4.49, "HOT_DOGS"));
        items.add(new MenuItem("KRAUT DOG", "Hot dog topped with sauerkraut", 4.49, "HOT_DOGS"));

        // SIDES
        items.add(new MenuItem("FRENCH FRIES", "Crispy golden fries", 2.99, "SIDES"));
        items.add(new MenuItem("CHILI CHEESE FRIES", "Fries topped with chili and melted cheese", 4.99, "SIDES"));
        items.add(new MenuItem("COLE SLAW", "Fresh homemade cole slaw", 2.49, "SIDES"));
        items.add(new MenuItem("ONION RINGS", "Crispy battered onion rings", 3.99, "SIDES"));
        items.add(new MenuItem("SIDE SALAD", "Fresh garden salad with choice of dressing", 3.49, "SIDES"));

        // CHILI
        items.add(new MenuItem("PLAIN CHILI", "Our signature homemade chili", 4.99, "CHILI"));
        items.add(new MenuItem("3-WAY CHILI", "Chili, Cheese, and Spaghetti", 6.99, "CHILI"));
        items.add(new MenuItem("4-WAY CHILI", "Chili, Cheese, Spaghetti, and Onions", 7.49, "CHILI"));
        items.add(new MenuItem("5-WAY CHILI", "Chili, Cheese, Spaghetti, Onions, and Beans", 7.99, "CHILI"));
        items.add(new MenuItem("7-WAY CHILI", "Chili, Cheese, Spaghetti, Onions, Beans, Green Peppers, and Tomatoes", 8.99, "CHILI"));

        // ICE CREAM
        items.add(new MenuItem("VANILLA CONE", "Soft-serve vanilla ice cream cone", 2.99, "ICE_CREAM"));
        items.add(new MenuItem("CHOCOLATE CONE", "Soft-serve chocolate ice cream cone", 2.99, "ICE_CREAM"));
        items.add(new MenuItem("TWIST CONE", "Vanilla and chocolate twist cone", 2.99, "ICE_CREAM"));
        items.add(new MenuItem("SUNDAE", "Choice of topping with whipped cream and cherry", 4.49, "ICE_CREAM"));
        items.add(new MenuItem("BANANA SPLIT", "Three scoops with banana, toppings, whipped cream, and cherry", 6.99, "ICE_CREAM"));
        items.add(new MenuItem("MILKSHAKE", "Hand-spun shake in vanilla, chocolate, or strawberry", 4.99, "ICE_CREAM"));
        items.add(new MenuItem("MALT", "Classic malt in vanilla, chocolate, or strawberry", 5.29, "ICE_CREAM"));
        items.add(new MenuItem("ROOT BEER FLOAT", "Vanilla ice cream in root beer", 4.49, "ICE_CREAM"));

        // DRINKS
        items.add(new MenuItem("FOUNTAIN DRINK", "Coca-Cola products", 2.49, "DRINKS"));
        items.add(new MenuItem("ICED TEA", "Fresh-brewed sweet or unsweet tea", 2.49, "DRINKS"));
        items.add(new MenuItem("LEMONADE", "Fresh-squeezed lemonade", 2.99, "DRINKS"));
        items.add(new MenuItem("COFFEE", "Fresh-brewed hot coffee", 1.99, "DRINKS"));
        items.add(new MenuItem("HOT CHOCOLATE", "Rich hot chocolate with whipped cream", 2.49, "DRINKS"));
        items.add(new MenuItem("BOTTLED WATER", "Pure spring water", 1.99, "DRINKS"));
        
        return items;
    }
} 