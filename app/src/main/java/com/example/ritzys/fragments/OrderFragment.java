package com.example.ritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.example.ritzys.adapters.CartAdapter;
import com.example.ritzys.models.CartItem;
import com.example.ritzys.models.CartManager;
import com.example.ritzys.models.RewardsManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class OrderFragment extends Fragment {
    private RecyclerView cartRecyclerView;
    private TextView subtotalText, taxText, totalText;
    private MaterialButton applyRewardsButton;
    private MaterialCardView rewardsDiscountCard;
    private CartAdapter cartAdapter;
    private static final double TAX_RATE = 0.07;
    private static final int POINTS_NEEDED = 15;
    private static final double POINTS_DISCOUNT = 1.00;
    private RewardsManager rewardsManager;
    private boolean rewardsApplied = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        
        rewardsManager = RewardsManager.getInstance(requireContext());
        setupViews(view);
        updateTotals();
        
        return view;
    }

    private void setupViews(View view) {//setting up the views
        cartRecyclerView = view.findViewById(R.id.cart_recycler_view);
        subtotalText = view.findViewById(R.id.subtotal_text);
        taxText = view.findViewById(R.id.tax_text);
        totalText = view.findViewById(R.id.total_text);
        applyRewardsButton = view.findViewById(R.id.apply_rewards_button);
        rewardsDiscountCard = view.findViewById(R.id.rewards_discount_card);
        
        cartAdapter = new CartAdapter(CartManager.getInstance().getItems(), this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecyclerView.setAdapter(cartAdapter);

        Button checkoutButton = view.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> processCheckout());

        // Setup rewards button
        updateRewardsButtonState();
        
        applyRewardsButton.setOnClickListener(v -> {
            rewardsApplied = true;
            applyRewardsButton.setVisibility(View.GONE);
            rewardsDiscountCard.setVisibility(View.VISIBLE);
            updateTotals();
        });

        view.findViewById(R.id.remove_rewards_button).setOnClickListener(v -> {
            rewardsApplied = false;
            applyRewardsButton.setVisibility(View.VISIBLE);
            rewardsDiscountCard.setVisibility(View.GONE);
            updateTotals();
        });
    }

    private void updateRewardsButtonState() {
        int currentPoints = rewardsManager.getCurrentPoints();
        boolean hasEnoughPoints = currentPoints >= POINTS_NEEDED;
        boolean hasItemsInCart = CartManager.getInstance().getSubtotal() > 0;
        
        // Only show button if they have enough points AND items in cart
        boolean shouldShowButton = hasEnoughPoints && hasItemsInCart && !rewardsApplied;
        
        applyRewardsButton.setEnabled(hasEnoughPoints && hasItemsInCart);
        applyRewardsButton.setVisibility(shouldShowButton ? View.VISIBLE : View.GONE);
        rewardsDiscountCard.setVisibility(rewardsApplied ? View.VISIBLE : View.GONE);
    }

    private void processCheckout() {
        double subtotal = CartManager.getInstance().getSubtotal();
        
        // Handle rewards points
        if (rewardsApplied) {
            rewardsManager.usePoints(POINTS_NEEDED);
            rewardsApplied = false;
        }
        
        // Calculate and add new rewards points
        int pointsEarned = RewardsManager.calculatePointsForPurchase(subtotal);
        rewardsManager.addPoints(pointsEarned);
        
        // Clear the cart
        CartManager.getInstance().clearCart();
        
        // Update the UI
        cartAdapter.notifyDataSetChanged();
        updateTotals();
        updateRewardsButtonState();
        
        // Show success message
        String message = String.format("Order complete! You earned %d points!", pointsEarned);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void updateTotals() {
        double subtotal = CartManager.getInstance().getSubtotal();
        double discount = rewardsApplied ? POINTS_DISCOUNT : 0.0;
        double discountedSubtotal = subtotal - discount;
        double tax = discountedSubtotal * TAX_RATE;
        double total = discountedSubtotal + tax;

        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
        taxText.setText(String.format("Tax: $%.2f", tax));
        totalText.setText(String.format("Total: $%.2f", total));
        
        // Update rewards button state whenever totals change
        updateRewardsButtonState();
    }
} 