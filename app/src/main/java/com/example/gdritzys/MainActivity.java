package com.example.gdritzys;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.gdritzys.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartManager = CartManager.getInstance();
        setupNavigation();
        setupCartPanel();
    }

    private void setupNavigation() {
        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_order, R.id.nav_deals, R.id.nav_rewards)
                .build();
        
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
    }

    private void setupCartPanel() {
        SlidingUpPanelLayout slidingLayout = binding.slidingLayout;
        CartPanelBinding cartBinding = binding.cartPanel;

        // Setup RecyclerView
        CartAdapter cartAdapter = new CartAdapter();
        cartBinding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartBinding.cartRecyclerView.setAdapter(cartAdapter);

        // Observe cart changes
        cartManager.getCartItems().observe(this, cartAdapter::submitList);
        cartManager.getSubtotal().observe(this, subtotal -> 
            cartBinding.subtotalText.setText(String.format(Locale.US, "$%.2f", subtotal)));
        cartManager.getTax().observe(this, tax -> 
            cartBinding.taxText.setText(String.format(Locale.US, "$%.2f", tax)));
        cartManager.getTotal().observe(this, total -> 
            cartBinding.totalText.setText(String.format(Locale.US, "$%.2f", total)));
        cartManager.getItemCount().observe(this, count -> {
            cartBinding.cartItemCount.setText(String.format(Locale.US, "%d items", count));
            cartBinding.checkoutButton.setEnabled(count > 0);
        });

        // Setup checkout button
        cartBinding.checkoutButton.setOnClickListener(v -> {
            // TODO: Implement checkout
            cartManager.clear();
            slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            showCheckoutSuccessDialog();
        });

        // Setup rewards
        RewardsManager.getInstance().getPoints().observe(this, points -> {
            boolean canRedeem = points >= 100;
            cartBinding.applyRewardButton.setEnabled(canRedeem);
            cartBinding.applyRewardButton.setText(
                String.format("Apply $5 Reward (%d/100 points)", points));
        });
    }

    private void showCheckoutSuccessDialog() {
        new MaterialAlertDialogBuilder(this)
            .setTitle("Order Placed!")
            .setMessage("Your order has been placed successfully. You'll receive a notification when it's ready.")
            .setPositiveButton("OK", null)
            .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}