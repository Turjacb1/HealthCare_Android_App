



//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class DoctorDetailsActivity extends AppCompatActivity {
//
//    private String[][] doctor_details1 = {
//            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//    };
//
//    // Reusing the same data for other categories for now
//    private String[][] doctor_details2 = {
//            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//    };
//    private String[][] doctor_details3 = {
//            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//    };
//    private String[][] doctor_details4 = {
//            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//    };
//    private String[][] doctor_details5 = {
//            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
//    };
//
//    TextView tv;
//    Button btn;
//    Button btnAddDoctor;
//    String[][] doctor_details = {};
//    ArrayList<HashMap<String, String>> list;
//    SimpleAdapter sa;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doctor_details);
//
//        tv = findViewById(R.id.textViewDDTitle);
//        btn = findViewById(R.id.buttonGoBack);
//        ListView lst = findViewById(R.id.ListViewDD);
//        btnAddDoctor = findViewById(R.id.buttonAddDoctor);
//
//        Intent it = getIntent();
//        String title = it.getStringExtra("title");
//        tv.setText(title);
//
//        // Select doctor details based on title
//        if (title.equals("Family Physician")) {
//            doctor_details = doctor_details1;
//        } else if (title.equals("Dietician")) {
//            doctor_details = doctor_details2;
//        } else if (title.equals("Dentist")) {
//            doctor_details = doctor_details3;
//        } else if (title.equals("Surgeon")) {
//            doctor_details = doctor_details4;
//        } else if (title.equals("Cardiologists")) {
//            doctor_details = doctor_details5;
//        }
//
//        // Populate the list
//        list = new ArrayList<>();
//        for (int i = 0; i < doctor_details.length; i++) {
//            HashMap<String, String> item = new HashMap<>();
//            item.put("line1", doctor_details[i][0]);
//            item.put("line2", doctor_details[i][1]);
//            item.put("line3", doctor_details[i][2]);
//            item.put("line4", doctor_details[i][3]);
//            item.put("line5", "Cons Fee : " + doctor_details[i][4] + "/-");
//            list.add(item);
//        }
//
//        sa = new SimpleAdapter(this, list,
//                R.layout.multi_lines,
//                new String[]{"line1", "line2", "line3", "line4", "line5"},
//                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
//        lst.setAdapter(sa);
//
//        // ListView item click listener
//        lst.setOnItemClickListener((parent, view, i, l) -> {
//            Intent intent = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
//            intent.putExtra("title", title);
//            intent.putExtra("line1", doctor_details[i][0]);
//            intent.putExtra("line2", doctor_details[i][1]);
//            intent.putExtra("line3", doctor_details[i][2]);
//            intent.putExtra("line4", doctor_details[i][3]);
//            intent.putExtra("line5", doctor_details[i][4]);
//            startActivity(intent);
//        });
//
//        // Back button click listener
//        btn.setOnClickListener(view -> finish());
//
//        // Add Doctor button click listener
//        btnAddDoctor.setOnClickListener(v -> {
//            // Check if user is logged in
//            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
//            String username = sharedPreferences.getString("username", null);
//            if (username == null) {
//                // Not logged in, redirect to LoginActivity
//                Intent intent = new Intent(DoctorDetailsActivity.this, AdminPanelActivity.class);
//                intent.putExtra("redirect_to", "AddDoctorActivity");
//                startActivity(intent);
//            } else {
//                // Already logged in, go directly to AddDoctorActivity
//                startActivity(new Intent(DoctorDetailsActivity.this, AdminPanelActivity.class));
//            }
//        });
//
//
//
//
//    }
//}



package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DoctorDetailsActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    Button btnAddDoctor;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    FirebaseFirestore db;
    private static final String TAG = "DoctorDetailsActivity";

    // Hardcoded doctor data
    private String[][] doctor_details1 = {
            {"Doctor Name : Devi Shethi", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Pran Gopal", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : Kaji", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : A", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
            {"Doctor Name : B", "Hospital Name : PG", "Exp : 40 Years", "Mobile No : 01010101010", "800"},
    };

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonGoBack);
        ListView lst = findViewById(R.id.ListViewDD);
        btnAddDoctor = findViewById(R.id.buttonAddDoctor);

        db = FirebaseFirestore.getInstance();

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        // Initialize the list for SimpleAdapter
        list = new ArrayList<>();

        // Upload hardcoded data to Firestore (only if not already uploaded)
        uploadHardcodedDoctors();

        // Fetch doctors from Firestore based on category
        db.collection("doctors")
                .whereEqualTo("category", title)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.isEmpty()) {
                        Toast.makeText(this, "No doctors found for " + title, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        HashMap<String, String> item = new HashMap<>();
                        item.put("line1", document.getString("name"));
                        item.put("line2", document.getString("hospital"));
                        item.put("line3", document.getString("exp"));
                        item.put("line4", document.getString("mobile"));
                        item.put("line5", "Cons Fee : " + document.getString("fee") + "/-");
                        list.add(item);
                    }

                    // Set up SimpleAdapter
                    sa = new SimpleAdapter(this, list,
                            R.layout.multi_lines,
                            new String[]{"line1", "line2", "line3", "line4", "line5"},
                            new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
                    lst.setAdapter(sa);

                    // ListView item click listener
                    lst.setOnItemClickListener((parent, view, i, l) -> {
                        Intent intent = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("line1", list.get(i).get("line1"));
                        intent.putExtra("line2", list.get(i).get("line2"));
                        intent.putExtra("line3", list.get(i).get("line3"));
                        intent.putExtra("line4", list.get(i).get("line4"));
                        intent.putExtra("line5", list.get(i).get("line5").replace("Cons Fee : ", "").replace("/-", ""));
                        startActivity(intent);
                    });
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error fetching doctors", e);
                    Toast.makeText(this, "Failed to load doctors", Toast.LENGTH_SHORT).show();
                });

        // Back button click listener
        btn.setOnClickListener(view -> finish());

        // Add Doctor button click listener
        btnAddDoctor.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
            String username = sharedPreferences.getString("username", null);
            if (username == null) {
                // Not logged in, redirect to AdminPanelActivity
                Intent intent = new Intent(DoctorDetailsActivity.this, AdminPanelActivity.class);
                intent.putExtra("redirect_to", "AddDoctorActivity");
                startActivity(intent);
            } else {
                // Already logged in, go directly to AddDoctorActivity
                Intent intent = new Intent(DoctorDetailsActivity.this, AddDoctorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void uploadHardcodedDoctors() {
        // Map arrays to categories
        String[] categories = {"Family Physician", "Dietician", "Dentist", "Surgeon", "Cardiologists"};
        String[][][] doctorDetails = {doctor_details1, doctor_details2, doctor_details3, doctor_details4, doctor_details5};

        // Use SharedPreferences to ensure data is uploaded only once
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        boolean dataUploaded = sharedPreferences.getBoolean("doctors_uploaded", false);

        if (dataUploaded) {
            Log.d(TAG, "Hardcoded doctors already uploaded, skipping...");
            return;
        }

        // Iterate through each category and its doctors
        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            String[][] doctors = doctorDetails[i];

            for (String[] doctor : doctors) {
                HashMap<String, String> doctorData = new HashMap<>();
                doctorData.put("name", doctor[0]); // e.g., "Doctor Name : Devi Shethi"
                doctorData.put("hospital", doctor[1]); // e.g., "Hospital Name : PG"
                doctorData.put("exp", doctor[2]); // e.g., "Exp : 40 Years"
                doctorData.put("mobile", doctor[3]); // e.g., "Mobile No : 01010101010"
                doctorData.put("fee", doctor[4]); // e.g., "800"
                doctorData.put("category", category); // e.g., "Family Physician"

                // Check if doctor already exists to avoid duplicates
                db.collection("doctors")
                        .whereEqualTo("name", doctor[0])
                        .whereEqualTo("category", category)
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            if (queryDocumentSnapshots.isEmpty()) {
                                // Doctor doesn't exist, add to Firestore
                                db.collection("doctors")
                                        .add(doctorData)
                                        .addOnSuccessListener(docRef -> Log.d(TAG, "Doctor added: " + doctor[0]))
                                        .addOnFailureListener(e -> Log.w(TAG, "Error adding doctor: " + doctor[0], e));
                            } else {
                                Log.d(TAG, "Doctor already exists: " + doctor[0]);
                            }
                        })
                        .addOnFailureListener(e -> Log.w(TAG, "Error checking for existing doctor: " + doctor[0], e));
            }
        }

        // Mark data as uploaded
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("doctors_uploaded", true);
        editor.apply();
    }
}