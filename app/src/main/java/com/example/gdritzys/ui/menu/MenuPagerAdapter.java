package com.example.gdritzys.ui.menu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MenuPagerAdapter extends FragmentStateAdapter {

    public MenuPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return MenuCategoryFragment.newInstance(MenuCategories.values()[position]);
    }

    @Override
    public int getItemCount() {
        return MenuCategories.values().length;
    }
} 