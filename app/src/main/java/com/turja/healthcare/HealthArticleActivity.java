package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticleActivity extends AppCompatActivity {

    private String[][] health_details = {
            {"Walking Daily", "", "", "", "Click more details"},
            {"Home Care of COVID-19", "", "", "", "Click more details"},
            {"Stop Smoking", "", "", "", "Click more details"},
            {"Menstrual Hygiene", "", "", "", "Click more details"},
            {"Healthy Heart", "", "", "", "Click more details"}
    };

    private int[] image = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };

    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);

        lst = findViewById(R.id.ListViewHA);
        btnBack = findViewById(R.id.buttonHAGoBack);

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(HealthArticleActivity.this, HomeActivity.class));
        });

        list = new ArrayList<>();
        for (int i = 0; i < health_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(HealthArticleActivity.this, HealthArticleDetailsActivity.class);
            it.putExtra("title", health_details[i][0]);
            it.putExtra("image", image[i]);
            startActivity(it);
        });
    }
}
