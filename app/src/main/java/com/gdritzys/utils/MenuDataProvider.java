package com.gdritzys.utils;

import com.gdritzys.models.MenuItem;
import java.util.ArrayList;

public class MenuDataProvider {
    public static ArrayList<MenuItem> getMenuItemsByCategory(String category) {
        ArrayList<MenuItem> items = new ArrayList<>();
        
        switch (category) {
            case "Combos":
                items.add(new MenuItem(
                    "combo_1",
                    "COMBO #1",
                    "Cheeseburger, Fries and a Medium Drink",
                    8.99,
                    "Combos",
                    getComboCustomizations(),
                    "combo_1"
                ));
                items.add(new MenuItem(
                    "combo_2",
                    "COMBO #2",
                    "Grilled Chicken Sandwich, Fries and a Medium Drink",
                    9.99,
                    "Combos",
                    getComboCustomizations(),
                    "combo_2"
                ));
                items.add(new MenuItem(
                    "combo_3",
                    "COMBO #3",
                    "Double Ritz w/ Cheese, Fries and a Medium Drink",
                    10.99,
                    "Combos",
                    getComboCustomizations(),
                    "combo_3"
                ));
                items.add(new MenuItem(
                    "combo_4",
                    "COMBO #4",
                    "Hot Dog, Fries and a Medium Drink",
                    7.99,
                    "Combos",
                    getComboCustomizations(),
                    "combo_4"
                ));
                items.add(new MenuItem(
                    "combo_5",
                    "RITZ COMBO BASKET",
                    "Double Ritz w/ Cheese, Fries, Cole Slaw and a Medium Drink",
                    11.99,
                    "Combos",
                    getComboCustomizations(),
                    "combo_5"
                ));
                items.add(new MenuItem(
                    "combo_6",
                    "COOL DEAL",
                    "Hamburger, Fries, Medium Drink and a Scoop of Ice Cream",
                    9.99,
                    "Combos",
                    getComboCustomizations(),
                    "combo_6"
                ));
                break;

            case "Sandwiches":
                items.add(new MenuItem(
                    "sand_1",
                    "Hamburger",
                    "We use lean, freshly ground beef and \"sear\" our hamburgers -- a special technique where we press each burger flat on a very hot grill to seal in natural beef flavor and juices.",
                    4.99,
                    "Sandwiches",
                    getSandwichCustomizations(),
                    "sand_burger"
                ));
                items.add(new MenuItem(
                    "sand_2",
                    "Cheeseburger",
                    "Our classic hamburger topped with American cheese",
                    5.49,
                    "Sandwiches",
                    getSandwichCustomizations(),
                    "sand_cheese"
                ));
                items.add(new MenuItem(
                    "sand_3",
                    "Double Ritz",
                    "Enjoy twice the amount of beef with the same hand-shaped and seared patties as our Single Ritz.",
                    6.99,
                    "Sandwiches",
                    getSandwichCustomizations(),
                    "sand_double"
                ));
                items.add(new MenuItem(
                    "sand_4",
                    "Double Ritz w/ Cheese",
                    "Our Double Ritz topped with American cheese",
                    7.49,
                    "Sandwiches",
                    getSandwichCustomizations(),
                    "sand_double_cheese"
                ));
                items.add(new MenuItem(
                    "sand_5",
                    "Fish Sandwich",
                    "Delicious deep fried white fish, breaded and cooked to perfection on a toasted bun.",
                    5.99,
                    "Sandwiches",
                    getSandwichCustomizations(),
                    "sand_fish"
                ));
                items.add(new MenuItem(
                    "sand_6",
                    "Chicken Grill",
                    "Our Chicken Grill is a real chicken breast left unbreaded, marinated and grilled to a light golden brown and served on a wheat bun.",
                    6.49,
                    "Sandwiches",
                    getSandwichCustomizations(),
                    "sand_chicken"
                ));
                break;

            case "Hot Dogs":
                items.add(new MenuItem(
                    "dog_1",
                    "All-American Hot Dog",
                    "It's 100% beef and grilled to perfection for a nice, tender and juicy bite",
                    3.99,
                    "Hot Dogs",
                    getHotDogCustomizations(),
                    "dog_american"
                ));
                items.add(new MenuItem(
                    "dog_2",
                    "Coney Dog",
                    "These tender hot dogs are just like our All-Americans, except they are topped with our special, fresh chili coney sauce.",
                    4.49,
                    "Hot Dogs",
                    getHotDogCustomizations(),
                    "dog_coney"
                ));
                break;

            case "Chili":
                items.add(new MenuItem(
                    "chili_1",
                    "Plain Chili",
                    "Basic Ritzy's Chili",
                    4.99,
                    "Chili",
                    getChiliCustomizations(),
                    "chili_plain"
                ));
                items.add(new MenuItem(
                    "chili_2",
                    "3-Way Chili",
                    "Includes Chili, Cheese, and Spaghetti",
                    6.49,
                    "Chili",
                    getChiliCustomizations(),
                    "chili_3way"
                ));
                items.add(new MenuItem(
                    "chili_3",
                    "5-Way Chili",
                    "Includes Chili, Cheese, Spaghetti, Onions, and Beans",
                    7.49,
                    "Chili",
                    getChiliCustomizations(),
                    "chili_5way"
                ));
                items.add(new MenuItem(
                    "chili_4",
                    "7-Way Chili",
                    "Includes Chili, Cheese, Spaghetti, Onions, Beans, Green Peppers, and Tomatoes",
                    8.49,
                    "Chili",
                    getChiliCustomizations(),
                    "chili_7way"
                ));
                break;
        }
        
        return items;
    }

    private static ArrayList<String> getSandwichCustomizations() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Mayonnaise");
        options.add("Ketchup");
        options.add("Pickles");
        options.add("Onions");
        options.add("Lettuce");
        options.add("Mustard");
        options.add("Cheese (Extra)");
        options.add("Tomatoes (Extra)");
        options.add("Bacon (Extra)");
        return options;
    }

    private static ArrayList<String> getHotDogCustomizations() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Ketchup");
        options.add("Mustard");
        options.add("Relish");
        options.add("Onions");
        options.add("Sauerkraut (Extra)");
        options.add("Chili (Extra)");
        options.add("Cheese (Extra)");
        return options;
    }

    private static ArrayList<String> getChiliCustomizations() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Fresh-Grated Cheddar Cheese");
        options.add("Onions");
        options.add("Beans");
        options.add("Tomatoes");
        options.add("Green Peppers");
        options.add("Cincinnati Sauce");
        options.add("Texas Hot Sauce");
        return options;
    }

    private static ArrayList<String> getComboCustomizations() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Upgrade to Basket Combo (includes coleslaw)");
        return options;
    }
} 