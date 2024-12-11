package com.example.gdritzys.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RewardsManager {
    private static RewardsManager instance;
    private final MutableLiveData<Integer> points = new MutableLiveData<>(0);
    private static final int POINTS_PER_DOLLAR = 1;
    private static final int POINTS_FOR_REWARD = 100;
    private static final double REWARD_VALUE = 5.00;

    private RewardsManager() {}

    public static RewardsManager getInstance() {
        if (instance == null) {
            instance = new RewardsManager();
        }
        return instance;
    }

    public void addPoints(double purchaseAmount) {
        Integer currentPoints = points.getValue();
        if (currentPoints == null) currentPoints = 0;
        currentPoints += (int)(purchaseAmount * POINTS_PER_DOLLAR);
        points.setValue(currentPoints);
    }

    public boolean canRedeemReward() {
        Integer currentPoints = points.getValue();
        return currentPoints != null && currentPoints >= POINTS_FOR_REWARD;
    }

    public double redeemReward() {
        if (canRedeemReward()) {
            Integer currentPoints = points.getValue();
            currentPoints -= POINTS_FOR_REWARD;
            points.setValue(currentPoints);
            return REWARD_VALUE;
        }
        return 0;
    }

    public LiveData<Integer> getPoints() { return points; }
    public static int getPointsForReward() { return POINTS_FOR_REWARD; }
    public static double getRewardValue() { return REWARD_VALUE; }
} 