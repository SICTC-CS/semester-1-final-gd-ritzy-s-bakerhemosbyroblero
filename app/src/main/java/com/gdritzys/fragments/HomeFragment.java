package com.gdritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.ritzys.R;
import com.gdritzys.adapters.PromotionsAdapter;
import com.gdritzys.adapters.QuickActionsAdapter;
import com.gdritzys.models.QuickAction;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        // Initialize welcome banner and promotions
        ViewPager2 promotionsViewPager = view.findViewById(R.id.promotions_viewpager);
        RecyclerView quickActions = view.findViewById(R.id.quick_actions_recycler);
        
        // Set up promotions slider
        setupPromotionsSlider(promotionsViewPager);
        
        // Set up quick action buttons
        setupQuickActions(quickActions);
        
        return view;
    }

    private void setupPromotionsSlider(ViewPager2 viewPager) {
        ArrayList<Integer> promotionImages = new ArrayList<>();
        promotionImages.add(R.drawable.promo_burgers);
        promotionImages.add(R.drawable.promo_icecream);
        promotionImages.add(R.drawable.promo_rewards);
        
        PromotionsAdapter adapter = new PromotionsAdapter(promotionImages);
        viewPager.setAdapter(adapter);
        
        // Auto-scroll promotions every 3 seconds
        viewPager.setCurrentItem(0);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    int nextItem = (viewPager.getCurrentItem() + 1) % promotionImages.size();
                    viewPager.setCurrentItem(nextItem, true);
                }
            };

            @Override
            public void onPageSelected(int position) {
                viewPager.removeCallbacks(runnable);
                viewPager.postDelayed(runnable, 3000);
            }
        });
    }

    private void setupQuickActions(RecyclerView recyclerView) {
        ArrayList<QuickAction> actions = new ArrayList<>();
        actions.add(new QuickAction("Order Now", R.drawable.ic_baseline_shopping_cart_24));
        actions.add(new QuickAction("Menu", R.drawable.ic_baseline_restaurant_menu_24));
        actions.add(new QuickAction("Rewards", R.drawable.ic_baseline_stars_24));
        
        QuickActionsAdapter adapter = new QuickActionsAdapter(actions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 
            LinearLayoutManager.HORIZONTAL, false));
    }
} 