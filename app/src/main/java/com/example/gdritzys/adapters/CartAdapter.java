package com.example.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gdritzys.R;
import com.example.gdritzys.managers.CartManager;
import com.example.gdritzys.models.CartItem;
import java.util.Locale;

public class CartAdapter extends ListAdapter<CartItem, CartAdapter.CartViewHolder> {

    public CartAdapter() {
        super(new DiffUtil.ItemCallback<CartItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
                return oldItem.getQuantity() == newItem.getQuantity();
            }
        });
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView customizationsText;
        private final TextView priceText;
        private final TextView quantityText;
        private final ImageButton decreaseButton;
        private final ImageButton increaseButton;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.item_name);
            customizationsText = itemView.findViewById(R.id.customizations);
            priceText = itemView.findViewById(R.id.price);
            quantityText = itemView.findViewById(R.id.quantity);
            decreaseButton = itemView.findViewById(R.id.decrease_button);
            increaseButton = itemView.findViewById(R.id.increase_button);
        }

        void bind(CartItem item) {
            nameText.setText(item.getMenuItem().getName());
            priceText.setText(String.format(Locale.US, "$%.2f", item.getSubtotal()));
            quantityText.setText(String.valueOf(item.getQuantity()));

            String customizations = item.getCustomizationSummary();
            if (!customizations.isEmpty()) {
                customizationsText.setVisibility(View.VISIBLE);
                customizationsText.setText(customizations);
            } else {
                customizationsText.setVisibility(View.GONE);
            }

            decreaseButton.setOnClickListener(v -> 
                CartManager.getInstance().updateItemQuantity(item, item.getQuantity() - 1));
            increaseButton.setOnClickListener(v -> 
                CartManager.getInstance().updateItemQuantity(item, item.getQuantity() + 1));
        }
    }
} 