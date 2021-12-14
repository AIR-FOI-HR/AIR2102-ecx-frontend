package com.ecxfoi.wbl.wienerbergerfrontend.ui.main;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityMainBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainActivityViewModel>
{
    private ActivityMainBinding binding;

    private DrawerLayout dlMainLayout;
    private NavigationView navigationView;
    private NavController navController;
    private ImageView ivHamburger;

    @Inject
    MainActivityViewModel viewModel;

    @Override
    public MainActivityViewModel getViewModel()
    {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initBindings();
        initNavigation();
    }

    private void initBindings()
    {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ivHamburger = binding.btnMenu;
        dlMainLayout = binding.layoutMain;
        navigationView = binding.navView;
    }

    private void initNavigation()
    {
        navigationView.setNavigationItemSelectedListener(this::onDrawerItemSelected);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null)
        {
            navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph()).build();

            NavigationUI.setupWithNavController(navigationView, navController);
        }

        ivHamburger.setOnClickListener(v -> {
            dlMainLayout.openDrawer(GravityCompat.START);
        });
    }

    public boolean onDrawerItemSelected(@NonNull final MenuItem item)
    {
        int selectedMenuItemId = item.getItemId();

        NavDestination currentDestination = navController.getCurrentDestination();
        if (currentDestination == null)
        {
            return true;
        }
        int currentMenuId = currentDestination.getId();

        if (selectedMenuItemId == currentMenuId) // When linked with NavigationUI the current item disappears, but just in case
        {
            return true;
        }
        else if (selectedMenuItemId == R.id.logout)
        {
            //TODO: implement logout
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        NavigationUI.onNavDestinationSelected(item, navController);

        return true;
    }
}