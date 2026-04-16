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

public class GadyaKhandActivity extends AppCompatActivity {

    ListView listView;

    // 🔥 Interstitial Ad
    private InterstitialAd mInterstitialAd;
    private int clickCount = 0;

    String[] lessons = {
            "पाठ 1 बात – पं० प्रतापनारायण मिश्र",
            "पाठ 2 मंत्र – मुंशी प्रेमचन्द",
            "पाठ 3 गुरु नानकदेव – डॉ0 हजारीप्रसाद द्विवेदी",
            "पाठ 4 गिल्लू – महादेवी वर्मा",
            "पाठ 5 स्मृति – श्रीराम शर्मा",
            "पाठ 6 निष्ठामूर्ति कस्तूरबा – काका कालेलकर",
            "पाठ 7 ठेले पर हिमालय – डॉ0 धर्मवीर भारती",
            "पाठ 8 तोता – रवीन्द्र नाथ टैगोर",
            "पाठ 9 सड़क सुरक्षा एवं यातायात के नियम"
    };

    String[] htmlFiles = {
            "gy1.html",
            "gy2.html",
            "gy3.html",
            "gy4.html",
            "gy5.html",
            "gy6.html",
            "gy7.html",
            "gy8.html",
            "gy9.html"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadya_khand);

        listView = findViewById(R.id.listView);

        // ✅ Adapter
        CustomAdapter adapter = new CustomAdapter(this, lessons, "gadya");
        listView.setAdapter(adapter);

        // 🔥 Load Ad
        loadInterstitial();

        // 🔥 Click Listener (WITH ADS)
        listView.setOnItemClickListener((parent, view, position, id) -> {

            clickCount++;

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("html", htmlFiles[position]);
            intent.putExtra("bg", "gadya");

            // ✅ Show ad every 2 clicks
            if (clickCount % 2 == 0 && mInterstitialAd != null) {

                mInterstitialAd.show(this);

                mInterstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                startActivity(intent);
                                loadInterstitial(); // reload next ad
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