package com.jilesh.class9thhindi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 imageSlider;
    private Handler sliderHandler;
    private Runnable sliderRunnable;
    private int slideCount;
    private static final long SLIDER_DELAY_MS = 3000L;

    private long backPressedTime = 0;
    private AdView adView;
    private MaterialButton btnNotification;

    // 🔥 Interstitial Ad
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CardView btnPrivacy = findViewById(R.id.btnPrivacy);
        CardView about_us = findViewById(R.id.about_us);

        about_us.setOnClickListener(v ->
                startActivity(new Intent(this, AboutActivity.class)));

        btnPrivacy.setOnClickListener(v ->
                startActivity(new Intent(this, PrivacyPolicyActivity.class)));

        // Notification
        btnNotification = findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(v ->
                Toast.makeText(this, "No new notifications", Toast.LENGTH_SHORT).show()
        );

        // ✅ AdMob init
        MobileAds.initialize(this, initializationStatus -> {});

        // ✅ Banner
        adView = findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());

        // ✅ Load Interstitial
        loadInterstitial();

        // Slider
        setupSlider();

        // 🔥 Cards with Ads
        findViewById(R.id.cardGadya).setOnClickListener(v ->
                openWithAd(GadyaKhandActivity.class));

        findViewById(R.id.cardKavya).setOnClickListener(v ->
                openWithAd(KavyaKhandActivity.class));

        findViewById(R.id.cardSanskrit).setOnClickListener(v ->
                openWithAd(SanskritKhandActivity.class));

        findViewById(R.id.cardRate).setOnClickListener(v -> rateApp());
    }

    // 🔥 Load Interstitial
    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,
                "ca-app-pub-3940256099942544/1033173712", // TEST ID
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd ad) {
                        mInterstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError error) {
                        mInterstitialAd = null;
                    }
                });
    }

    // 🔥 Show Ad + Open Activity
    private void openWithAd(Class<?> targetActivity) {

        Intent intent = new Intent(this, targetActivity);

        if (mInterstitialAd != null) {

            mInterstitialAd.show(this);

            mInterstitialAd.setFullScreenContentCallback(
                    new FullScreenContentCallback() {

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            startActivity(intent);
                            loadInterstitial(); // reload next
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            startActivity(intent);
                        }
                    });

        } else {
            startActivity(intent);
        }
    }

    private void setupSlider() {
        imageSlider = findViewById(R.id.image_slider);

        List<SliderAdapter.SlideItem> slides = new ArrayList<>();

        slides.add(new SliderAdapter.SlideItem(
                R.drawable.slider_bg_1,
                "FEATURED · PROSE",
                "गद्य-खंड",
                "New Book"
        ));

        slides.add(new SliderAdapter.SlideItem(
                R.drawable.slider_bg_2,
                "FEATURED · VERSE",
                "काव्य-खंड",
                "Poetry"
        ));

        slides.add(new SliderAdapter.SlideItem(
                R.drawable.gradient_card_sanskrit,
                "FEATURED · CLASSICAL",
                "संस्कृत-खंड",
                "Sanskrit"
        ));

        slideCount = slides.size();

        SliderAdapter adapter = new SliderAdapter(this, slides, position -> {

            if (position == 0) openWithAd(GadyaKhandActivity.class);
            else if (position == 1) openWithAd(KavyaKhandActivity.class);
            else if (position == 2) openWithAd(SanskritKhandActivity.class);

        });

        imageSlider.setAdapter(adapter);

        sliderHandler = new Handler(Looper.getMainLooper());
        sliderRunnable = () -> {
            int next = (imageSlider.getCurrentItem() + 1) % slideCount;
            imageSlider.setCurrentItem(next, true);
            sliderHandler.postDelayed(sliderRunnable, SLIDER_DELAY_MS);
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) adView.resume();
        if (sliderHandler != null) {
            sliderHandler.postDelayed(sliderRunnable, SLIDER_DELAY_MS);
        }
    }

    @Override
    protected void onPause() {
        if (adView != null) adView.pause();
        if (sliderHandler != null) {
            sliderHandler.removeCallbacks(sliderRunnable);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) adView.destroy();
        super.onDestroy();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            backPressedTime = System.currentTimeMillis();
        }
    }

    private void rateApp() {
        String packageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + packageName)));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}