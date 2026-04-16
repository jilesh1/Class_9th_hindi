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

public class KavyaKhandActivity extends AppCompatActivity {

    ListView listView;

    // 🔥 Interstitial
    private InterstitialAd mInterstitialAd;
    private int clickCount = 0;

    String[] lessons = {
            "पाठ 1 कबीर",
            "पाठ 2 संत रैदास",
            "पाठ 3 मीराबाई",
            "पाठ 4 रहीम",
            "पाठ 5 भारतेन्दु हरिश्चन्द्र",
            "पाठ 6 मैथिलीशरण गुप्त",
            "पाठ 7 जयशंकर प्रसाद",
            "पाठ 8 सूर्यकान्त त्रिपाठी “निराला”",
            "पाठ 9 सोहनलाल द्विवेदी",
            "पाठ 10 हरिवंशराय बच्चन",
            "पाठ 11 नागार्जुन",
            "पाठ 12 केदारनाथ अग्रवाल",
            "पाठ 13 शिवमंगल सिंह ‘सुमन‘"
    };

    String[] htmlFiles = {
            "ky1.html",
            "ky2.html",
            "ky3.html",
            "ky4.html",
            "ky5.html",
            "ky6.html",
            "ky7.html",
            "ky8.html",
            "ky9.html",
            "ky10.html",
            "ky11.html",
            "ky12.html",
            "ky13.html"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kavya_khand);

        listView = findViewById(R.id.listView);

        // ✅ Adapter
        CustomAdapter adapter = new CustomAdapter(this, lessons, "kavya");
        listView.setAdapter(adapter);

        // 🔥 Load Ad
        loadInterstitial();

        // 🔥 Click with Ads
        listView.setOnItemClickListener((parent, view, position, id) -> {

            clickCount++;

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("html", htmlFiles[position]);
            intent.putExtra("bg", "kavya");

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