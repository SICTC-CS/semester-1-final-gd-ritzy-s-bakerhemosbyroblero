package com.example.gdritzys.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdritzys.adapters.MenuAdapter;
import com.example.gdritzys.databinding.FragmentFoodBinding;
import com.example.gdritzys.models.MenuItem;

public class FoodFragment extends Fragment implements MenuAdapter.OnItemClickListener {

    private FragmentFoodBinding binding;
    private MenuAdapter adapter;
    private FoodViewModel foodViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.menuRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MenuAdapter(this);
        recyclerView.setAdapter(adapter);

        foodViewModel.getMenuItems().observe(getViewLifecycleOwner(), menuItems -> {
            adapter.updateItems(menuItems);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAddToCartClick(MenuItem item) {
        // TODO: Implement cart functionality
        Toast.makeText(getContext(), item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }
} 