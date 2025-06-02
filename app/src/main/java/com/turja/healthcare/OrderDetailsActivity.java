//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class OrderDetailsActivity extends AppCompatActivity {
//
//    private String [][] order_details={};
//
//    HashMap<String,String>item;
//    ArrayList list;
//    SimpleAdapter sa;
//    ListView lst;
//    Button btn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_details);
//
//        btn=findViewById(R.id.buttonODBack);
//        lst=findViewById(R.id.listViewOD);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent (OrderDetailsActivity.this,HomeActivity.class));
//
//
//            }
//        });
//
//
//        // Database
//        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
//        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
//        String username = sharedPreferences.getString("username", "").toString();
//
//        ArrayList dbData = db.getOrderData(username);
//
//        order_details=new String[dbData.size()][];
//        for(int i=0;i<order_details.length;i++)
//        {
//            String arrData=dbData.get(i).toString();
//            String[]strData=arrData.split(java.util.regex.Pattern.quote("$"));
//            order_details[i][0]=strData[0];
//            order_details[i][1]=strData[1];
//            if(strData[7].compareTo("medicine")==0)
//            {
//                order_details[i][3]="Del :"+strData[4]+" "+strData[5];
//
//            }
//            order_details[i][2]="Rs ."+strData[6];
//            order_details[i][4]=strData[7];
//        }
//        list =new ArrayList();
//        for(int i=0;i<order_details.length;i++)
//        {
//            item=new HashMap<String,String>();
//            item.put("line1",order_details[i][0]);
//            item.put("line2",order_details[i][1]);
//            item.put("line3",order_details[i][2]);
//            item.put("line4",order_details[i][3]);
//            item.put("line5",order_details[i][4]);
//            list.add(sa);
//        }
//    }
//}




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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private ListView lst;
    private Button btn;
    private TextView tvNoOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Initialize UI components
        btn = findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listViewOD);
        tvNoOrders = findViewById(R.id.textViewNoOrders);

        // Back button click listener
        btn.setOnClickListener(v -> startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class)));

        // Initialize database and SharedPreferences
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        Log.d("OrderDetailsActivity", "Username: " + username);

        if (username.isEmpty()) {
            Log.e("OrderDetailsActivity", "Username is empty");
            Toast.makeText(this, "Please log in to view orders", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(OrderDetailsActivity.this, LoginActivity.class));
            finish();
            return;
        }

        // Fetch order data from database
        ArrayList<String> dbData = db.getOrderData(username);
        Log.d("OrderDetailsActivity", "dbData size: " + dbData.size());
        for (String order : dbData) {
            Log.d("OrderDetailsActivity", "Order data: " + order);
        }

        // Prepare data for ListView
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (String order : dbData) {
            try {
                String[] strData = order.split(java.util.regex.Pattern.quote("$"));
                if (strData.length < 8) {
                    Log.e("OrderDetailsActivity", "Malformed order data: " + order);
                    continue;
                }
                HashMap<String, String> item = new HashMap<>();
                item.put("line1", "Name: " + strData[0]); // Full Name
                item.put("line2", "Address: " + strData[1]); // Address
                item.put("line3", "Fee: Rs. " + strData[6]); // Amount
                item.put("line4", "Date: " + strData[4] + " Time: " + strData[5]); // Date + Time
                item.put("line5", "Type: " + strData[7]); // Order Type
                list.add(item);
            } catch (Exception e) {
                Log.e("OrderDetailsActivity", "Error parsing order: " + order + ", " + e.getMessage());
            }
        }

        // Handle empty order list
        if (list.isEmpty()) {
            if (tvNoOrders != null) {
                tvNoOrders.setVisibility(View.VISIBLE);
                lst.setVisibility(View.GONE);
            } else {
                Log.e("OrderDetailsActivity", "tvNoOrders is null");
                Toast.makeText(this, "No orders found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Set up SimpleAdapter for ListView
            SimpleAdapter sa = new SimpleAdapter(
                    this,
                    list,
                    R.layout.multi_lines,
                    new String[]{"line1", "line2", "line3", "line4", "line5"},
                    new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
            );
            lst.setAdapter(sa);
            if (tvNoOrders != null) {
                tvNoOrders.setVisibility(View.GONE);
            }
            lst.setVisibility(View.VISIBLE);
        }
    }
}








