package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LabTestActivity extends AppCompatActivity {
    private  String[][] packges={
            {"Package 1 :Full Boy Checkup","","","","999"},
            {"Package 2 :Blood Glucose","","","","999"},
            {"Package 3 :Covid-19","","","","999"},
            {"Package 4 :Thyroid Check","","","","999"},
            {"Package 5 :immunity Check","","","","999"}

    };

    private String[] packges_details={
            "Blood Glucose\n"+
                    "Complete Hemogram\n" +
                    "HbAic\n"+
                    "iron Studies\n"+
                    "Kidney Function\n"+
                    "LDH Labacate,serum\n"+
                    "Lipid Profile"+
                    "Liver Function Test"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
    }
}