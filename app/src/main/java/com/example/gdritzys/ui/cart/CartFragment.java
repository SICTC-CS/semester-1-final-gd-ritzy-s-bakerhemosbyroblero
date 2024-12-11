package com.example.gdritzys.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gdritzys.adapters.CartAdapter;
import com.example.gdritzys.databinding.FragmentCartBinding;
import com.google.android.material.snackbar.Snackbar;
import java.util.Locale;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;
    private CartAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerView();
        setupObservers();
        setupButtons();

        return root;
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter();
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRecyclerView.setAdapter(adapter);
    }

    private void setupObservers() {
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), items -> {
            adapter.submitList(items);
            binding.checkoutButton.setEnabled(!items.isEmpty());
        });

        cartViewModel.getSubtotal().observe(getViewLifecycleOwner(), subtotal -> 
            binding.subtotalText.setText(String.format(Locale.US, "$%.2f", subtotal)));

        cartViewModel.getTax().observe(getViewLifecycleOwner(), tax -> 
            binding.taxText.setText(String.format(Locale.US, "$%.2f", tax)));

        cartViewModel.getFinalTotal().observe(getViewLifecycleOwner(), total -> 
            binding.totalText.setText(String.format(Locale.US, "$%.2f", total)));

        cartViewModel.getRewardsPoints().observe(getViewLifecycleOwner(), points -> {
            boolean canRedeem = points >= 100;
            binding.applyRewardButton.setEnabled(canRedeem);
            binding.applyRewardButton.setText(String.format("Apply $5 Reward (%d/100 points)", points));
        });

        cartViewModel.isRewardApplied().observe(getViewLifecycleOwner(), isApplied -> {
            if (isApplied) {
                binding.applyRewardButton.setText("Remove $5 Reward");
            }
        });
    }

    private void setupButtons() {
        binding.checkoutButton.setOnClickListener(v -> {
            cartViewModel.checkout();
            Snackbar.make(v, "Order placed successfully!", Snackbar.LENGTH_LONG).show();
        });

        binding.applyRewardButton.setOnClickListener(v -> {
            cartViewModel.toggleReward();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 