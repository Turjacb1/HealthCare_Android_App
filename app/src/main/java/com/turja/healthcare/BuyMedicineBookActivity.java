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

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBcontactNumber);
        edpincode = findViewById(R.id.editTextBMBPinnumber);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        if (btnBooking == null) {
            Log.e("BuyMedicineBookActivity", "Button not found in layout");
            Toast.makeText(getApplicationContext(), "Button not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Get intent data
        Intent intent = getIntent();
        String priceStr = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");


        Log.d("BuyMedicineBookActivity", "Received: price=" + priceStr + ", date=" + date);

        // Validate intent data
        if (priceStr == null || priceStr.isEmpty() || date == null || date.isEmpty() ) {
            Log.e("BuyMedicineBookActivity", "Missing intent extras: price=" + priceStr + ", date=" + date );
            Toast.makeText(getApplicationContext(), "Missing booking details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Parse price
        float price = 0.0f;
        try {
            String cleanedPrice = priceStr.replaceAll("[^0-9.]", "");
            if (!cleanedPrice.matches("\\d*\\.?\\d+")) {
                Log.e("BuyMedicineBookActivity", "Invalid cleaned price: " + cleanedPrice);
                Toast.makeText(getApplicationContext(), "Invalid price format", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            price = Float.parseFloat(cleanedPrice);
        } catch (NumberFormatException e) {
            Log.e("BuyMedicineBookActivity", "Price parsing error: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Invalid price format: " + priceStr, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        final float finalPrice = price;

        // Handle button click
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BuyMedicineBookActivity", "Book button clicked");

                // Validate input fields
                String fullName = edname.getText().toString().trim();
                String address = edaddress.getText().toString().trim();
                String contact = edcontact.getText().toString().trim();
                String pincodeStr = edpincode.getText().toString().trim();
                Log.d("BuyMedicineBookActivity", "Inputs: name=" + fullName + ", address=" + address + ", contact=" + contact + ", pincode=" + pincodeStr);

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
                Log.d("BuyMedicineBookActivity", "Username: " + username);

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please log in to book", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineBookActivity.this, LoginActivity.class));
                    finish();
                    return;
                }

                // Save order to database
                try {
                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username, fullName, address, contact, pincode, date,"", finalPrice, "medicine");
                    db.removeCart(username, "medicine");
                    db.logOrders(); // Debug: Log all orders
                    Toast.makeText(getApplicationContext(), "Your booking was completed successfully", Toast.LENGTH_SHORT).show();
                    Log.d("BuyMedicineBookActivity", "Redirecting to HomeActivity");
                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                    finish();
                } catch (Exception e) {
                    Log.e("BuyMedicineBookActivity", "Database error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error saving order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}