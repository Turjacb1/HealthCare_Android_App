package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
    };

    // Reusing the same data for other categories for now
    private String[][] doctor_details2 = {
            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
    };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonLTBack);
        ListView lst = findViewById(R.id.ListViewDD);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        // Select doctor details based on title
        if (title.equals("Family Physician")) {
            doctor_details = doctor_details1;
        } else if (title.equals("Dietician")) {
            doctor_details = doctor_details2;
        } else if (title.equals("Dentist")) {
            doctor_details = doctor_details3;
        } else if (title.equals("Surgeon")) {
            doctor_details = doctor_details4;
        } else if (title.equals("Cardiologists")) {
            doctor_details = doctor_details5;
        }

        // Populate the list
        list = new ArrayList<>();
        for (int i = 0; i < doctor_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fee : " + doctor_details[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        // ✅ Correct Listener to detect item click
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("title", title);
                it.putExtra("line1", doctor_details[i][0]);
                it.putExtra("line2", doctor_details[i][1]);
                it.putExtra("line3", doctor_details[i][2]);
                it.putExtra("line4", doctor_details[i][3]);
                it.putExtra("line5", doctor_details[i][4]);
                startActivity(it);
            }
        });

        // ✅ Enable back button
        btn.setOnClickListener(view -> {
            finish(); // Go back to previous activity
        });
    }
}
