package com.gdritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ritzys.R;
import com.gdritzys.adapters.MenuCategoryAdapter;
import com.gdritzys.adapters.MenuItemsAdapter;
import com.gdritzys.utils.MenuDataProvider;
import com.gdritzys.models.MenuItem;
import java.util.ArrayList;

public class MenuFragment extends Fragment {
    private RecyclerView menuCategoriesRecycler;
    private RecyclerView menuItemsRecycler;
    private MenuCategoryAdapter categoryAdapter;
    private MenuItemsAdapter itemsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        
        menuCategoriesRecycler = view.findViewById(R.id.menu_categories_recycler);
        menuItemsRecycler = view.findViewById(R.id.menu_items_recycler);
        
        setupMenuCategories();
        setupMenuItems("Combos"); // Default category
        
        return view;
    }

    private void setupMenuCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Combos");
        categories.add("Sandwiches");
        categories.add("Hot Dogs");
        categories.add("Chili");

        categoryAdapter = new MenuCategoryAdapter(categories, category -> {
            // Handle category selection
            setupMenuItems(category);
        });

        menuCategoriesRecycler.setAdapter(categoryAdapter);
        menuCategoriesRecycler.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setupMenuItems(String category) {
        ArrayList<MenuItem> items = MenuDataProvider.getMenuItemsByCategory(category);
        itemsAdapter = new MenuItemsAdapter(items, item -> {
            // Launch item detail fragment
            showItemDetails(item);
        });

        menuItemsRecycler.setAdapter(itemsAdapter);
        menuItemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showItemDetails(MenuItem item) {
        ItemDetailFragment fragment = ItemDetailFragment.newInstance(item);
        fragment.show(getParentFragmentManager(), "item_detail");
    }
} 