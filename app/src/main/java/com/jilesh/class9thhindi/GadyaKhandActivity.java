package com.jilesh.class9thhindi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class GadyaKhandActivity extends AppCompatActivity {

    ListView listView;

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

        CustomAdapter adapter = new CustomAdapter(this, lessons);
        listView.setAdapter(adapter);

        // 🔥 Item Click Listener
        listView.setOnItemClickListener((parent, view, position, id) -> {

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

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("html", htmlFiles[position]);
            startActivity(intent);
        });
    }
}
