package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;

public class AddDoctorActivity extends AppCompatActivity {
    EditText edtName, edtHospital, edtExp, edtMobile, edtFee;
    Spinner spinnerCategory;
    Button btnSave;
    FirebaseFirestore db;
    private static final String TAG = "AddDoctorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        edtName = findViewById(R.id.editTextName);
        edtHospital = findViewById(R.id.editTextHospital);
        edtExp = findViewById(R.id.editTextExp);
        edtMobile = findViewById(R.id.editTextMobile);
        edtFee = findViewById(R.id.editTextFee);
        btnSave = findViewById(R.id.buttonSave);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        db = FirebaseFirestore.getInstance();

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String hospital = edtHospital.getText().toString().trim();
            String exp = edtExp.getText().toString().trim();
            String mobile = edtMobile.getText().toString().trim();
            String fee = edtFee.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(hospital) ||
                    TextUtils.isEmpty(exp) || TextUtils.isEmpty(mobile) ||
                    TextUtils.isEmpty(fee) || TextUtils.isEmpty(category)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            HashMap<String, String> doctorData = new HashMap<>();
            doctorData.put("name", "Doctor Name : " + name);
            doctorData.put("hospital", "Hospital Name : " + hospital);
            doctorData.put("exp", "Exp : " + exp + " Years");
            doctorData.put("mobile", "Mobile No : " + mobile);
            doctorData.put("fee", fee);
            doctorData.put("category", category); // 🔑 Save the category

            db.collection("doctors")
                    .add(doctorData)
                    .addOnSuccessListener(docRef -> {
                        Log.d(TAG, "Doctor added with ID: " + docRef.getId());
                        Toast.makeText(this, "Doctor added successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding doctor", e);
                        Toast.makeText(this, "Failed to add doctor", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
