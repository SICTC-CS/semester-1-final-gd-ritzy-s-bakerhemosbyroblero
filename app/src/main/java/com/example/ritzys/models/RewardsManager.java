package com.example.ritzys.models;

import android.content.Context;
import android.content.SharedPreferences;

public class RewardsManager {
    private static RewardsManager instance;
    private static final String PREFS_NAME = "RitzysRewards";
    private static final String KEY_POINTS = "points";
    private SharedPreferences prefs;
    
    private RewardsManager(Context context) {//allows for manipulation of the rewards data
        prefs = context.getApplicationContext()
                      .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    public static synchronized RewardsManager getInstance(Context context) { //prevents mutiple instances
        if (instance == null) {
            instance = new RewardsManager(context);
        }
        return instance;
    }
    
    public int getCurrentPoints() {
        return prefs.getInt(KEY_POINTS, 0);
    }//get current points
    
    public void addPoints(int points) { //adding points
        int currentPoints = getCurrentPoints();
        prefs.edit().putInt(KEY_POINTS, currentPoints + points).apply();
    }
    
    public boolean usePoints(int points) {//using points
        int currentPoints = getCurrentPoints();
        if (currentPoints >= points) {
            prefs.edit().putInt(KEY_POINTS, currentPoints - points).apply();
            return true;
        }
        return false;
    }
    
    public static int calculatePointsForPurchase(double amount) {//calculate points
        // $1 = 1 point
        return (int) amount;
    }
} 