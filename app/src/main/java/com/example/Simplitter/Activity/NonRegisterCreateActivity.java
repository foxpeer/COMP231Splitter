package com.example.Simplitter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Simplitter.R;

public class NonRegisterCreateActivity extends AppCompatActivity {
    EditText expenseName, howmanyppl, contributorEmail,totalAmount;
    Button sendNotification, backtomain;
    int peopleNumber=1;
    double total=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_registercreateactivity);

        expenseName=findViewById(R.id.editText_non_expensename);
        howmanyppl=findViewById(R.id.editText_non_howMany2);
        contributorEmail=findViewById(R.id.editText_non_contributorEmail);
        totalAmount=findViewById(R.id.editText_non_totalAmount);
        sendNotification=findViewById(R.id.button_non_sendNotification);
        backtomain=findViewById(R.id.button_backtomain);
        total=Double.parseDouble(totalAmount.getText().toString());
        peopleNumber =Integer.parseInt(howmanyppl.getText().toString());
        if(peopleNumber==0){
            peopleNumber=1;
        }
        double result=(total/peopleNumber);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            String message="A new "+expenseName.getText().toString()+" expenses with split result"+result+" need to pay, please check!";
            @Override
            public void onClick(View v) {
                sendEmail(contributorEmail.getText().toString(),message);
            }
        });

        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
