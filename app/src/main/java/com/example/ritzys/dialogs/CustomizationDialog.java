package com.example.ritzys.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.ritzys.R;
import com.example.ritzys.models.CartItem;
import com.example.ritzys.models.CartManager;
import com.example.ritzys.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class CustomizationDialog extends Dialog {
    private MenuItem menuItem;
    private List<String> customizations;
    private int quantity = 1;
    private static final int MAX_QUANTITY = 10;
    private double extrasCost = 0.0;

    public CustomizationDialog(@NonNull Context context, MenuItem menuItem) {
        super(context);
        this.menuItem = menuItem;
        this.customizations = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customization);

        // Set dialog width to almost screen width
        if (getWindow() != null) {
            getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            );
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().setGravity(Gravity.CENTER);
        }

        TextView titleText = findViewById(R.id.item_name);
        TextView priceText = findViewById(R.id.item_price);
        TextView quantityText = findViewById(R.id.quantity_text);
        ImageButton decreaseButton = findViewById(R.id.decrease_quantity);
        ImageButton increaseButton = findViewById(R.id.increase_quantity);
        LinearLayout customizationsLayout = findViewById(R.id.customizations_layout);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        titleText.setText(menuItem.getName());

        decreaseButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
                updatePrice(priceText);
            }
        });

        increaseButton.setOnClickListener(v -> {
            if (quantity < MAX_QUANTITY) {
                quantity++;
                quantityText.setText(String.valueOf(quantity));
                updatePrice(priceText);
            }
        });

        setupCustomizationOptions(customizationsLayout);
        updatePrice(priceText);

        addToCartButton.setOnClickListener(v -> {
            String customizationsStr = String.join(", ", customizations);
            CartItem cartItem = new CartItem(menuItem, quantity, customizationsStr, 
                                           menuItem.getPrice() + extrasCost);
            CartManager.getInstance().addItem(cartItem);
            dismiss();
        });
    }

    private void setupCustomizationOptions(LinearLayout layout) {
        String category = menuItem.getCategory();
        //setting the topping options based on catagory
        if (category.equals("sandwiches")) { //sandwich catagory options
            addCheckboxOption(layout, "No Lettuce", 0);
            addCheckboxOption(layout, "No Tomato", 0);
            addCheckboxOption(layout, "No Onion", 0);
            addCheckboxOption(layout, "No Pickles", 0);
            addCheckboxOption(layout, "No Mayo", 0);
            addCheckboxOption(layout, "Extra Mayo", 0.25); //it's not free because its extra same with the others
            addCheckboxOption(layout, "Extra Cheese", 0.50);
            if (menuItem.getName().contains("Fish")) {// fish catagory options
                addCheckboxOption(layout, "No Tartar Sauce", 0);
                addCheckboxOption(layout, "Extra Tartar Sauce", 0.25);
            }
        } else if (category.equals("combos")) {// combo catagory options
            addCheckboxOption(layout, "Large Fries", 1.00);
            addCheckboxOption(layout, "Large Drink", 0.40);
            addCheckboxOption(layout, "Onion Rings Instead", 1.00);
        } else if (category.equals("shakes") || category.equals("desserts")) { //shakes/deserts catagory options
            addCheckboxOption(layout, "Extra Whipped Cream", 0.25);
            addCheckboxOption(layout, "Add Cherry", 0.25);
            if (menuItem.getName().contains("Sundae")) { //sundaes catagory options
                addCheckboxOption(layout, "Extra Sauce", 0.50);
                addCheckboxOption(layout, "Add Nuts", 0.50);
            }
        } else if (category.equals("kids")) { //kids catagory options
            addCheckboxOption(layout, "No Lettuce", 0);
            addCheckboxOption(layout, "No Tomato", 0);
            addCheckboxOption(layout, "No Pickles", 0);
            addCheckboxOption(layout, "Apple Juice Instead of Soda", 0.50);
        }
    }

    private void addCheckboxOption(LinearLayout layout, String text, double price) { //displaying the options
        CheckBox checkBox = new CheckBox(getContext());
        String displayText = price > 0 ? String.format("%s (+$%.2f)", text, price) : text;
        checkBox.setText(displayText);
        checkBox.setTextSize(16);
        checkBox.setPadding(0, 8, 0, 8);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                customizations.add(text);
                extrasCost += price;
            } else {
                customizations.remove(text);
                extrasCost -= price;
            }
            updatePrice((TextView) findViewById(R.id.item_price));
        });
        layout.addView(checkBox);
    }

    private void updatePrice(TextView priceText) { //updating the price of the item
        double total = (menuItem.getPrice() + extrasCost) * quantity;
        priceText.setText(String.format("$%.2f", total));
    }
} 