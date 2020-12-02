package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.Simplitter.ActivityViewModel;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateExpenseActivity extends AppCompatActivity {

    EditText expenseName, howmanyppl, contributorEmail,activityID;
    Button sendNotification, createButton, cancelCreate;
    ActivityViewModel expensesActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);

        expensesActivityViewModel= ViewModelProviders.of(this).get(ActivityViewModel.class);

        expenseName = findViewById(R.id.editText_non_expensename);
        howmanyppl = findViewById(R.id.editText_non_howMany2);
        activityID=findViewById(R.id.editText_activityID);

        contributorEmail=findViewById(R.id.editText_non_contributorEmail);
        sendNotification=findViewById(R.id.button_non_sendNotification);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(contributorEmail.getText().toString(),"You have a new expenses need to be paid, please check!");
            }
        });

        createButton=findViewById(R.id.button_createDetailExpense);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insertExpensesActivity();
                    Toast.makeText(getApplicationContext(),"New expenses activity created",Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelCreate=findViewById(R.id.button_cancelCreate);
        cancelCreate.setOnClickListener(new View.OnClickListener() {
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

    public void insertExpensesActivity(){
        Intent intent =getIntent();
        if(intent.hasExtra("userId")){
            int userID=intent.getIntExtra("userId",0);
            String expensesTitle=expenseName.getText().toString();
            int activityId=Integer.parseInt(activityID.getText().toString());
            int numberOfContributors=Integer.parseInt(howmanyppl.getText().toString());
            double totalAmount=0,reult=0;
            expensesActivityViewModel.insertActivity(new ExpensesActivity(userID, activityId,expensesTitle,
                    numberOfContributors,totalAmount,reult));
        }

    }

}