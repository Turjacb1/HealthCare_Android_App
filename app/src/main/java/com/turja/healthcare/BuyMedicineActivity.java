//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class BuyMedicineActivity extends AppCompatActivity {
//
//    public String[][] packages = {
//            {"Uprise-03 1000IU Capsule", "", "", "", "50"},
//            {"Dolo 650 Tablet", "", "", "", "30"},
//            {"Crocin Advance Tablet", "", "", "", "25"},
//            {"Saridon Tablet", "", "", "", "20"},
//            {"Disprin Tablet", "", "", "", "15"},
//            {"Ciplox 500 Tablet", "", "", "", "60"},
//            {"Amoxicillin 250mg Capsule", "", "", "", "40"},
//            {"Zincovit Tablet", "", "", "", "35"}
//    };
//
//    public String[] package_details = {
//            "Helps in building and keeping the bones and teeth strong. Vitamin D3 supplement.",
//            "Used to reduce fever and relieve mild to moderate pain including headache, muscle ache.",
//            "Paracetamol-based pain relief for fever and body ache with fast absorption formula.",
//            "Provides quick relief from headache, toothache, and body pain.",
//            "Used to treat pain and fever, especially effective for mild migraines.",
//            "Antibiotic used to treat bacterial infections like UTI, respiratory and skin infections.",
//            "Common antibiotic for bacterial infections. Prescribed for throat, chest and skin infections.",
//            "Multivitamin supplement that helps in boosting immunity and general health."
//    };
//
//HashMap<String,String>item;
//ArrayList  list;
//SimpleAdapter sa;
//ListView lst;
//Button btnBack,btnGoToCart,btnAddmedicine;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_buy_medicine);
//
//        lst=findViewById(R.id.ListViewBM);
//        btnBack=findViewById(R.id.buttonBMGoBack);
//        btnGoToCart=findViewById(R.id.buttonBMgoToCart);
//        btnAddmedicine=findViewById((R.id.buttonAddMedicine));
//
//        btnGoToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
//            }
//        });
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
//            }
//        });
//
//        list = new ArrayList<>();
//        for (int i = 0; i < packages.length; i++) {
//            HashMap<String, String> item = new HashMap<>();
//            item.put("line1", packages[i][0]); // Package name
//            item.put("line2", packages[i][1]);
//            item.put("line3", packages[i][2]);
//            item.put("line4", packages[i][3]);
//            item.put("line5", "Total Cost: " + packages[i][4] + "/-");
//            list.add(item);
//        }
//
//        sa = new SimpleAdapter(this, list,
//                R.layout.multi_lines,
//                new String[]{"line1", "line2", "line3", "line4", "line5"},
//                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
//
//        lst.setAdapter(sa);
//
//        lst.setOnItemClickListener((adapterView, view, i, l) -> {
//            Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
//            it.putExtra("title", packages[i][0]);
//            it.putExtra("details", package_details[i]);
//            it.putExtra("price", packages[i][4]);
//            startActivity(it);
//        });
//
//        btnAddmedicine.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this, AdminPanelActivity.class)));
//
//    }
//}
//
//
//



package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    public String[][] packages = {
            {"Uprise-03 1000IU Capsule", "", "", "", "50"},
            {"Dolo 650 Tablet", "", "", "", "30"},
            {"Crocin Advance Tablet", "", "", "", "25"},
            {"Saridon Tablet", "", "", "", "20"},
            {"Disprin Tablet", "", "", "", "15"},
            {"Ciplox 500 Tablet", "", "", "", "60"},
            {"Amoxicillin 250mg Capsule", "", "", "", "40"},
            {"Zincovit Tablet", "", "", "", "35"}
    };

    public String[] package_details = {
            "Helps in building and keeping the bones and teeth strong. Vitamin D3 supplement.",
            "Used to reduce fever and relieve mild to moderate pain including headache, muscle ache.",
            "Paracetamol-based pain relief for fever and body ache with fast absorption formula.",
            "Provides quick relief from headache, toothache, and body pain.",
            "Used to treat pain and fever, especially effective for mild migraines.",
            "Antibiotic used to treat bacterial infections like UTI, respiratory and skin infections.",
            "Common antibiotic for bacterial infections. Prescribed for throat, chest and skin infections.",
            "Multivitamin supplement that helps in boosting immunity and general health."
    };

    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart, btnAddMedicine;
    FirebaseFirestore db;
    private static final String TAG = "BuyMedicineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.ListViewBM);
        btnBack = findViewById(R.id.buttonBMGoBack);
        btnGoToCart = findViewById(R.id.buttonBMgoToCart);
        btnAddMedicine = findViewById(R.id.buttonAddMedicine);

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();

        // Upload hardcoded medicines to Firestore
        uploadHardcodedMedicines();

        // Fetch medicines from Firestore
        fetchMedicines();

        btnGoToCart.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class)));

        btnBack.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class)));

        btnAddMedicine.setOnClickListener(v -> {
            Intent intent = new Intent(BuyMedicineActivity.this, MedicineAdminPanelActivity.class);
            intent.putExtra("redirect_to", "AddMedicineActivity");
            startActivity(intent);
        });
    }

    private void uploadHardcodedMedicines() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        boolean medicinesUploaded = sharedPreferences.getBoolean("medicines_uploaded", false);

        if (medicinesUploaded) {
            Log.d(TAG, "Hardcoded medicines already uploaded, skipping...");
            return;
        }

        for (int i = 0; i < packages.length; i++) {
            HashMap<String, String> medicineData = new HashMap<>();
            medicineData.put("name", packages[i][0]);
            medicineData.put("details", package_details[i]);
            medicineData.put("price", packages[i][4]);

            // Check if medicine already exists
            db.collection("medicines")
                    .whereEqualTo("name", packages[i][0])
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (queryDocumentSnapshots.isEmpty()) {
                            db.collection("medicines")
                                    .add(medicineData)
                                    .addOnSuccessListener(docRef -> Log.d(TAG, "Medicine added: " + medicineData.get("name")))
                                    .addOnFailureListener(e -> Log.w(TAG, "Error adding medicine: " + medicineData.get("name"), e));
                        } else {
                            Log.d(TAG, "Medicine already exists: " + medicineData.get("name"));
                        }
                    })
                    .addOnFailureListener(e -> Log.w(TAG, "Error checking for existing medicine: " + medicineData.get("name"), e));
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("medicines_uploaded", true);
        editor.apply();
    }

    private void fetchMedicines() {
        list.clear();
        db.collection("medicines")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.isEmpty()) {
                        Toast.makeText(this, "No medicines found", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        HashMap<String, String> item = new HashMap<>();
                        item.put("line1", document.getString("name"));
                        item.put("line2", "");
                        item.put("line3", "");
                        item.put("line4", "");
                        item.put("line5", "Total Cost: " + document.getString("price") + "/-");
                        item.put("details", document.getString("details"));
                        list.add(item);
                    }

                    sa = new SimpleAdapter(this, list,
                            R.layout.multi_lines,
                            new String[]{"line1", "line2", "line3", "line4", "line5"},
                            new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
                    lst.setAdapter(sa);

                    lst.setOnItemClickListener((adapterView, view, i, l) -> {
                        Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                        it.putExtra("title", list.get(i).get("line1"));
                        it.putExtra("details", list.get(i).get("details"));
                        it.putExtra("price", list.get(i).get("line5").replace("Total Cost: ", "").replace("/-", ""));
                        startActivity(it);
                    });
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error fetching medicines", e);
                    Toast.makeText(this, "Failed to load medicines", Toast.LENGTH_SHORT).show();
                });
    }
}