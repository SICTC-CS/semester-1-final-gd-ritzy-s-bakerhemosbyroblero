package com.example.gdritzys.models;

public class CustomizationOption {
    private String name;
    private double additionalCost;
    private boolean selected;

    public CustomizationOption(String name, double additionalCost) {
        this.name = name;
        this.additionalCost = additionalCost;
        this.selected = false;
    }

    public String getName() { return name; }
    public double getAdditionalCost() { return additionalCost; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
} 