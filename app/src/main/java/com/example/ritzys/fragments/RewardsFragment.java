package com.example.ritzys.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.ritzys.R;
import com.example.ritzys.models.RewardsManager;

public class RewardsFragment extends Fragment {
    private TextView pointsValueText;
    private RewardsManager rewardsManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        
        pointsValueText = view.findViewById(R.id.points_value);
        rewardsManager = RewardsManager.getInstance(requireContext());
        updatePoints();
        
        return view;
    }

    private void updatePoints() {
        int points = rewardsManager.getCurrentPoints();
        pointsValueText.setText(String.valueOf(points));
    }
} 