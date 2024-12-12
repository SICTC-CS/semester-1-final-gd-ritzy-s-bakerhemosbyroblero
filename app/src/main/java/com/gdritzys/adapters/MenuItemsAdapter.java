package com.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.gdritzys.models.MenuItem;
import java.util.ArrayList;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuItemViewHolder> {
    private ArrayList<MenuItem> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    public MenuItemsAdapter(ArrayList<MenuItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_menu, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem item = items.get(position);
        
        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText(String.format("$%.2f", item.getPrice()));
        
        // TODO: Load image using your preferred image loading library
        // Glide.with(holder.itemView).load(item.getImageUrl()).into(holder.itemImage);
        
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemDescription;
        TextView itemPrice;

        MenuItemViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }
} 