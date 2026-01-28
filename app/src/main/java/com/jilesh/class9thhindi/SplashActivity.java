package com.jilesh.class9thhindi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Show modern splash screen (Android 12+)
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo); // Declare here, accessible everywhere

        // Fallback animation for Android 11 and below
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_zoom);
            logo.startAnimation(animation);
        }

        // Delay to move to MainActivity
        logo.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 1400); // animation duration
    }
}
