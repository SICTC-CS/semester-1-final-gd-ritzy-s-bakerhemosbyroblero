package com.gdritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.gdritzys.adapters.CartAdapter;
import com.gdritzys.models.CartItem;
import com.gdritzys.utils.CartManager;
import java.util.ArrayList;

public class OrderFragment extends Fragment implements CartManager.CartUpdateListener {
    private static final double TAX_RATE = 0.07; // 7% tax rate
    
    private RecyclerView cartRecyclerView;
    private TextView subtotalText;
    private TextView taxText;
    private TextView totalText;
    private Button checkoutButton;
    private View emptyCartView;
    private CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        cartRecyclerView = view.findViewById(R.id.cart_recycler_view);
        subtotalText = view.findViewById(R.id.subtotal_text);
        taxText = view.findViewById(R.id.tax_text);
        totalText = view.findViewById(R.id.total_text);
        checkoutButton = view.findViewById(R.id.checkout_button);
        emptyCartView = view.findViewById(R.id.empty_cart_view);

        setupCartRecyclerView();
        updateCartDisplay();

        CartManager.getInstance().addListener(this);

        checkoutButton.setOnClickListener(v -> {
            // TODO: Implement checkout process
        });

        return view;
    }

    private void setupCartRecyclerView() {
        cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems(), 
            new CartAdapter.CartItemListener() {
                @Override
                public void onQuantityChanged(CartItem item, int newQuantity) {
                    if (newQuantity <= 0) {
                        CartManager.getInstance().removeItem(item);
                    } else {
                        item.setQuantity(newQuantity);
                        CartManager.getInstance().notifyListeners();
                    }
                }
            });
        
        cartRecyclerView.setAdapter(cartAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateCartDisplay() {
        ArrayList<CartItem> items = CartManager.getInstance().getCartItems();
        
        if (items.isEmpty()) {
            emptyCartView.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
            checkoutButton.setEnabled(false);
            return;
        }

        emptyCartView.setVisibility(View.GONE);
        cartRecyclerView.setVisibility(View.VISIBLE);
        checkoutButton.setEnabled(true);

        double subtotal = CartManager.getInstance().getTotal();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
        taxText.setText(String.format("Tax (7%%): $%.2f", tax));
        totalText.setText(String.format("Total: $%.2f", total));
    }

    @Override
    public void onCartUpdated() {
        updateCartDisplay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CartManager.getInstance().removeListener(this);
    }
} 