package com.example.ritzys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gdritzys.fragments.HomeFragment;
import com.gdritzys.fragments.MenuFragment;
import com.gdritzys.fragments.RewardsFragment;
import com.gdritzys.fragments.OrderFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide default title

        // Set up bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_menu) {
                selectedFragment = new MenuFragment();
            } else if (itemId == R.id.nav_rewards) {
                selectedFragment = new RewardsFragment();
            } else if (itemId == R.id.nav_order) {
                selectedFragment = new OrderFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            }
            return true;
        });

        // Show HomeFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }
}