/*
 * Author Victoria Liu. Last modified on 18-Nov-2020. This activity implements the create detail expenses function of the app
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

public class CreateDetailExpenseActivity extends AppCompatActivity {

    //UI control instances
    DetailExpensesViewModel detailExpensesViewModel;
    Button createButton, cancelButton;
    EditText expensesName, expensesAmount, expensesID;
    ExpensesActivity expensesActivity;
    ActivityViewModel activityViewModel;
    //Variables
    int activityID;
    String expensesname;
    double expenseamount;
    int expensesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_detailexpenses);

        //Fasten UI control instances with layout ID
        createButton=findViewById(R.id.button_createDetailExpense);
        cancelButton=findViewById(R.id.button_cancelCreate);
        expensesName=findViewById(R.id.editTextExpensesName);
        expensesAmount=findViewById(R.id.editTextExpenseAmount);
        expensesID=findViewById(R.id.editTextTextExpensesID);

        //Invoke detail Expenses ViewModel and activity ViewModel
        detailExpensesViewModel= ViewModelProviders.of(this).get(DetailExpensesViewModel.class);
        activityViewModel=ViewModelProviders.of(this).get(ActivityViewModel.class);

        //Get activity ID from intent
        Intent intent=getIntent();
        if(intent.hasExtra("ActivityID")){
            this.activityID=intent.getIntExtra("ActivityID",0);
        }
        //Get expenses activity by activity ID
        activityViewModel.getActivity(activityID).observe(this, (List<ExpensesActivity> expensesActivityList)->{
            expensesActivity=expensesActivityList.get(0);
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expensesname=expensesName.getText().toString();
                expenseamount=Double.parseDouble(expensesAmount.getText().toString());
                expensesId=Integer.parseInt(expensesID.getText().toString());

                //Invoke insertExpenses method in detail expensesViewModel to insert new detail expenses
                detailExpensesViewModel.insertExpenses(new DetailExpenses(activityID,expensesId,expensesname,expenseamount));

                //Navigate to expenses detail activity and sent activity ID and activity back
                Intent intent=new Intent(getApplicationContext(),ExpensesDetailActivity.class);
                intent.putExtra("ActivityID",activityID);
                intent.putExtra("Activity",  new Gson().toJson(expensesActivity));
                intent.putExtra("Contributors",  expensesActivity.getNumberOfContributors());
                startActivity(intent);
            }
        });
        //Close this activity if cancel button clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
