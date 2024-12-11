package com.example.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gdritzys.R;
import com.example.gdritzys.models.ToppingOption;
import java.util.List;

public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ViewHolder> {
    private final List<ToppingOption> toppings;
    private final OnToppingChangedListener listener;

    public interface OnToppingChangedListener {
        void onToppingChanged(ToppingOption topping, int selection);
    }

    public ToppingsAdapter(List<ToppingOption> toppings, OnToppingChangedListener listener) {
        this.toppings = toppings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topping_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(toppings.get(position));
    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final RadioGroup optionsGroup;
        private final RadioButton noneButton;
        private final RadioButton normalButton;
        private final RadioButton extraButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.topping_name);
            optionsGroup = itemView.findViewById(R.id.topping_options);
            noneButton = itemView.findViewById(R.id.option_none);
            normalButton = itemView.findViewById(R.id.option_normal);
            extraButton = itemView.findViewById(R.id.option_extra);
        }

        void bind(ToppingOption topping) {
            nameText.setText(topping.getName());
            
            // Set initial selection
            switch (topping.getSelection()) {
                case ToppingOption.NONE:
                    noneButton.setChecked(true);
                    break;
                case ToppingOption.NORMAL:
                    normalButton.setChecked(true);
                    break;
                case ToppingOption.EXTRA:
                    extraButton.setChecked(true);
                    break;
            }

            optionsGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int selection;
                if (checkedId == R.id.option_none) {
                    selection = ToppingOption.NONE;
                } else if (checkedId == R.id.option_normal) {
                    selection = ToppingOption.NORMAL;
                } else {
                    selection = ToppingOption.EXTRA;
                }
                topping.setSelection(selection);
                listener.onToppingChanged(topping, selection);
            });
        }
    }
} 