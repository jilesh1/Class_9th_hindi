package com.jilesh.class9thhindi;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class SanskritKhandActivity extends AppCompatActivity {

    ListView listView;

    String[] lessons = {
            "पाठ 1 वन्दना",
            "पाठ 2 सदाचारः",
            "पाठ 3 पुरुषोत्तमः रामः",
            "पाठ 4 सिद्धिमन्त्रः",
            "पाठ 5 सुभाषितानि",
            "पाठ 6 परमहंसः रामकृष्णः",
            "पाठ 7 कृष्णः गोपालनन्दनः"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanskrit_khand);

        listView = findViewById(R.id.listView);

        CustomAdapter adapter = new CustomAdapter(this, lessons);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String[] htmlFiles = {
                    "sk1.html",
                    "sk2.html",
                    "sk3.html",
                    "sk4.html",
                    "sk5.html",
                    "sk6.html",
                    "sk7.html"
            };

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("html", htmlFiles[position]);
            startActivity(intent);
        });
    }
}