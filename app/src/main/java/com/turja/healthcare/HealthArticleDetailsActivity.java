package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetailsActivity extends AppCompatActivity {

    TextView tv1;
    ImageView img;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);

        tv1 = findViewById(R.id.textViewHADTitle);
        img = findViewById(R.id.imageViewHAD);
        btnBack = findViewById(R.id.buttonHADGoBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int imageResId = intent.getIntExtra("image", -1);

        tv1.setText(title);
        if (imageResId != -1) {
            img.setImageResource(imageResId);
        }

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(HealthArticleDetailsActivity.this, HealthArticleActivity.class));
        });
    }
}
