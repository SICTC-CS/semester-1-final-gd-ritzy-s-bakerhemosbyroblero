package com.example.ritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.example.ritzys.models.MenuCategory;
import com.example.ritzys.models.MenuItem;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_ITEM = 1;

    private List<MenuCategory> categories;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    public MenuAdapter(List<MenuCategory> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = 0;
        for (MenuCategory category : categories) {
            if (position == itemCount) {
                return TYPE_CATEGORY;
            }
            itemCount++;
            if (category.isExpanded()) {
                if (position < itemCount + category.getItems().size()) {
                    return TYPE_ITEM;
                }
                itemCount += category.getItems().size();
            }
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_category_header, parent, false);
            return new CategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu, parent, false);
            return new MenuViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoryViewHolder) {
            MenuCategory category = getCategoryForPosition(position);
            ((CategoryViewHolder) holder).bind(category);
        } else if (holder instanceof MenuViewHolder) {
            MenuItem item = getItemForPosition(position);
            ((MenuViewHolder) holder).bind(item, listener);
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for (MenuCategory category : categories) {
            itemCount++; // Category header
            if (category.isExpanded()) {
                itemCount += category.getItems().size();
            }
        }
        return itemCount;
    }

    private MenuCategory getCategoryForPosition(int position) {
        int itemCount = 0;
        for (MenuCategory category : categories) {
            if (position == itemCount) {
                return category;
            }
            itemCount++;
            if (category.isExpanded()) {
                itemCount += category.getItems().size();
            }
        }
        return null;
    }

    private MenuItem getItemForPosition(int position) {
        int itemCount = 0;
        for (MenuCategory category : categories) {
            itemCount++; // Skip category header
            if (category.isExpanded()) {
                int categoryItemCount = category.getItems().size();
                if (position < itemCount + categoryItemCount) {
                    return category.getItems().get(position - itemCount);
                }
                itemCount += categoryItemCount;
            }
        }
        return null;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView expandIcon;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.category_title);
            expandIcon = itemView.findViewById(R.id.expand_icon);

            itemView.setOnClickListener(v -> {
                MenuCategory category = getCategoryForPosition(getAdapterPosition());
                if (category != null) {
                    category.setExpanded(!category.isExpanded());
                    expandIcon.setRotation(category.isExpanded() ? 180 : 0);
                    notifyDataSetChanged();
                }
            });
        }

        void bind(MenuCategory category) {
            titleText.setText(category.getTitle());
            expandIcon.setRotation(category.isExpanded() ? 180 : 0);
        }
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView descriptionText;
        TextView priceText;

        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.item_name);
            descriptionText = itemView.findViewById(R.id.item_description);
            priceText = itemView.findViewById(R.id.item_price);
        }

        void bind(MenuItem item, OnItemClickListener listener) {
            nameText.setText(item.getName());
            descriptionText.setText(item.getDescription());
            priceText.setText(String.format("$%.2f", item.getPrice()));

            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
} 