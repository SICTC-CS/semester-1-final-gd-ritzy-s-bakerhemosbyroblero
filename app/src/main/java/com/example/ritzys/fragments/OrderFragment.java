package com.example.ritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.example.ritzys.adapters.CartAdapter;
import com.example.ritzys.models.CartItem;
import com.example.ritzys.models.CartManager;

public class OrderFragment extends Fragment {
    private RecyclerView cartRecyclerView;
    private TextView subtotalText, taxText, totalText;
    private CartAdapter cartAdapter;
    private static final double TAX_RATE = 0.07;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        
        setupViews(view);
        updateTotals();
        
        return view;
    }

    private void setupViews(View view) {
        cartRecyclerView = view.findViewById(R.id.cart_recycler_view);
        subtotalText = view.findViewById(R.id.subtotal_text);
        taxText = view.findViewById(R.id.tax_text);
        totalText = view.findViewById(R.id.total_text);
        
        cartAdapter = new CartAdapter(CartManager.getInstance().getItems());
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecyclerView.setAdapter(cartAdapter);
    }

    private void updateTotals() {
        double subtotal = CartManager.getInstance().getSubtotal();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
        taxText.setText(String.format("Tax: $%.2f", tax));
        totalText.setText(String.format("Total: $%.2f", total));
    }
} 