package com.example.gdritzys.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> updates;
    private final MutableLiveData<Integer> points;

    public HomeViewModel() {
        updates = new MutableLiveData<>();
        points = new MutableLiveData<>();
        
        // Initialize with some example data
        updates.setValue("🎉 New Summer Flavors are here!\n\n" +
                "🍔 Try our new Double Ritz Combo\n\n" +
                "🏆 Earn double points this weekend!");
        points.setValue(30); // Example points value
    }

    public LiveData<String> getUpdates() {
        return updates;
    }

    public LiveData<Integer> getPoints() {
        return points;
    }
}