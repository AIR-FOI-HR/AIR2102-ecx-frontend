package com.ecxfoi.wbl.wienerbergerfrontend.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

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

        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                switchActivities();
            }
        }, 600);
    }

    private void switchActivities() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}