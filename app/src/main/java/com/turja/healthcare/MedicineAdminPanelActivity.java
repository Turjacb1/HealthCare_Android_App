package com.turja.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MedicineAdminPanelActivity extends AppCompatActivity {

    EditText NameInput, addressInput, phoneInput;
    Button submitButton;
    FirebaseFirestore db;
    private static final String TAG = "AdminPanelActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        // Initialize Medicine input fields
        NameInput = findViewById(R.id.editTextName);
        addressInput = findViewById(R.id.editTextAddress);
        phoneInput = findViewById(R.id.editTextPhone);
        submitButton = findViewById(R.id.buttonSubmit);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Check intent for redirect
        Intent intent = getIntent();
        String redirectTo = intent.getStringExtra("redirect_to");

        submitButton.setOnClickListener(v -> {
            String name = NameInput.getText().toString().trim();
            String details = addressInput.getText().toString().trim();
            String price = phoneInput.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(details) || TextUtils.isEmpty(price)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create HashMap for medicine data
            HashMap<String, String> medicineData = new HashMap<>();
            medicineData.put("name", name);
            medicineData.put("details", details);
            medicineData.put("price", price);

            // Save to Firestore
            db.collection("medicines")
                    .add(medicineData)
                    .addOnSuccessListener(docRef -> {
                        Log.d(TAG, "Medicine added with ID: " + docRef.getId());
                        Toast.makeText(MedicineAdminPanelActivity.this, "Medicine info saved", Toast.LENGTH_SHORT).show();
                        // Navigate to AddMedicineActivity if redirected from BuyMedicineActivity
                        if ("AddMedicineActivity".equals(redirectTo)) {
                            Intent addMedicineIntent = new Intent(MedicineAdminPanelActivity.this, AddMedicineActivity.class);
                            addMedicineIntent.putExtra("medicineId", docRef.getId());
                            addMedicineIntent.putExtra("medicineName", name);
                            addMedicineIntent.putExtra("medicineDetails", details);
                            addMedicineIntent.putExtra("medicinePrice", price);
                            startActivity(addMedicineIntent);
                        } else {
                            finish(); // Close AdminPanelActivity if no further redirection
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding medicine", e);
                        Toast.makeText(MedicineAdminPanelActivity.this, "Failed to save medicine: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}