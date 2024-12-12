package com.gdritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.ritzys.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.gdritzys.models.MenuItem;
import com.gdritzys.models.CartItem;
import com.gdritzys.utils.CartManager;
import java.util.ArrayList;

public class ItemDetailFragment extends BottomSheetDialogFragment {
    private MenuItem menuItem;
    private ArrayList<String> selectedCustomizations = new ArrayList<>();
    private int quantity = 1;
    private TextView quantityText;
    private TextView totalPrice;

    public static ItemDetailFragment newInstance(MenuItem item) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putString("itemId", item.getId());
        args.putString("itemName", item.getName());
        args.putString("itemDescription", item.getDescription());
        args.putDouble("itemPrice", item.getPrice());
        fragment.setArguments(args);
        fragment.menuItem = item;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Set up item details
        TextView nameText = view.findViewById(R.id.item_name);
        TextView descriptionText = view.findViewById(R.id.item_description);
        TextView priceText = view.findViewById(R.id.item_price);
        ImageView itemImage = view.findViewById(R.id.item_image);
        LinearLayout customizationsContainer = view.findViewById(R.id.customizations_container);
        Button addToCartButton = view.findViewById(R.id.add_to_cart_button);
        
        // Quantity controls
        ImageButton decreaseBtn = view.findViewById(R.id.decrease_quantity);
        ImageButton increaseBtn = view.findViewById(R.id.increase_quantity);
        quantityText = view.findViewById(R.id.quantity_text);
        totalPrice = view.findViewById(R.id.total_price);

        nameText.setText(menuItem.getName());
        descriptionText.setText(menuItem.getDescription());
        priceText.setText(String.format("$%.2f", menuItem.getPrice()));
        updateTotalPrice();

        // Add customization options dynamically
        for (String option : menuItem.getCustomizationOptions()) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(option);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedCustomizations.add(option);
                } else {
                    selectedCustomizations.remove(option);
                }
            });
            customizationsContainer.addView(checkBox);
        }

        // Quantity controls
        decreaseBtn.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityAndPrice();
            }
        });

        increaseBtn.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndPrice();
        });

        // Handle add to cart
        addToCartButton.setOnClickListener(v -> {
            CartItem cartItem = new CartItem(menuItem, new ArrayList<>(selectedCustomizations), quantity);
            CartManager.getInstance().addItem(cartItem);
            dismiss();
        });

        return view;
    }

    private void updateQuantityAndPrice() {
        quantityText.setText(String.valueOf(quantity));
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = menuItem.getPrice() * quantity;
        totalPrice.setText(String.format("Total: $%.2f", total));
    }
} 