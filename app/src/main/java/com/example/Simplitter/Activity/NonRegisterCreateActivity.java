/*
* Author: Xinglong Lu. Last modified: 25,Nov,2020. This activity implement the function that allows non-register user create
* expenses activity
* User story: As an app user, I want to fill out my activity title and add all the contributors
* */
package com.example.Simplitter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Simplitter.R;

public class NonRegisterCreateActivity extends AppCompatActivity {
    //UI control instance
    EditText expenseName, howmanyppl, contributorEmail,totalAmount;
    Button sendNotification, backtomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_registercreateactivity);

        //Get UI control from layout
        expenseName=findViewById(R.id.editText_non_expensename);
        howmanyppl=findViewById(R.id.editText_non_howMany2);
        contributorEmail=findViewById(R.id.editText_non_contributorEmail);
        totalAmount=findViewById(R.id.editText_non_totalAmount);
        sendNotification=findViewById(R.id.button_non_sendNotification);
        backtomain=findViewById(R.id.button_backtomain);
        howmanyppl.setText(String.valueOf(1));
        totalAmount.setText(String.valueOf(0.0));

        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total=Double.parseDouble(totalAmount.getText().toString());
                int peopleNumber =Integer.parseInt(howmanyppl.getText().toString());
                //Calculate split result
                double result=Math.round(total/peopleNumber);
                String message="A new "+expenseName.getText().toString()+" expenses with split result "+String.valueOf(result)+" need to pay, please check!";
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
