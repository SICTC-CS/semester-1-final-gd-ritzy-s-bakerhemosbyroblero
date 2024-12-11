package com.example.gdritzys.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gdritzys.R;
import com.example.gdritzys.models.MenuItem;

public class MenuCategoryFragment extends Fragment {
    private static final String ARG_CATEGORY = "category";
    private MenuCategories category;
    private MenuViewModel menuViewModel;
    private MenuItemAdapter adapter;

    public static MenuCategoryFragment newInstance(MenuCategories category) {
        MenuCategoryFragment fragment = new MenuCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = MenuCategories.valueOf(getArguments().getString(ARG_CATEGORY));
        }
        menuViewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu_category, container, false);
        
        RecyclerView recyclerView = root.findViewById(R.id.menu_items_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new MenuItemAdapter(item -> {
            // Handle item click - show customization dialog
            menuViewModel.showCustomizationDialog(item);
        });
        
        recyclerView.setAdapter(adapter);

        // Observe menu items for this category
        menuViewModel.getMenuItemsForCategory(category).observe(getViewLifecycleOwner(),
            menuItems -> adapter.submitList(menuItems));

        return root;
    }
} 