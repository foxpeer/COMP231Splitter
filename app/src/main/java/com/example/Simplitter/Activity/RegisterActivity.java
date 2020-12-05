/*
 * Author: Liping Wu. Last modified 20,Nov,2020. This class is for store user register to app.
 * User story: As a non-register user, I want to have options to register
 * */
package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Simplitter.Model.User;
import com.example.Simplitter.UserViewModel;
import com.example.Simplitter.R;

public class RegisterActivity extends AppCompatActivity {

    //UI control instance
    EditText eTemail, eTfirstName, eTlastName, eTpassword,userID;
    //User view model instance
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Get user view model
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        //Get UI control from layout
        userID=(EditText)findViewById(R.id.editText_userId);
        eTemail=(EditText)findViewById(R.id.editText_email);
        eTfirstName=(EditText)findViewById(R.id.editText_firstName);
        eTlastName=(EditText)findViewById(R.id.editText_lastName);
        eTpassword=(EditText)findViewById(R.id.editText_password);
    }

    public void RegisterClick(View view) {
        //Get user's input
        int id=Integer.parseInt(userID.getText().toString());
        String email= eTemail.getText().toString();
        String firstname=eTfirstName.getText().toString();
        String lastname=eTlastName.getText().toString();
        String passw=eTpassword.getText().toString();
        User user=new User(id,email,firstname,lastname,passw);
        //Invoke user view model to insert new user
        userViewModel.insert(user);
        Toast.makeText(getApplicationContext(),"Thanks for your registration, you are good to login now", Toast.LENGTH_SHORT).show();

        //After register, go to login page directly
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void LoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You already have an account, go to Login directly", Toast.LENGTH_SHORT).show();
    }
}