package com.example.gdritzys.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdritzys.R;
import com.example.gdritzys.adapters.CustomizationAdapter;
import com.example.gdritzys.adapters.MenuAdapter;
import com.example.gdritzys.adapters.ToppingsAdapter;
import com.example.gdritzys.databinding.FragmentFoodBinding;
import com.example.gdritzys.models.MenuItem;
import com.example.gdritzys.models.Cart;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class FoodFragment extends Fragment implements MenuAdapter.OnItemClickListener {

    private FragmentFoodBinding binding;
    private MenuAdapter adapter;
    private FoodViewModel foodViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.menuRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MenuAdapter(this);
        recyclerView.setAdapter(adapter);

        foodViewModel.getMenuItems().observe(getViewLifecycleOwner(), menuItems -> {
            adapter.updateItems(menuItems);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAddToCartClick(MenuItem item) {
        showCustomizationDialog(item);
    }

    private void showCustomizationDialog(MenuItem menuItem) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_customize_item, null);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(requireContext());
        
        TextView itemNameText = dialogView.findViewById(R.id.item_name);
        TextView itemDescText = dialogView.findViewById(R.id.item_description);
        TextView totalPriceText = dialogView.findViewById(R.id.total_price);
        RecyclerView toppingsRecyclerView = dialogView.findViewById(R.id.toppings_recycler_view);
        
        itemNameText.setText(menuItem.getName());
        itemDescText.setText(menuItem.getDescription());
        updateTotalPrice(menuItem, totalPriceText);
        
        // Set up toppings recycler view
        toppingsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ToppingsAdapter adapter = new ToppingsAdapter(
            menuItem.getToppings(),
            (topping, selection) -> updateTotalPrice(menuItem, totalPriceText)
        );
        toppingsRecyclerView.setAdapter(adapter);
        
        builder.setView(dialogView)
               .setPositiveButton("Add to Cart", (dialog, which) -> {
                   MenuItem customizedItem = menuItem.createCustomizedCopy();
                   Cart.getInstance().addItem(customizedItem);
                   Toast.makeText(requireContext(), 
                       "Added " + menuItem.getName() + " to cart", 
                       Toast.LENGTH_SHORT).show();
               })
               .setNegativeButton("Cancel", null)
               .show();
    }

    private void updateTotalPrice(MenuItem item, TextView priceText) {
        priceText.setText(String.format(Locale.US, "Total: $%.2f", item.getTotalPrice()));
    }
} 