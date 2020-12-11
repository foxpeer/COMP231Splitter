/*
* Author: Xinglong Lu. Last Modified: 9, Nov,2020. This class implements update user information function of app
* User story: As a registered user I want to visit my profit page. As a registered user, I would want to change my email or
* password, and also be able to logout
* */
package com.example.Simplitter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import com.example.Simplitter.Model.User;
import com.example.Simplitter.R;
import com.example.Simplitter.UserViewModel;

public class UpdateUserActivity extends AppCompatActivity {

    //UI control instance
    EditText updateEmail, updateFirstName,updateLastName,updatePassword;
    Button updateButton, cancelButton;
    UserViewModel userViewModel;
    int userID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user);

        //Get UI control from layout
        updateEmail=findViewById(R.id.editText_updateEmail);
        updateFirstName=findViewById(R.id.editText_updateFirstName);
        updateLastName=findViewById(R.id.editText_updateLastName);
        updatePassword=findViewById(R.id.editText_updatePassword);
        updateButton=findViewById(R.id.Button_updateUser);
        cancelButton=findViewById(R.id.Button_cancelUpdate);

        //Check if there has user id
        Intent intent=getIntent();
        if(intent.hasExtra("userID")){
            userID=intent.getIntExtra("userID",0);
        }
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        //Get user and display it
        userViewModel.loadUser(userID,this);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get user input
                String email=updateEmail.getText().toString();
                String firstName=updateFirstName.getText().toString();
                String lastName=updateLastName.getText().toString();
                String password=updatePassword.getText().toString();
                //Update user
                userViewModel.update(new User(userID,email,firstName,lastName,password));

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //Display user
    public void loadUserText(User user) {
        userID=user.getUserID();
        updateFirstName.setText(user.getFirstname());
        updateLastName.setText(user.getLastname());
        updateEmail.setText(user.getEmail());
    }
}
