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

    EditText updateExpensesName, updateExpensesAmount;
    Button updateButton,cancelButton;
    DetailExpensesViewModel detailExpensesViewModel;
    int detailExpensesID;
    int activityID;
    ActivityViewModel activityViewModel;
    ExpensesActivity expensesActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_detailexpenses);

        updateExpensesName=findViewById(R.id.editTextUpdateExpensesName);
        updateExpensesAmount=findViewById(R.id.editTextUpdateExpenseAmount);
        updateButton=findViewById(R.id.button_updateDetailExpense);

        activityViewModel=ViewModelProviders.of(this).get(ActivityViewModel.class);
        detailExpensesViewModel= ViewModelProviders.of(this).get(DetailExpensesViewModel.class);

        Intent intent=getIntent();
        if(intent.hasExtra("DetailExpensesID")){
            detailExpensesID=intent.getIntExtra("DetailExpensesID",0);
            detailExpensesViewModel.loadDetailExpenses(detailExpensesID,this);
        }

        if(intent.hasExtra("ActivityID")){
            this.activityID=intent.getIntExtra("ActivityID",0);
        }
        activityViewModel.getActivity(activityID).observe(this, (List<ExpensesActivity> expensesActivityList)->{
            expensesActivity=expensesActivityList.get(0);
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String expenseName=updateExpensesName.getText().toString();
                double expenseAmount=Double.parseDouble(updateExpensesAmount.getText().toString());
                DetailExpenses detailExpenses=new DetailExpenses(activityID,detailExpensesID,expenseName,expenseAmount);
                detailExpensesViewModel.updateExpenses(detailExpenses);

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
    public void loadInputText(DetailExpenses detailExpenses) {
        activityID=detailExpenses.getActivityID();
        updateExpensesName.setText(detailExpenses.getDetailExpensesName());
        updateExpensesAmount.setText(String.valueOf(detailExpenses.getDetailExpensesAmount()));
    }
}
