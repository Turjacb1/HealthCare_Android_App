package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, btnCheckout, btnBack;
    private String[][] packages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dateButton = findViewById(R.id.buttonBMCartDatePicker);

        btnCheckout = findViewById(R.id.buttonBMCartCheckout);
        btnBack = findViewById(R.id.buttonBackBMCart);
        tvTotal = findViewById(R.id.textViewBMCartTotalCost);
        lst = findViewById(R.id.listViewBMCart);


        // Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        Log.d("CartBuyMedicineActivity", "Username: " + username);

        if (username.isEmpty()) {
            Toast.makeText(this, "Please log in to view cart", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CartBuyMedicineActivity.this, LoginActivity.class));
            finish();
            return;
        }

        // Database
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        ArrayList<String> dbData = db.getCartData(username, "lab");

        // Initialize total amount
        float totalAmount = 0;

        // Initialize packages array based on dbData size
        packages = new String[dbData.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }

        // Populate packages array from dbData
        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0]; // Product name
            packages[i][1] = ""; // Placeholder
            packages[i][2] = ""; // Placeholder
            packages[i][3] = ""; // Placeholder
            packages[i][4] = "Cost: " + strData[1] + "/-";
            totalAmount += Float.parseFloat(strData[1]);
        }

        // Set total cost
        tvTotal.setText("Total Cost: " + totalAmount);

        // Populate ListView
        list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }

        // Set up SimpleAdapter
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        // Handle empty cart
        if (list.isEmpty()) {
            tvTotal.setText("Total Cost: 0");
            btnCheckout.setEnabled(false);
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
        } else {
            btnCheckout.setEnabled(true);
        }

        // Back Button
        btnBack.setOnClickListener(v -> startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class)));

        // Date picker
        initDatePicker();
        dateButton.setOnClickListener(v -> datePickerDialog.show());



        // Checkout Button
        final float finalTotalAmount = totalAmount;
        btnCheckout.setOnClickListener(v -> {
            if (list.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            String selectedDate = dateButton.getText().toString();

            if (selectedDate.equals("Select Date") ) {
                Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
            intent.putExtra("price", String.valueOf(finalTotalAmount));
            intent.putExtra("date", selectedDate);

            startActivity(intent);
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            dateButton.setText(day + "/" + month + "/" + year);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // +1 day
    }

}