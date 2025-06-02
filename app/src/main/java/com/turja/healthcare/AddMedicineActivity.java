
package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddMedicineActivity extends AppCompatActivity {
    EditText edtName, edtDetails, edtPrice;
    Button btnSave;
    FirebaseFirestore db;
    private static final String TAG = "AddMedicineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        // Initialize UI components
        edtName = findViewById(R.id.editTextName);
        edtDetails = findViewById(R.id.editTextDetails);
        edtPrice = findViewById(R.id.editTextPrice);
        btnSave = findViewById(R.id.buttonSave);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Pre-fill fields with data from AdminPanelActivity
        Intent intent = getIntent();
        String medicineId = intent.getStringExtra("medicineId");
        String medicineName = intent.getStringExtra("medicineName");
        String medicineDetails = intent.getStringExtra("medicineDetails");
        String medicinePrice = intent.getStringExtra("medicinePrice");

        if (medicineName != null) edtName.setText(medicineName);
        if (medicineDetails != null) edtDetails.setText(medicineDetails);
        if (medicinePrice != null) edtPrice.setText(medicinePrice);

        // Save button click listener
        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String details = edtDetails.getText().toString().trim();
            String price = edtPrice.getText().toString().trim();

            // Validate input fields
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(details) || TextUtils.isEmpty(price)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a HashMap for medicine data
            HashMap<String, String> medicineData = new HashMap<>();
            medicineData.put("name", name);
            medicineData.put("details", details);
            medicineData.put("price", price);

            // Update or add to Firestore
            if (medicineId != null) {
                db.collection("medicines").document(medicineId)
                        .set(medicineData)
                        .addOnSuccessListener(docRef -> {
                            Log.d(TAG, "Medicine updated with ID: " + medicineId);
                            Toast.makeText(this, "Medicine updated successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Log.w(TAG, "Error updating medicine", e);
                            Toast.makeText(this, "Failed to update medicine", Toast.LENGTH_SHORT).show();
                        });
            } else {
                db.collection("medicines")
                        .add(medicineData)
                        .addOnSuccessListener(docRef -> {
                            Log.d(TAG, "Medicine added with ID: " + docRef.getId());
                            Toast.makeText(this, "Medicine added successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Log.w(TAG, "Error adding medicine", e);
                            Toast.makeText(this, "Failed to add medicine", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}