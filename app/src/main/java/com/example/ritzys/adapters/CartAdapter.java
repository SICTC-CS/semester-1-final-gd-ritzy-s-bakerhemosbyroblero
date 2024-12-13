package com.example.ritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.example.ritzys.models.CartItem;
import com.example.ritzys.models.CartManager;
import com.example.ritzys.fragments.OrderFragment;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private OrderFragment orderFragment;

    public CartAdapter(List<CartItem> cartItems, OrderFragment orderFragment) {
        this.cartItems = cartItems;
        this.orderFragment = orderFragment;
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
        CartItem item = cartItems.get(position);
        holder.bind(item);
        //setting up delelete button listioner, for option to remove item and update total
        holder.deleteButton.setOnClickListener(v -> {
            CartManager.getInstance().removeItem(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            orderFragment.updateTotals();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView quantityText;
        TextView priceText;
        TextView customizationsText;
        ImageButton deleteButton;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            //setting up text items
            nameText = itemView.findViewById(R.id.cart_item_name);
            quantityText = itemView.findViewById(R.id.cart_item_quantity);
            priceText = itemView.findViewById(R.id.cart_item_price);
            customizationsText = itemView.findViewById(R.id.cart_item_customizations);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

        void bind(CartItem item) {
            //actually setting the text of the items
            nameText.setText(item.getMenuItem().getName());
            quantityText.setText(String.format("Qty: %d", item.getQuantity()));
            priceText.setText(String.format("$%.2f", item.getTotalPrice()));

            //Showing the customization text if it's not empty and not null.
            if (item.getCustomizations() != null && !item.getCustomizations().isEmpty()) {
                customizationsText.setVisibility(View.VISIBLE);
                customizationsText.setText(item.getCustomizations());
            } else {
                customizationsText.setVisibility(View.GONE);
            }
        }
    }
} 