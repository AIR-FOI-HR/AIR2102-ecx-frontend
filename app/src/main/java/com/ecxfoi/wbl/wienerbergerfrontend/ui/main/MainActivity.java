package com.ecxfoi.wbl.wienerbergerfrontend.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.api.JwtAuthInterceptor;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityMainBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainActivityViewModel>
{

    @Inject
    MainActivityViewModel viewModel;
    @Inject
    JwtAuthInterceptor authInterceptor;
    private DrawerLayout dlMainLayout;
    private NavigationView navigationView;
    private NavController navController;
    private ImageView ivHamburger;
    private TextView tvLogoutMenuItem;

    @Override
    public MainActivityViewModel getViewModel()
    {
        return viewModel;
    }

    @Override
    public void onBackPressed()
    {
        NavDestination currentDestination = navController.getCurrentDestination();

        if (dlMainLayout.isDrawerOpen(GravityCompat.START))
        {
            dlMainLayout.close();
        }
        else if (currentDestination != null && StringUtils.equals((String) currentDestination.getLabel(), "HomeFragment"))
        {
            super.onBackPressed();
        }
        else
        {
            navController.popBackStack();
        }
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
        final com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ivHamburger = binding.btnMenu;
        dlMainLayout = binding.layoutMain;
        navigationView = binding.navView;
        tvLogoutMenuItem = binding.tvFooterLogout;
    }

    private void initNavigation()
    {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null)
        {
            navController = navHostFragment.getNavController();

            NavigationUI.setupWithNavController(navigationView, navController);
        }

        navigationView.setNavigationItemSelectedListener(this::onDrawerItemSelected);
        navigationView.bringToFront();

        ivHamburger.setOnClickListener(v -> dlMainLayout.openDrawer(GravityCompat.START));

        tvLogoutMenuItem.setOnClickListener(view -> onLogoutMenuItemClick());
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

        if (selectedMenuItemId == currentMenuId)
        {
            return true;
        }

        NavigationUI.onNavDestinationSelected(item, navController);

        dlMainLayout.close();

        return true;
    }

    private void onLogoutMenuItemClick()
    {
        authInterceptor.setJwtToken("");
        startActivity(new Intent(this, LoginActivity.class));
    }
}