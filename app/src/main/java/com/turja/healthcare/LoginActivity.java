//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class LoginActivity extends AppCompatActivity {
//    private static final String TAG = "LoginActivity";
//    EditText edUsername, edPassword;
//    Button btnLogin;
//    TextView tvRegister;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        // Initialize UI components
//        edUsername = findViewById(R.id.editTextLoginuserName);
//        edPassword = findViewById(R.id.editTextLoginPassword);
//        btnLogin = findViewById(R.id.buttonLogin);
//        tvRegister = findViewById(R.id.textViewNewUser);
//
//        // Login button click listener
//        btnLogin.setOnClickListener(v -> {
//            String username = edUsername.getText().toString().trim();
//            String password = edPassword.getText().toString().trim();
//            Log.d(TAG, "Login attempt: username=" + username);
//
//            if (username.isEmpty() || password.isEmpty()) {
//                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
//            if (db.login(username, password) == 1) {
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", username);
//                editor.apply();
//                Log.d(TAG, "Username saved to SharedPreferences: " + username);
//                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                finish();
//            } else {
//                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Register link click listener
//        tvRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
//    }
//}



//package com.turja.healthcare;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.google.firebase.firestore.FirebaseFirestore;
//import java.util.HashMap;
//
//public class LoginActivity extends AppCompatActivity {
//    private static final String TAG = "LoginActivity";
//    EditText edUsername, edPassword;
//    Button btnLogin;
//    TextView tvRegister;
//    FirebaseFirestore db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        // Initialize UI components
//        edUsername = findViewById(R.id.editTextLoginuserName);
//        edPassword = findViewById(R.id.editTextLoginPassword);
//        btnLogin = findViewById(R.id.buttonLogin);
//        tvRegister = findViewById(R.id.textViewNewUser);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Login button click listener
//        btnLogin.setOnClickListener(v -> {
//            String username = edUsername.getText().toString().trim();
//            String password = edPassword.getText().toString().trim();
//            Log.d(TAG, "Login attempt: username=" + username);
//
//            if (username.isEmpty() || password.isEmpty()) {
//                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Database dbLocal = new Database(getApplicationContext(), "healthcare", null, 1);
//            if (dbLocal.login(username, password) == 1) {
//                // Save login details to SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", username);
//                editor.apply();
//                Log.d(TAG, "Username saved to SharedPreferences: " + username);
//
//                // Save login details to Firestore
//                HashMap<String, Object> userData = new HashMap<>();
//                userData.put("username", username);
//                userData.put("password", password); // Note: Storing passwords in plain text is insecure; consider hashing
//                userData.put("login_timestamp", System.currentTimeMillis());
//
//                db.collection("users")
//                        .document(username) // Use username as document ID for simplicity
//                        .set(userData)
//                        .addOnSuccessListener(aVoid -> Log.d(TAG, "User login details saved to Firestore"))
//                        .addOnFailureListener(e -> Log.w(TAG, "Error saving user login details", e));
//
//                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
//
//                // Check for redirect_to extra
//                Intent intent = getIntent();
//                String redirectTo = intent.getStringExtra("redirect_to");
//                if ("AddDoctorActivity".equals(redirectTo)) {
//                    startActivity(new Intent(LoginActivity.this, AddDoctorActivity.class));
//                } else {
//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                }
//                finish();
//            } else {
//                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Register link click listener
//        tvRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
//    }
//}



package com.turja.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    EditText edUsername, edPassword;
    Button btnLogin;
    TextView tvRegister;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;

    private final ActivityResultLauncher<IntentSenderRequest> signInLauncher =
            registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    try {
                        SignInCredential credential = Identity.getSignInClient(this)
                                .getSignInCredentialFromIntent(result.getData());
                        String idToken = credential.getGoogleIdToken();
                        if (idToken != null) {
                            firebaseAuthWithGoogle(idToken);
                        } else {
                            Log.w(TAG, "No ID token!");
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Google sign-in failed", e);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginuserName);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tvRegister = findViewById(R.id.textViewNewUser);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Traditional login
        btnLogin.setOnClickListener(v -> {
            String username = edUsername.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Database dbLocal = new Database(getApplicationContext(), "healthcare", null, 1);
            if (dbLocal.login(username, password) == 1) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
                sharedPreferences.edit().putString("username", username).apply();

                HashMap<String, Object> userData = new HashMap<>();
                userData.put("username", username);
                userData.put("password", password); // Consider hashing in production
                userData.put("login_timestamp", System.currentTimeMillis());

                db.collection("users").document(username)
                        .set(userData)
                        .addOnSuccessListener(unused -> Log.d(TAG, "User login saved to Firestore"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error saving login", e));

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

                String redirectTo = getIntent().getStringExtra("redirect_to");
                if ("AddDoctorActivity".equals(redirectTo)) {
                    startActivity(new Intent(LoginActivity.this, AddDoctorActivity.class));
                } else {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                finish();
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));

        // Google Sign-In setup
        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId("247097924859-1vt7e3h7at9acomis1e27ofsslub13k5.apps.googleusercontent.com")
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();

        // Optional: Automatically show Google Sign-In button or trigger sign-in
        findViewById(R.id.google_sign_in_button).setOnClickListener(v -> startGoogleSignIn());
    }

    private void startGoogleSignIn() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, result -> {
                    IntentSenderRequest request = new IntentSenderRequest.Builder(result.getPendingIntent()).build();
                    signInLauncher.launch(request);
                })
                .addOnFailureListener(this, e -> Log.e(TAG, "Google Sign-In failed", e));
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(this, "Google Sign-In successful", Toast.LENGTH_SHORT).show();
                        updateUI(user);
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Log.d(TAG, "User: " + user.getDisplayName() + " | " + user.getEmail());
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
            sharedPreferences.edit().putString("username", user.getEmail()).apply();

            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}




