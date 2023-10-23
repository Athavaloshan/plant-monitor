package com.android.plantmonitor.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import 	androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.plantmonitor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private EditText inputemail, inputpassword, retypePassword, fullName, address, contact;
    private FirebaseAuth mAuth;
    private Button btnSignup;
    private ProgressDialog pd;
    private boolean isUpdate = false;
    private FirebaseDatabase db_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);
        pd.show();


        db_User = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        inputemail = findViewById(R.id.input_userEmail);
        inputpassword = findViewById(R.id.input_password);
        retypePassword = findViewById(R.id.input_password_confirm);
        btnSignup = findViewById(R.id.button_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputemail.getText().toString();
                final String password = inputpassword.getText().toString();
                final String ConfirmPassword = retypePassword.getText().toString();
                try {
                        if (!isUpdate) {
                            if (email.length() == 0) {
                                ShowError("Email ID");
                                inputemail.requestFocusFromTouch();
                            } else if (password.length() <= 5) {
                                ShowError("Password");
                                inputpassword.requestFocusFromTouch();
                            } else if (password.compareTo(ConfirmPassword) != 0) {
                                Toast.makeText(ProfileActivity.this, "Password did not match!", Toast.LENGTH_LONG)
                                        .show();
                                retypePassword.requestFocusFromTouch();
                            } else {
                                pd.show();
                                mAuth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(ProfileActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {

                                                if (!task.isSuccessful()) {
                                                    Toast.makeText(ProfileActivity.this, "Registration failed! try agian.", Toast.LENGTH_LONG)
                                                            .show();
                                                    Log.v("error", task.getException().getMessage());
                                                } else {

                                                    Toast.makeText(getApplicationContext(), "Welcome, your account has been created!", Toast.LENGTH_LONG)
                                                            .show();
                                                    Intent intent = new Intent(ProfileActivity.this, SplashActivity.class);//TODO
                                                    //change from SplashActivity to dashboard
                                                    startActivity(intent);

                                                    finish();
                                                }
                                                pd.dismiss();

                                            }

                                        });
                            }

                        } else {

                            Toast.makeText(getApplicationContext(), "Your account has been updated!", Toast.LENGTH_LONG)
                                    .show();
                            Intent intent = new Intent(ProfileActivity.this, SplashActivity.class);
                            //TODO: Mainactivity to dashboard
                            startActivity(intent);
                            finish();
                        }
                        pd.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void ShowError(String error) {

        Toast.makeText(ProfileActivity.this, "Please, Enter a valid "+error,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


//aa@gmail.com
//athavan
//1@gmail.com
//123456