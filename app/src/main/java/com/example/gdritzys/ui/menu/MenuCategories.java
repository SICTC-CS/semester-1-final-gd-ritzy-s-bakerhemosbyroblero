package com.example.gdritzys.ui.menu;

public enum MenuCategories {
    COMBOS("Combos"),
    BURGERS("Burgers"),
    SANDWICHES("Sandwiches"),
    HOT_DOGS("Hot Dogs"),
    SIDES("Sides"),
    CHILI("Chili"),
    ICE_CREAM("Ice Cream"),
    DRINKS("Drinks");

    private final String title;

    MenuCategories(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
} 