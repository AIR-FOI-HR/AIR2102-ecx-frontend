package com.ecxfoi.wbl.wienerbergerfrontend.ui.main;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ActivityMainBinding binding;

    private DrawerLayout dlMainLayout;
    private NavigationView navigationView;
    private NavController navController;
    private ImageView ivHamburger;
    private TextView tvLogoutMenuItem;

    @Inject
    MainActivityViewModel viewModel;

    @Inject
    JwtAuthInterceptor authInterceptor;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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

            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph()).build();

            NavigationUI.setupWithNavController(navigationView, navController);
        }

        navigationView.setNavigationItemSelectedListener(this::onDrawerItemSelected);
        navigationView.bringToFront();

        ivHamburger.setOnClickListener(v -> dlMainLayout.openDrawer(GravityCompat.START));

        tvLogoutMenuItem.setOnClickListener(this::onLogoutMenuItemClick);
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
        // This would work if we didn't need to put the logout item at the bottom of the screen,
        // in which case we need to manually bind it to the since it's not a regular menu item any more
        /*else if (selectedMenuItemId == R.id.logout)
        {
        } */

        NavigationUI.onNavDestinationSelected(item, navController);

        dlMainLayout.close();

        return true;
    }

    private void onLogoutMenuItemClick(final View view)
    {
        authInterceptor.setJwtToken("");
        startActivity(new Intent(this, LoginActivity.class));
    }
}