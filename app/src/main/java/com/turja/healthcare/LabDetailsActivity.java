//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class LabDetailsActivity extends AppCompatActivity {
//
//    TextView tvPackageName, tvTotalCost;
//    EditText edDetails;
//    Button btnaddToCart, btnBack;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_details);
//
//        tvPackageName = findViewById(R.id.textViewLDPackageName);
//        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
//        edDetails = findViewById(R.id.editTextTextLDMultiLine);
//        btnaddToCart = findViewById(R.id.buttonLDAddToCart);
//        btnBack = findViewById(R.id.buttonLDGoBack);
//
//        edDetails.setKeyListener(null);
//
//
//
//        // ✅ Get data using correct keys
//        Intent intent = getIntent();
//        tvPackageName.setText(intent.getStringExtra("title"));
//        edDetails.setText(intent.getStringExtra("details"));
//        tvTotalCost.setText("Total Cost : " + intent.getStringExtra("price") + "/-");
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LabDetailsActivity.this, LabTestActivity.class));
//            }
//        });
//
//        // Optional: AddToCart functionality
//        btnaddToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Placeholder for adding to cart logic
//                SharedPreferences sharedPreferences=getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
//                String username=sharedPreferences.getString("username","").toString();
//                String product=tvPackageName.getText().toString();
//                float price=Float.parseFloat(intent.getStringExtra("price").toString());
//
//                Database db=new Database(getApplicationContext(),"healthcare",null,1);
//
//                if(db.checkCart(username,product)==1){
//                    Toast.makeText(getApplicationContext(),"already added", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    db.addCart(username,product,price,"lab");
//                    Toast.makeText(getApplicationContext(),"Record Inserted To cart", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LabDetailsActivity.this, LabTestActivity.class));
//                }
//
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


package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_details);

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
        edDetails = findViewById(R.id.editTextTextLDMultiLine);
        btnAddToCart = findViewById(R.id.buttonLDAddToCart);
        btnBack = findViewById(R.id.buttonLDGoBack);

        edDetails.setKeyListener(null);

        // Get data from Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String details = intent.getStringExtra("details");
        String price = intent.getStringExtra("price");

        // Set data to views
        tvPackageName.setText(title);
        edDetails.setText(details);
        tvTotalCost.setText("Total Cost: " + price + "/-");

        btnBack.setOnClickListener(v -> startActivity(new Intent(LabDetailsActivity.this, LabTestActivity.class)));

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
                    startActivity(new Intent(LabDetailsActivity.this, LabTestActivity.class));
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid price format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}