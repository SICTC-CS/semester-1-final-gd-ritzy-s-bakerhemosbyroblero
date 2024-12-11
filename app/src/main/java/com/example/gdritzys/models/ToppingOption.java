package com.example.gdritzys.models;

public class ToppingOption {
    public static final int NONE = 0;
    public static final int NORMAL = 1;
    public static final int EXTRA = 2;

    private String name;
    private int selection;
    private double extraCost;

    public ToppingOption(String name, double extraCost) {
        this.name = name;
        this.selection = NORMAL;
        this.extraCost = extraCost;
    }

    public String getName() { return name; }
    public int getSelection() { return selection; }
    public void setSelection(int selection) { this.selection = selection; }
    public double getExtraCost() { return extraCost; }
    
    public double getCurrentCost() {
        return selection == EXTRA ? extraCost : 0.0;
    }
} 