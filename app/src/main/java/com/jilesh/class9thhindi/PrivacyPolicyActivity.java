package com.jilesh.class9thhindi;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_privacy_policy);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();

        });

        WebView webView = findViewById(R.id.webView);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });

        // Load the built-in HTML privacy policy
        webView.loadDataWithBaseURL(null, getPrivacyPolicyHtml(),
                "text/html", "UTF-8", null);
    }

    private String getPrivacyPolicyHtml(){
        return "<!DOCTYPE html><html><head>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<style>"
                + "body { font-family: Arial; background:#0D1B2A; color:#ECEFF1; padding:20px; line-height:1.7; }"
                + "h1 { color:#64B5F6; }"
                + "h2 { color:#81C784; margin-top:20px; }"
                + "p { color:#B0BEC5; }"
                + ".card { background:#1A1A2E; padding:16px; border-radius:12px; margin-bottom:15px; }"
                + "a { color:#64B5F6; }"
                + "</style></head><body>"

                + "<h1>📚 Class 9 Hindi — Privacy Policy</h1>"
                + "<p>Last updated: 2026</p>"

                + "<div class='card'>"
                + "<h2>1. Introduction</h2>"
                + "<p>This app provides Class 9 Hindi NCERT study material including prose, poetry, and Sanskrit sections. "
                + "We respect your privacy and are committed to protecting it.</p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>2. Data Collection</h2>"
                + "<p>We do <strong>not collect any personal information</strong> directly from users.</p>"
                + "<p>The app works offline and does not require user login.</p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>3. Advertising (AdMob)</h2>"
                + "<p>This app uses <strong>Google AdMob</strong> to show ads.</p>"
                + "<p>AdMob may collect:</p>"
                + "<p>• Device information<br>"
                + "• Advertising ID<br>"
                + "• IP address<br>"
                + "• Interaction with ads</p>"
                + "<p>These are used to show relevant ads.</p>"
                + "<p>Google Privacy Policy: "
                + "<a href='https://policies.google.com/privacy'>https://policies.google.com/privacy</a></p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>4. Children's Privacy</h2>"
                + "<p>This app is educational and suitable for students. "
                + "We do not knowingly collect personal data from children.</p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>5. Third-Party Services</h2>"
                + "<p>We use:</p>"
                + "<p>• Google AdMob (Ads)<br>"
                + "• Google Play Services</p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>6. Data Security</h2>"
                + "<p>We do not store or share user data. All app usage is local to your device.</p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>7. Changes</h2>"
                + "<p>We may update this policy. Changes will be reflected in the app.</p>"
                + "</div>"

                + "<div class='card'>"
                + "<h2>8. Contact</h2>"
                + "<p>Email: <strong>jileshdev@gmail.com</strong></p>"
                + "</div>"

                + "</body></html>";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
