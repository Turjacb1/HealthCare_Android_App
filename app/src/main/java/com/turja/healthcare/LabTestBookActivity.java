//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class LabTestBookActivity extends AppCompatActivity {
//    EditText edname, edaddress, edcontact, edpincode;
//    Button btnBooking;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_test_book);
//
//        // Initialize UI components
//        edname = findViewById(R.id.editTextTBFullName);
//        edaddress = findViewById(R.id.editTextTBAddress);
//        edcontact = findViewById(R.id.editTextTBcontactNumber);
//        edpincode = findViewById(R.id.editTextTBPinnumber);
//        btnBooking = findViewById(R.id.buttonTBBooking); // Ensure this ID exists in your layout
//
//        // Get intent data
//        Intent intent = getIntent();
//        String priceStr = intent.getStringExtra("price");
//        String date = intent.getStringExtra("date");
//        String time = intent.getStringExtra("time");
//
//        // Log the price string for debugging
//        Log.d("LabTestBookActivity", "Received price: " + priceStr);
//
//        // Parse price safely
//        float price = 0.0f;
//        if (priceStr != null && !priceStr.isEmpty()) {
//            try {
//                // Remove any non-numeric characters (e.g., "$", "Price:", etc.)
//                String cleanedPrice = priceStr.replaceAll("[^0-9.]", "");
//                if (!cleanedPrice.isEmpty()) {
//                    price = Float.parseFloat(cleanedPrice);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Price is empty after cleaning", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            } catch (NumberFormatException e) {
//                Log.e("LabTestBookActivity", "Price parsing error: " + e.getMessage());
//                Toast.makeText(getApplicationContext(), "Invalid price format: " + priceStr, Toast.LENGTH_SHORT).show();
//                return;
//            }
//        } else {
//            Log.e("LabTestBookActivity", "Price not provided in Intent");
//            Toast.makeText(getApplicationContext(), "Price not provided", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Store price in a final variable for use in the OnClickListener
//        final float finalPrice = price;
//
//        // Handle button click
//        btnBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Validate input fields
//                String fullName = edname.getText().toString().trim();
//                String address = edaddress.getText().toString().trim();
//                String contact = edcontact.getText().toString().trim();
//                String pincodeStr = edpincode.getText().toString().trim();
//
//                if (fullName.isEmpty() || address.isEmpty() || contact.isEmpty() || pincodeStr.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Parse pincode
//                int pincode;
//                try {
//                    pincode = Integer.parseInt(pincodeStr);
//                } catch (NumberFormatException e) {
//                    Toast.makeText(getApplicationContext(), "Invalid pincode format", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Get username from SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                String username = sharedPreferences.getString("username", "");
//
//                if (username.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "User not logged in", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Save order to database
//                try {
//                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
//                    db.addOrder(username, fullName, address, contact, pincode, date, time, finalPrice, "labtest");
//                    db.removeCart(username, "labtest"); // Ensure otype matches cart table
//                    Toast.makeText(getApplicationContext(), "Your booking was completed successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
//                    finish(); // Close current activity
//                } catch (Exception e) {
//                    Log.e("LabTestBookActivity", "Database error: " + e.getMessage());
//                    Toast.makeText(getApplicationContext(), "Error saving order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//


        package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        // Initialize UI components
        edname = findViewById(R.id.editTextTBFullName);
        edaddress = findViewById(R.id.editTextTBAddress);
        edcontact = findViewById(R.id.editTextTBcontactNumber);
        edpincode = findViewById(R.id.editTextTBPinnumber);
        btnBooking = findViewById(R.id.buttonTBBooking);

        if (btnBooking == null) {
            Log.e("LabTestBookActivity", "Button not found in layout");
            Toast.makeText(getApplicationContext(), "Button not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Get intent data
        Intent intent = getIntent();
        String priceStr = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        Log.d("LabTestBookActivity", "Received: price=" + priceStr + ", date=" + date + ", time=" + time);

        // Validate intent data
        if (priceStr == null || priceStr.isEmpty() || date == null || date.isEmpty() || time == null || time.isEmpty()) {
            Log.e("LabTestBookActivity", "Missing intent extras: price=" + priceStr + ", date=" + date + ", time=" + time);
            Toast.makeText(getApplicationContext(), "Missing booking details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Parse price
        float price = 0.0f;
        try {
            String cleanedPrice = priceStr.replaceAll("[^0-9.]", "");
            if (!cleanedPrice.matches("\\d*\\.?\\d+")) {
                Log.e("LabTestBookActivity", "Invalid cleaned price: " + cleanedPrice);
                Toast.makeText(getApplicationContext(), "Invalid price format", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            price = Float.parseFloat(cleanedPrice);
        } catch (NumberFormatException e) {
            Log.e("LabTestBookActivity", "Price parsing error: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Invalid price format: " + priceStr, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        final float finalPrice = price;

        // Handle button click
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LabTestBookActivity", "Book button clicked");

                // Validate input fields
                String fullName = edname.getText().toString().trim();
                String address = edaddress.getText().toString().trim();
                String contact = edcontact.getText().toString().trim();
                String pincodeStr = edpincode.getText().toString().trim();
                Log.d("LabTestBookActivity", "Inputs: name=" + fullName + ", address=" + address + ", contact=" + contact + ", pincode=" + pincodeStr);

                if (fullName.isEmpty() || address.isEmpty() || contact.isEmpty() || pincodeStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse pincode
                int pincode;
                try {
                    pincode = Integer.parseInt(pincodeStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid pincode format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get username from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                Log.d("LabTestBookActivity", "Username: " + username);

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please log in to book", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestBookActivity.this, LoginActivity.class));
                    finish();
                    return;
                }

                // Save order to database
                try {
                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username, fullName, address, contact, pincode, date, time, finalPrice, "labtest");
                    db.removeCart(username, "lab");
                    db.logOrders(); // Debug: Log all orders
                    Toast.makeText(getApplicationContext(), "Your booking was completed successfully", Toast.LENGTH_SHORT).show();
                    Log.d("LabTestBookActivity", "Redirecting to HomeActivity");
                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                    finish();
                } catch (Exception e) {
                    Log.e("LabTestBookActivity", "Database error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error saving order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
