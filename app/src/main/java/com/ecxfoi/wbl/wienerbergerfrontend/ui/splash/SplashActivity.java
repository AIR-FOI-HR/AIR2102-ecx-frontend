package com.ecxfoi.wbl.wienerbergerfrontend.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ecxfoi.wbl.wienerbergerfrontend.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title

        if (getSupportActionBar() != null) {
        getSupportActionBar().hide(); // hide the title bar
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // enable full screen

        setContentView(R.layout.activity_splash); // show splash screen with Wienerberger logo
    }
}