package com.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.gdritzys.models.CartItem;
import java.util.ArrayList;
import android.text.TextUtils;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {
    private ArrayList<CartItem> cartItems;
    private CartItemListener listener;

    public interface CartItemListener {
        void onQuantityChanged(CartItem item, int newQuantity);
    }

    public CartAdapter(ArrayList<CartItem> cartItems, CartItemListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_cart, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        
        holder.itemName.setText(item.getMenuItem().getName());
        holder.itemPrice.setText(String.format("$%.2f", item.getTotalPrice()));
        holder.quantityText.setText(String.valueOf(item.getQuantity()));
        
        // Show customizations if any
        if (item.getSelectedCustomizations().isEmpty()) {
            holder.itemCustomizations.setVisibility(View.GONE);
        } else {
            holder.itemCustomizations.setVisibility(View.VISIBLE);
            holder.itemCustomizations.setText(TextUtils.join(", ", item.getSelectedCustomizations()));
        }

        // Handle quantity changes
        holder.decreaseQuantity.setOnClickListener(v -> {
            int newQuantity = item.getQuantity() - 1;
            listener.onQuantityChanged(item, newQuantity);
        });

        holder.increaseQuantity.setOnClickListener(v -> {
            int newQuantity = item.getQuantity() + 1;
            listener.onQuantityChanged(item, newQuantity);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemCustomizations;
        TextView itemPrice;
        TextView quantityText;
        ImageButton decreaseQuantity;
        ImageButton increaseQuantity;

        CartItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemCustomizations = itemView.findViewById(R.id.item_customizations);
            itemPrice = itemView.findViewById(R.id.item_price);
            quantityText = itemView.findViewById(R.id.quantity_text);
            decreaseQuantity = itemView.findViewById(R.id.decrease_quantity);
            increaseQuantity = itemView.findViewById(R.id.increase_quantity);
        }
    }
} 