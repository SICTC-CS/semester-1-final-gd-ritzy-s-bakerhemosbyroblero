package com.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.gdritzys.models.QuickAction;
import java.util.ArrayList;

public class QuickActionsAdapter extends RecyclerView.Adapter<QuickActionsAdapter.QuickActionViewHolder> {
    private ArrayList<QuickAction> actions;

    public QuickActionsAdapter(ArrayList<QuickAction> actions) {
        this.actions = actions;
    }

    @NonNull
    @Override
    public QuickActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_quick_action, parent, false);
        return new QuickActionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuickActionViewHolder holder, int position) {
        QuickAction action = actions.get(position);
        holder.icon.setImageResource(action.getIconResource());
        holder.title.setText(action.getTitle());
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    static class QuickActionViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        QuickActionViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.quick_action_icon);
            title = itemView.findViewById(R.id.quick_action_title);
        }
    }
} 