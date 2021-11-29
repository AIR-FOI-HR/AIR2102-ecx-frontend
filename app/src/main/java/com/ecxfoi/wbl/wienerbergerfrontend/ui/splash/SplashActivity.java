package com.ecxfoi.wbl.wienerbergerfrontend.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

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

    private void switchActivities()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}