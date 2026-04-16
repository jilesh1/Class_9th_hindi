package com.jilesh.class9thhindi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class SanskritKhandActivity extends AppCompatActivity {

    ListView listView;

    // 🔥 Interstitial
    private InterstitialAd mInterstitialAd;
    private int clickCount = 0;

    String[] lessons = {
            "पाठ 1 वन्दना",
            "पाठ 2 सदाचारः",
            "पाठ 3 पुरुषोत्तमः रामः",
            "पाठ 4 सिद्धिमन्त्रः",
            "पाठ 5 सुभाषितानि",
            "पाठ 6 परमहंसः रामकृष्णः",
            "पाठ 7 कृष्णः गोपालनन्दनः"
    };

    String[] htmlFiles = {
            "sk1.html",
            "sk2.html",
            "sk3.html",
            "sk4.html",
            "sk5.html",
            "sk6.html",
            "sk7.html"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanskrit_khand);

        listView = findViewById(R.id.listView);

        // ✅ Adapter
        CustomAdapter adapter = new CustomAdapter(this, lessons, "sanskrit");
        listView.setAdapter(adapter);

        // 🔥 Load Ad
        loadInterstitial();

        // 🔥 Click with Ads
        listView.setOnItemClickListener((parent, view, position, id) -> {

            clickCount++;

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("html", htmlFiles[position]);
            intent.putExtra("bg", "sanskrit");

            // ✅ Show ad every 2 clicks
            if (clickCount % 2 == 0 && mInterstitialAd != null) {

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
        });
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
}