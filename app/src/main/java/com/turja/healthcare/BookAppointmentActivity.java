//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlertDialog;
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import java.util.Calendar;
//
//public class BookAppointmentActivity extends AppCompatActivity {
//    EditText ed1, ed2, ed3, ed4;
//    TextView tv;
//    private DatePickerDialog datePickerDialog;
//    private TimePickerDialog timePickerDialog;
//    private Button timeButton,dateButton,btnBook,btnBack;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_appointment);
//
//        tv = findViewById(R.id.textViewAppTitle);
//        ed1 = findViewById(R.id.editTextAppFullName);
//        ed2 = findViewById(R.id.editTextAppAddress);
//        ed3 = findViewById(R.id.editTextAppContactNum);
//        ed4 = findViewById(R.id.editTextAppFees);
//        timeButton = findViewById(R.id.buttonAppTime);
//        dateButton = findViewById(R.id.buttonAppDate);
//        btnBook=findViewById(R.id.buttonAppBookAppointment);
//        btnBack=findViewById(R.id.buttonAppBack);
//
//        ed1.setKeyListener(null);
//        ed2.setKeyListener(null);
//        ed3.setKeyListener(null);
//        ed4.setKeyListener(null);
//
//        Intent it = getIntent();
//        String title = it.getStringExtra("title");
//        String fullname = it.getStringExtra("line1");
//        String hospital = it.getStringExtra("line2");
//        String contact = it.getStringExtra("line4");
//        String fees = it.getStringExtra("line5");
//
//        tv.setText(title);
//        ed1.setText(fullname);
//        ed2.setText(hospital); // you can display both together
//        ed3.setText(contact);
//        ed4.setText("Cons Fee: " + fees + "/-");
//
//        //datepicker
//        intDatePicker();
//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                datePickerDialog.show();
//
//            }
//        });
//
//        //timepicker
//        intTimePicker();
//        timeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                timePickerDialog.show();
//
//            }
//        });
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
//
//            }
//        });
//
//        btnBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Database db=new Database( getApplicationContext(),"healthcare",null,1);
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                String username = sharedPreferences.getString("username", "");
//                if(db.checkAppointmentExitsts(username,title+"=>"+fullname,hospital,contact,dateButton.getText().toString(),timeButton.getText().toString())==1)
//                {
//                    Toast.makeText(getApplicationContext(), "Appointment alreeady booked", Toast.LENGTH_LONG).show();
//                }
//                else {
//                db.addOrder(username,title+"=>"+fullname,hospital,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
//                Toast.makeText(getApplicationContext(),"your appointment is done successfule",Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
//
//                }
//
//
//            }
//        });
//
//    }
//public void intDatePicker()
//{
//    DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//            i1=i1+1;
//           dateButton.setText(i2+"/"+i1+"/"+i);
//
//        }
//    };
//    Calendar cal=Calendar.getInstance();
//    int year=cal.get(Calendar.YEAR);
//    int month=cal.get(Calendar.MONTH);
//    int day=cal.get(Calendar.DAY_OF_MONTH);
//
//    int style= AlertDialog.THEME_HOLO_DARK;
//    datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
//    datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
//
//}
//
//    public void intTimePicker()
//    {
//        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                timeButton.setText(i+":"+i1);
//
//            }
//        };
//        Calendar cal=Calendar.getInstance();
//        int hrs=cal.get(Calendar.HOUR);
//        int minis=cal.get(Calendar.MINUTE);
//
//
//        int style= AlertDialog.THEME_HOLO_DARK;
//        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,minis,true);
//
//
//    }
//
//}


package com.turja.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button timeButton, dateButton, btnBook, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNum);
        ed4 = findViewById(R.id.editTextAppFees);
        timeButton = findViewById(R.id.buttonAppTime);
        dateButton = findViewById(R.id.buttonAppDate);
        btnBook = findViewById(R.id.buttonAppBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        String fullname = it.getStringExtra("line1");
        String hospital = it.getStringExtra("line2");
        String contact = it.getStringExtra("line4");
        String fees = it.getStringExtra("line5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(hospital);
        ed3.setText(contact);
        ed4.setText("Cons Fee: " + fees + "/-");

        // DatePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        // TimePicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String date = dateButton.getText().toString();
                String time = timeButton.getText().toString();

                // Validate date and time
                if (date.equals("Select Date") || time.equals("Select Time")) {
                    Toast.makeText(getApplicationContext(), "Please select a date and time", Toast.LENGTH_LONG).show();
                    return;
                }

                // Check if appointment already exists
                if (db.checkAppointmentExists(username, title + " => " + fullname, hospital, contact, date, time) == 1) {
                    Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_LONG).show();
                } else {
                    db.addOrder(username, title + " => " + fullname, hospital, contact, 0, date, time, Float.parseFloat(fees), "appointment");
                    Toast.makeText(getApplicationContext(), "Your appointment is booked successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1; // Month is 0-based
                dateButton.setText(day + "/" + month + "/" + year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Set min date to tomorrow
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                timeButton.setText(String.format("%02d:%02d", hour, minute));
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}