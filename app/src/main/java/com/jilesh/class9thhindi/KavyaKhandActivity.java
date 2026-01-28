package com.jilesh.class9thhindi;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class KavyaKhandActivity extends AppCompatActivity {

    ListView listView;

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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kavya_khand);

        listView = findViewById(R.id.listView);

        CustomAdapter adapter = new CustomAdapter(this, lessons);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

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

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("html", htmlFiles[position]);
            startActivity(intent);
        });

    }
}