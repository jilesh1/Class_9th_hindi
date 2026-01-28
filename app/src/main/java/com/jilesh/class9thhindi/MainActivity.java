package com.jilesh.class9thhindi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardGadya = findViewById(R.id.cardGadya);

        cardGadya.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GadyaKhandActivity.class);
            startActivity(intent);
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardKavya = findViewById(R.id.cardKavya);

        cardKavya.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KavyaKhandActivity.class);
            startActivity(intent);
        });
        CardView cardSanskrit = findViewById(R.id.cardSanskrit);

        cardSanskrit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SanskritKhandActivity.class);
            startActivity(intent);
        });
       CardView cardRate = findViewById(R.id.cardRate);

        cardRate.setOnClickListener(v -> {
            rateApp();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();   // Exit the app
            return;
        } else {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
    private void rateApp() {
        final String appPackageName = getPackageName(); // your app package name

        try {
            // Open Play Store app
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + appPackageName)
            ));
        } catch (android.content.ActivityNotFoundException e) {
            // Open Play Store in browser
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)
            ));
        }
    }

}
