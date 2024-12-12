package com.gdritzys.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ritzys.R;

import java.util.ArrayList;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.PromoViewHolder> {
    private ArrayList<Integer> promotionImages;

    public PromotionsAdapter(ArrayList<Integer> promotionImages) {
        this.promotionImages = promotionImages;
    }

    @NonNull
    @Override
    public PromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_promotion, parent, false);
        return new PromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoViewHolder holder, int position) {
        holder.imageView.setImageResource(promotionImages.get(position));
    }

    @Override
    public int getItemCount() {
        return promotionImages.size();
    }

    static class PromoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        PromoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.promo_image);
        }
    }
} 