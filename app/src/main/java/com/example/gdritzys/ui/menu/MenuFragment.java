package com.example.gdritzys.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.example.gdritzys.databinding.FragmentMenuBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private MenuViewModel menuViewModel;
    private MenuPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        setupViewPager();
        return binding.getRoot();
    }

    private void setupViewPager() {
        pagerAdapter = new MenuPagerAdapter(this);
        binding.menuViewPager.setAdapter(pagerAdapter);

        // Link tabs with ViewPager
        new TabLayoutMediator(binding.categoryTabs, binding.menuViewPager,
            (tab, position) -> tab.setText(MenuCategories.values()[position].getTitle())
        ).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 