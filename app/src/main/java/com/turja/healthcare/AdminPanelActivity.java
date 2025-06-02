package com.turja.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AdminPanelActivity extends AppCompatActivity {

    EditText nameInput, addressInput, phoneInput;
    Button submitButton;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        nameInput = findViewById(R.id.editTextName);
        addressInput = findViewById(R.id.editTextAddress);
        phoneInput = findViewById(R.id.editTextPhone);
        submitButton = findViewById(R.id.buttonSubmit);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        submitButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create DoctorModel with basic info
            String doctorId = db.collection("doctors").document().getId();
            UserModel doctor = new UserModel(doctorId, name, address, phone);

            // Save to Firestore
            db.collection("doctors").document(doctorId)
                    .set(doctor)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(AdminPanelActivity.this, "Basic info saved", Toast.LENGTH_SHORT).show();
                        // Pass doctorId to AddDoctorActivity
                        Intent intent = new Intent(AdminPanelActivity.this, AddDoctorActivity.class);
                        intent.putExtra("doctorId", doctorId);
                        intent.putExtra("doctorName", name);
                        intent.putExtra("doctorPhone", phone);
                        startActivity(intent);
                        // Optionally keep AdminPanelActivity in back stack
                        // finish(); // Uncomment if you want to close AdminPanelActivity
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AdminPanelActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}