package com.example.ritzys.models;

import java.util.List;

public class MenuCategory {
    private String title;
    private List<MenuItem> items;
    private boolean isExpanded;

    public MenuCategory(String title, List<MenuItem> items) {
        this.title = title;
        this.items = items;
        this.isExpanded = false;
    }

    public String getTitle() {
        return title;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
} 