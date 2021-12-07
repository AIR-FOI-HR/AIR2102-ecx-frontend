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
    }

    private void initBindings()
    {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ivHamburger = binding.btnMenu;
    }
}