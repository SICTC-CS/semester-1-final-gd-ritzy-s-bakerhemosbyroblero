package com.example.gdritzys.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.NavOptions;

import com.example.gdritzys.R;
import com.example.gdritzys.databinding.FragmentHomeBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set up progress indicator
        LinearProgressIndicator progressIndicator = binding.pointsProgress;
        progressIndicator.setProgress(30);

        // Set up order now button
        binding.orderNowButton.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.nav_food, null,
                new NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .build());
        });

        // Observe updates from ViewModel
        homeViewModel.getUpdates().observe(getViewLifecycleOwner(), updates -> {
            binding.updatesText.setText(updates);
        });

        // Observe rewards points from ViewModel
        homeViewModel.getPoints().observe(getViewLifecycleOwner(), points -> {
            binding.pointsText.setText(points + " / 100 points");
            binding.pointsProgress.setProgress(points);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}