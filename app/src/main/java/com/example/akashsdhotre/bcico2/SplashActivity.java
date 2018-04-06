package com.example.akashsdhotre.bcico2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    private Class activity = LoginActivity.class;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "N/A");
        password = sharedPreferences.getString("password", "N/A");

        if (email.equals("N/A") || password.equals("N/A")) {
            activity = LoginActivity.class;
        }
        else
        {
            activity = MainActivity.class;

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startActivityIntent = new Intent(SplashActivity.this, activity);
//                startActivityIntent.putExtra("email",email);
                startActivity(startActivityIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);




    }

}
