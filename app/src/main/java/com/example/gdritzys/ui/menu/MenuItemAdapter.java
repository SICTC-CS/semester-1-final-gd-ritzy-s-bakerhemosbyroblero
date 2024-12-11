package com.example.gdritzys.ui.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gdritzys.R;
import com.example.gdritzys.models.MenuItem;
import java.util.Locale;

public class MenuItemAdapter extends ListAdapter<MenuItem, MenuItemAdapter.MenuItemViewHolder> {
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    protected MenuItemAdapter(OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<MenuItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull MenuItem oldItem, @NonNull MenuItem newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull MenuItem oldItem, @NonNull MenuItem newItem) {
                return oldItem.getPrice() == newItem.getPrice() &&
                       oldItem.getDescription().equals(newItem.getDescription());
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu_product, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class MenuItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameText;
        private final TextView descriptionText;
        private final TextView priceText;

        MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            nameText = itemView.findViewById(R.id.product_name);
            descriptionText = itemView.findViewById(R.id.product_description);
            priceText = itemView.findViewById(R.id.product_price);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        void bind(MenuItem item) {
            nameText.setText(item.getName());
            descriptionText.setText(item.getDescription());
            priceText.setText(String.format(Locale.US, "$%.2f", item.getPrice()));
            // TODO: Load image using Glide or similar library
            // Glide.with(imageView).load(item.getImageUrl()).into(imageView);
        }
    }
} 