package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.Simplitter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateExpenseActivity extends AppCompatActivity {

    EditText expenseName, howmanyppl, contributorEmail;
    Button sendNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);

        expenseName = findViewById(R.id.editText_expensename);
        howmanyppl = findViewById(R.id.editText_howMany2);

        contributorEmail=findViewById(R.id.editText_contributorEmail);
        sendNotification=findViewById(R.id.button_sendNotification);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(contributorEmail.getText().toString(),"You have a new expenses need to be paid, please check!");
            }
        });
    }

    //Send email using intent with action send
    public void sendEmail(String emailAddress,String message){
        Intent emailIntent=new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{emailAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"New split expenses need to be payed.");
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Choose Mail APP"));
    }

}