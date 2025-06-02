package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvPackageName=findViewById(R.id.textViewBMDPackageName);
        tvTotalCost=findViewById(R.id.textViewBMDTotalCost);
        edDetails=findViewById(R.id.editTextTextBMDDMultiLine);
        edDetails.setKeyListener(null);
        btnBack=findViewById(R.id.buttonBMDGoBack);
        btnAddToCart=findViewById(R.id.buttonBMDAddToCart);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String details = intent.getStringExtra("details");
        String price = intent.getStringExtra("price");

        // Set data to views
        tvPackageName.setText(title);
        edDetails.setText(details);
        tvTotalCost.setText("Total Cost: " + price + "/-");
        btnBack.setOnClickListener(v -> startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class)));



        btnAddToCart.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");
            String product = tvPackageName.getText().toString();

            try {
                float priceValue = Float.parseFloat(price);
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product already in cart", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username, product, priceValue, "lab");
                    Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid price format", Toast.LENGTH_SHORT).show();
            }


        });

    }
}