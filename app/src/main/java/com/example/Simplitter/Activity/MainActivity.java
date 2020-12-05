/*
 * Author: Liping Wu. Last modified 20,Nov,2020. This class is for store user login to app.
 * User story: As a register user, I want to login to this app
 * */
package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.Simplitter.UserViewModel;
import com.example.Simplitter.R;

public class MainActivity extends AppCompatActivity {

    //User View Model Instance
    private UserViewModel userViewModel;
    //UI control Instance
    EditText etEmail;
    EditText etPassword;
    TextView validation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get view model and UI control
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etPassword = (EditText) findViewById(R.id.editText_password);
        validation = (TextView) findViewById(R.id.textViewValidation);
    }

    public void LoginClick(View view) {
        validation.setText("");
       try {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            //Get user by user email
            userViewModel.getUserByEmail(email).observe(MainActivity.this, optionalN -> {
                optionalN.ifPresent(user -> {
                    //Check if user's password equals to password in database
                    if (user.getPassword().equals(password)) {
                        validation.setText("Welcome " + user.getFirstname() + " " + user.getLastname());
                        Intent intent = new Intent(this, HomeActivity.class);
                        intent.putExtra("userID",user.getUserID());
                        startActivity(intent);
                        LoginStatus.IsLogin=true;
                        Toast.makeText(getApplicationContext(), "Welcome back "+ email +"!", Toast.LENGTH_SHORT).show();
                    } else {
                        validation.setText("Failed to verify your credential, please try again");
                    }
                });
            });
        } catch (Exception ex) {
            validation.setText("Failed to verify your credential, please try again");
        }

    }

    //Register Click Button Clicked, navigate to register page
    public void RegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Register as a new user", Toast.LENGTH_SHORT).show();
    }
}

