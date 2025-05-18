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

public class LoginActivity extends AppCompatActivity {
    EditText edUsername,edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername=findViewById(R.id.editTextLoginuserName);
        edPassword=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(db.login(username,password)==1)
                    {
                        Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( LoginActivity.this,HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("username",username);
                        //to save our data key and value
                        editor.apply();
                    }

                }
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });
    }
}