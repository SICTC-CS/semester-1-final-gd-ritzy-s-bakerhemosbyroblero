package com.example.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gdritzys.R;
import com.example.gdritzys.models.CustomizationOption;
import java.util.List;
import java.util.Locale;

public class CustomizationAdapter extends RecyclerView.Adapter<CustomizationAdapter.ViewHolder> {
    private final List<CustomizationOption> options;
    private final OnOptionSelectedListener listener;

    public interface OnOptionSelectedListener {
        void onOptionSelected(CustomizationOption option, boolean isSelected);
    }

    public CustomizationAdapter(List<CustomizationOption> options, OnOptionSelectedListener listener) {
        this.options = options;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customization_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(options.get(position));
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final TextView priceText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            priceText = itemView.findViewById(R.id.price);
        }

        void bind(CustomizationOption option) {
            checkBox.setText(option.getName());
            checkBox.setChecked(option.isSelected());
            
            if (option.getAdditionalCost() > 0) {
                priceText.setText(String.format(Locale.US, "+$%.2f", option.getAdditionalCost()));
                priceText.setVisibility(View.VISIBLE);
            } else {
                priceText.setVisibility(View.GONE);
            }

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                option.setSelected(isChecked);
                listener.onOptionSelected(option, isChecked);
            });
        }
    }
} 