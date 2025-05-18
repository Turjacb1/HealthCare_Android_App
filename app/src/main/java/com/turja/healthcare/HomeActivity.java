package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences=getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "welcome " +username,Toast.LENGTH_SHORT).show();

        //logout
        CardView exit=findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });



        CardView findDoctor=findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });


        CardView labtest=findViewById(R.id.cardLabTest);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });

    }
}