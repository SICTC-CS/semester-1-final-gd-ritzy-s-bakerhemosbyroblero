package com.example.ritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.ritzys.R;
import com.example.ritzys.adapters.PromotionsAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager2 promotionsViewPager;
    private PromotionsAdapter promotionsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        promotionsViewPager = view.findViewById(R.id.promotions_viewpager);
        setupPromotions();
        
        return view;
    }

    private void setupPromotions() {
        List<String> promotions = new ArrayList<>();
        promotions.add("Try our World Famous Shoestring Fries!");
        promotions.add("New! World's Best PB&J Sandwich");
        promotions.add("Download our app for exclusive rewards!");
        
        promotionsAdapter = new PromotionsAdapter(promotions);
        promotionsViewPager.setAdapter(promotionsAdapter);
    }
} 