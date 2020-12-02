package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.Simplitter.UserViewModel;
import com.example.Simplitter.R;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private SharedPreferences userPref;
    public SharedPreferences.Editor editor;
    EditText etEmail;
    EditText etPassword;
    EditText validation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etPassword = (EditText) findViewById(R.id.editText_password);
        validation = (EditText) findViewById(R.id.textViewValidation);
    }

    public void LoginClick(View view) {
        validation.setText("");
       try {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            userViewModel.getUserByEmail(email).observe(MainActivity.this, optionalN -> {
                optionalN.ifPresent(user -> {
                    if (user.getPassword().equals(password)) {
                        validation.setText("Welcome " + user.getFirstname() + " " + user.getLastname());
                        userPref = getSharedPreferences("user", MODE_PRIVATE);
                        editor = userPref.edit();
                        editor.putString("email", user.getEmail());
                        editor.putString("firstName", user.getFirstname());
                        editor.putString("lastName", user.getLastname());
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Welcome back "+ email +"!", Toast.LENGTH_SHORT).show();
                        Log.d("Login Check", "MainActivity::LoginClick: Welcome back "+ email +"! ");

                       // add in the future for new activity added
                        Intent intent = new Intent(this, HomeActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("firstName", user.getFirstname());
                        intent.putExtra("lastName", user.getLastname());
                        startActivity(intent);

                    } else {
                        validation.setText("Oops, please try again");
                    }
                });
            });
        } catch (Exception ex) {
            validation.setText("Oops, please try again");
        }

    }

    //Register Click Button Clicked
    public void RegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Register as a new user", Toast.LENGTH_SHORT).show();
    }


}

