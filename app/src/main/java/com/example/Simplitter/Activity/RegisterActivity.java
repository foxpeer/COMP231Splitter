/*
 * Author: Liping Wu. Last modified 20,Nov,2020. This class is for store user register to app.
 * User story: As a non-register user, I want to have options to register
 * */
package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.Simplitter.Model.User;
import com.example.Simplitter.UserViewModel;
import com.example.Simplitter.R;

public class RegisterActivity extends AppCompatActivity {

    //UI control instance
    EditText eTemail, eTfirstName, eTlastName, eTpassword,userID, etValidation;
    //User view model instance
    private UserViewModel userViewModel;

    boolean isUserExist=false;

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
        etValidation = (EditText) findViewById(R.id.etValidation);
    }

    //click Reigister button to Register
    public void RegisterClick(View view) {
        //Get user's input
        int id=Integer.parseInt(userID.getText().toString());
        String email= eTemail.getText().toString();
        String firstname=eTfirstName.getText().toString();
        String lastname=eTlastName.getText().toString();
        String passw=eTpassword.getText().toString();
        //validate email input format is correct
        if(validateEmailInputFormat(email) == false){
            etValidation.setText("Please check your email input format");
            return;
        }

        //check whether the user with the input email exist
        //if the user/ email exists in database, return
        //else: insert a new user to the database
        else{
            userViewModel.getUserByEmail(email).observe(this, optionalN -> {
                optionalN.ifPresent(user -> {
                    if (user.getEmail().equals(email)) {
                        isUserExist=true;
                        etValidation.setText("The email account is already exist, please go to login directly or creat a new account.");
                        return;
                    }
                });
                if(!isUserExist){
                    User user=new User(id,email,firstname,lastname,passw);
                    userViewModel.insert(user);
                    Toast.makeText(getApplicationContext(),"Thanks for your registration, you are good to login now", Toast.LENGTH_SHORT).show();
                    //after register, go to login page directly
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
    // click login button to login
    public void LoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You already have an account, go to Login directly", Toast.LENGTH_SHORT).show();
    }

    //method validate email input format
    public boolean validateEmailInputFormat (String email){
        String regex =  "^[a-zA-Z0-9._-]+@[a-z]+(\\.[a-z]+)+$";
        String result;
        if(email.equals(null)|| email.equals("") ||email.isEmpty() ||(!email.matches(regex))){
            result = "Please enter a correct email format ";
            //Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }//end of validation
}