package com.example.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gdritzys.R;
import com.example.gdritzys.models.MenuItem;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_ITEM = 1;

    private List<Object> items = new ArrayList<>();
    private Map<String, Boolean> expandedCategories = new HashMap<>();
    private Map<String, List<MenuItem>> categorizedItems = new TreeMap<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onAddToCartClick(MenuItem item);
    }

    public MenuAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void updateItems(List<MenuItem> menuItems) {
        // Clear and rebuild categorized items
        categorizedItems.clear();
        for (MenuItem item : menuItems) {
            categorizedItems.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
        }

        // Rebuild the items list
        rebuildItemsList();
    }

    private void rebuildItemsList() {
        items.clear();
        for (Map.Entry<String, List<MenuItem>> entry : categorizedItems.entrySet()) {
            String category = entry.getKey();
            items.add(category); // Add category header
            if (expandedCategories.getOrDefault(category, false)) {
                items.addAll(entry.getValue()); // Add items if category is expanded
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) instanceof String ? TYPE_CATEGORY : TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_category_header, parent, false);
            return new CategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_item, parent, false);
            return new MenuViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CATEGORY) {
            ((CategoryViewHolder) holder).bind((String) items.get(position));
        } else {
            ((MenuViewHolder) holder).bind((MenuItem) items.get(position), listener);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private ImageView arrowIcon;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.category_title);
            arrowIcon = itemView.findViewById(R.id.category_arrow);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    String category = (String) items.get(position);
                    boolean isExpanded = expandedCategories.getOrDefault(category, false);
                    expandedCategories.put(category, !isExpanded);
                    rebuildItemsList();
                }
            });
        }

        public void bind(String category) {
            titleText.setText(category);
            boolean isExpanded = expandedCategories.getOrDefault(category, false);
            arrowIcon.setRotation(isExpanded ? 180 : 0);
        }
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private TextView descriptionText;
        private TextView priceText;
        private MaterialButton addToCartButton;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.item_name);
            descriptionText = itemView.findViewById(R.id.item_description);
            priceText = itemView.findViewById(R.id.item_price);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }

        public void bind(MenuItem item, OnItemClickListener listener) {
            nameText.setText(item.getName());
            descriptionText.setText(item.getDescription());
            priceText.setText(String.format(Locale.US, "$%.2f", item.getPrice()));
            addToCartButton.setOnClickListener(v -> listener.onAddToCartClick(item));
        }
    }
} 