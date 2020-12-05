/*
 * Author Victoria Liu. Last modified on 25-Nov-2020. This activity implements the update detail expenses function of the app
 * User story: As an register user, I want to view detailed expenses and create, update or delete detail expenses
 * */
package com.example.Simplitter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.Simplitter.ActivityViewModel;
import com.example.Simplitter.DetailExpensesViewModel;
import com.example.Simplitter.Model.DetailExpenses;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.R;
import com.google.gson.Gson;

import java.util.List;

public class UpdateDetailExpensesActivity extends AppCompatActivity {

    //UI control instance
    EditText updateExpensesName, updateExpensesAmount;
    Button updateButton,cancelButton;
    DetailExpensesViewModel detailExpensesViewModel;
    ActivityViewModel activityViewModel;
    ExpensesActivity expensesActivity;

    //Variable
    int detailExpensesID;
    int activityID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_detailexpenses);
        //Get UI control from layout
        updateExpensesName=findViewById(R.id.editTextUpdateExpensesName);
        updateExpensesAmount=findViewById(R.id.editTextUpdateExpenseAmount);
        updateButton=findViewById(R.id.button_updateDetailExpense);
        //Get activity view model
        activityViewModel=ViewModelProviders.of(this).get(ActivityViewModel.class);
        detailExpensesViewModel= ViewModelProviders.of(this).get(DetailExpensesViewModel.class);

        //Check if there has extra values
        Intent intent=getIntent();
        if(intent.hasExtra("DetailExpensesID")){
            detailExpensesID=intent.getIntExtra("DetailExpensesID",0);
            //Get and display detail expenses
            detailExpensesViewModel.loadDetailExpenses(detailExpensesID,this);
        }

        if(intent.hasExtra("ActivityID")){
            this.activityID=intent.getIntExtra("ActivityID",0);
        }
        //Get expenses activity
        activityViewModel.getActivity(activityID).observe(this, (List<ExpensesActivity> expensesActivityList)->{
            expensesActivity=expensesActivityList.get(0);
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get user's input
                String expenseName=updateExpensesName.getText().toString();
                double expenseAmount=Double.parseDouble(updateExpensesAmount.getText().toString());
                DetailExpenses detailExpenses=new DetailExpenses(activityID,detailExpensesID,expenseName,expenseAmount);
                //Update detail expenses
                detailExpensesViewModel.updateExpenses(detailExpenses);
                //Send extra value to previous activity
                Intent intent=new Intent(getApplicationContext(),ExpensesDetailActivity.class);
                intent.putExtra("ActivityID",activityID);
                intent.putExtra("Activity",  new Gson().toJson(expensesActivity));
                intent.putExtra("Contributors",  expensesActivity.getNumberOfContributors());
                startActivity(intent);
            }
        });
        cancelButton=findViewById(R.id.button_cancelUpdate);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    //Display detail expenses
    public void loadInputText(DetailExpenses detailExpenses) {
        activityID=detailExpenses.getActivityID();
        updateExpensesName.setText(detailExpenses.getDetailExpensesName());
        updateExpensesAmount.setText(String.valueOf(detailExpenses.getDetailExpensesAmount()));
    }
}
