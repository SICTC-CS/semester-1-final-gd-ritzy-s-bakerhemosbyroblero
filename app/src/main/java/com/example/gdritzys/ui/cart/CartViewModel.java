package com.example.gdritzys.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.gdritzys.models.Cart;
import com.example.gdritzys.models.CartItem;
import com.example.gdritzys.models.RewardsManager;
import java.util.List;

public class CartViewModel extends ViewModel {
    private final Cart cart;
    private final RewardsManager rewardsManager;
    private final MutableLiveData<Boolean> rewardApplied = new MutableLiveData<>(false);
    private final MutableLiveData<Double> finalTotal = new MutableLiveData<>(0.0);

    public CartViewModel() {
        cart = Cart.getInstance();
        rewardsManager = RewardsManager.getInstance();
        updateFinalTotal();

        // Observe cart total changes
        cart.getTotal().observeForever(total -> updateFinalTotal());
    }

    public void toggleReward() {
        Boolean currentlyApplied = rewardApplied.getValue();
        if (currentlyApplied != null && !currentlyApplied && rewardsManager.canRedeemReward()) {
            rewardApplied.setValue(true);
        } else {
            rewardApplied.setValue(false);
        }
        updateFinalTotal();
    }

    private void updateFinalTotal() {
        Double cartTotal = cart.getTotal().getValue();
        if (cartTotal == null) cartTotal = 0.0;

        Boolean isRewardApplied = rewardApplied.getValue();
        if (isRewardApplied != null && isRewardApplied) {
            cartTotal -= RewardsManager.getRewardValue();
            if (cartTotal < 0) cartTotal = 0.0;
        }

        finalTotal.setValue(cartTotal);
    }

    public void checkout() {
        Double total = finalTotal.getValue();
        if (total != null && total > 0) {
            // Add points for purchase
            rewardsManager.addPoints(total);
            
            // Apply reward if used
            Boolean isRewardApplied = rewardApplied.getValue();
            if (isRewardApplied != null && isRewardApplied) {
                rewardsManager.redeemReward();
            }

            // Clear cart
            cart.clear();
            rewardApplied.setValue(false);
        }
    }

    public LiveData<List<CartItem>> getCartItems() { return cart.getItems(); }
    public LiveData<Double> getSubtotal() { return cart.getSubtotal(); }
    public LiveData<Double> getTax() { return cart.getTax(); }
    public LiveData<Double> getCartTotal() { return cart.getTotal(); }
    public LiveData<Double> getFinalTotal() { return finalTotal; }
    public LiveData<Boolean> isRewardApplied() { return rewardApplied; }
    public LiveData<Integer> getRewardsPoints() { return rewardsManager.getPoints(); }
} 