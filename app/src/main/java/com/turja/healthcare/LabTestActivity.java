//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class LabTestActivity extends AppCompatActivity {
//
//    private String[][] packages = {
//            {"Package 1: Full Body Checkup", "", "", "", "999"},
//            {"Package 2: Blood Glucose", "", "", "", "499"},
//            {"Package 3: Covid-19", "", "", "", "699"},
//            {"Package 4: Thyroid Check", "", "", "", "799"},
//            {"Package 5: Immunity Check", "", "", "", "899"}
//    };
//
//    private String[] packageDetails = {
//            "Blood Glucose" +
//                    "Complete Hemogram" +
//                    "HbA1c\n" +
//                    "Iron Studies\n" +
//                    "Kidney Function\n" +
//                    "LDH Lactate, Serum\n" +
//                    "Lipid Profile\n" +
//                    "Liver Function Test",
//            "Blood Glucose Fasting",
//            "COVID-19 Antibody - IgG",
//            "Thyroid Profile - Total (T3, T4 & TSH Ultra-sensitive)",
//            "Complete Hemogram\n" +
//                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
//                    "Iron Studies\n" +
//                    "Kidney Function Test\n" +
//                    "Vitamin D Total - 25 Hydroxy\n" +
//                    "Liver Function Test\n" +
//                    "Lipid Profile"
//    };
//
//    Button btnGoToCart, btnBack;
//    ListView listView;
//    ArrayList<HashMap<String, String>> list;
//    SimpleAdapter sa;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_test);
//
//        btnGoToCart = findViewById(R.id.buttonAddToCart);
//        btnBack = findViewById(R.id.buttonGoBack);
//        listView = findViewById(R.id.ListViewLabTest);
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
//            }
//        });
//
//        list = new ArrayList<>();
//        for (int i = 0; i < packages.length; i++) {
//            HashMap<String, String> item = new HashMap<>();
//            item.put("line1", packages[i][0]);
//            item.put("line2", packages[i][1]);
//            item.put("line3", packages[i][2]);
//            item.put("line4", packages[i][3]);
//            item.put("line5", "Total Cost : " + packages[i][4] + "/-");
//            list.add(item);
//        }
//
//        sa = new SimpleAdapter(this, list,
//                R.layout.multi_lines,
//                new String[]{"line1", "line2", "line3", "line4", "line5"},
//                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
//        listView.setAdapter(sa);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent it = new Intent(LabTestActivity.this, LabDetailsActivity.class);
//                it.putExtra("title", packages[i][0]);
//                it.putExtra("details", packageDetails[i]);
//                it.putExtra("price", packages[i][4]);
//                startActivity(it);
//            }
//        });
//
//        btnGoToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
//            }
//        });
//    }
//}



package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Package 1: Full Body Checkup", "", "", "", "999"},
            {"Package 2: Blood Glucose", "", "", "", "499"},
            {"Package 3: Covid-19", "", "", "", "699"},
            {"Package 4: Thyroid Check", "", "", "", "799"},
            {"Package 5: Immunity Check", "", "", "", "899"}
    };

    private String[] packageDetails = {
            "Blood Glucose\nComplete Hemogram\nHbA1c\nIron Studies\nKidney Function\nLDH Lactate, Serum\nLipid Profile\nLiver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile - Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\nCRP (C Reactive Protein) Quantitative, Serum\nIron Studies\nKidney Function Test\nVitamin D Total - 25 Hydroxy\nLiver Function Test\nLipid Profile"
    };

    Button btnGoToCart, btnBack;
    ListView listView;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.buttonAddToCart);
        btnBack = findViewById(R.id.buttonGoBack);
        listView = findViewById(R.id.ListViewLabTest);

        btnBack.setOnClickListener(v -> startActivity(new Intent(LabTestActivity.this, HomeActivity.class)));

        list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", packages[i][0]); // Package name
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: " + packages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(LabTestActivity.this, LabDetailsActivity.class);
            it.putExtra("title", packages[i][0]);
            it.putExtra("details", packageDetails[i]);
            it.putExtra("price", packages[i][4]);
            startActivity(it);
        });

        btnGoToCart.setOnClickListener(v -> startActivity(new Intent(LabTestActivity.this, CartLabActivity.class)));
    }
}