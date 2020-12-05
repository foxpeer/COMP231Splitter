/*
 * Author Victoria Liu, Yoonseop Lee, Dewakar. Last modified on 26-Nov-2020. This activity implements the viewing expenses function of the app
 * User story: As an register user, I want to view detailed expenses and create, update or delete expenses.
 * */
package com.example.Simplitter.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Simplitter.ActivityViewModel;
import com.example.Simplitter.Adapters.ActivityAdapter;
import com.example.Simplitter.Adapters.ExpensesAdapter;
import com.example.Simplitter.DetailExpensesViewModel;
import com.example.Simplitter.Model.DetailExpenses;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.R;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExpensesDetailActivity extends AppCompatActivity {

    //UI control instances
    DetailExpensesViewModel detailExpensesViewModel;
    RecyclerView detailActivityRecyclerView;
    Button addExpenseButton, updateButton,cancel3,payNow;
    ExpensesActivity expensesActivity;
    ActivityViewModel activityViewModel;
    TextView totalamount;
    EditText contributors;
    //Variables
    int activityID;
    double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_detail);
        //Get  UI control from layout
        detailActivityRecyclerView=findViewById(R.id.recycler_viewExpense);
        contributors=findViewById(R.id.edtContributors);
        activityViewModel=ViewModelProviders.of(this).get(ActivityViewModel.class);

        //Check if there has extra, and assign the extra value to variables
        Intent intent=getIntent();
        if(intent.hasExtra("ActivityID")){
            activityID=intent.getIntExtra("ActivityID",0);
        }
        if(intent.hasExtra("Activity")){
            String JsonData=intent.getStringExtra("Activity");
            expensesActivity= new Gson().fromJson(JsonData, ExpensesActivity.class);
        }
        if(intent.hasExtra("Contributors")){
            contributors.setText(String.valueOf(intent.getIntExtra("Contributors",0)));
        }

        //Set detailActivityRecyclerView with expensesAdapter
        detailActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExpensesAdapter expensesAdapter=new ExpensesAdapter(this);
        detailActivityRecyclerView.setAdapter(expensesAdapter);
        detailExpensesViewModel=ViewModelProviders.of(this).get(DetailExpensesViewModel.class);

        //Get detail expenses activity by activity ID
        detailExpensesViewModel.getExpensesByActivityID(activityID).observe(this, new Observer<List<DetailExpenses>>() {
            @Override
            public void onChanged(List<DetailExpenses> detailExpenses) {
                expensesAdapter.setDetailExpensesActivities(detailExpenses);
            }
        });

        //Get total amount from each detail expenses' amount
        detailExpensesViewModel.getExpensesByActivityID(activityID).observe(this, (List<DetailExpenses> detailExpensesList)->{
            totalAmount=0.0;
            for (DetailExpenses detailexpenses:detailExpensesList) {
                totalAmount+=detailexpenses.getDetailExpensesAmount();
            }
            expensesActivity.setTotalAmount(totalAmount);
            totalamount=findViewById(R.id.textViewTotalAmount);
            totalamount.setText("Total Amount: "+String.valueOf(expensesActivity.getTotalAmount()));
        });

        //Navigate to update detail expenses page
        expensesAdapter.setOnItemClickListener(new ExpensesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent =new Intent(getApplicationContext(), UpdateDetailExpensesActivity.class);
                intent.putExtra("DetailExpensesID",expensesAdapter.getDetailExpenses(position).getDetailExpensesID());
                intent.putExtra("ActivityID",activityID);
                startActivity(intent);
            }
        });

        //Swipe left or right to delete detail expenses
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                detailExpensesViewModel.deleteExpenses(expensesAdapter.getDetailExpenses(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Expenses deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(detailActivityRecyclerView);

        //Navigate to create detail expenses page
        addExpenseButton=findViewById(R.id.button_addexpense);
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CreateDetailExpenseActivity.class);
                intent.putExtra("ActivityID",activityID);
                startActivity(intent);
            }
        });
        updateButton=findViewById(R.id.button_cancel2);

        //Yoonseop, Navigate to home page and update expenses activity
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId=expensesActivity.getUserID();
                String activityName=expensesActivity.getActivityName();
                int contributorsNumber=Integer.parseInt(contributors.getText().toString());
                double result=getResult(totalAmount,contributorsNumber);
                ExpensesActivity expensesActivity=new ExpensesActivity(userId,activityID,activityName,contributorsNumber,totalAmount,result);
                activityViewModel.updateActivity(expensesActivity);

                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                intent.putExtra("userID",userId);
                startActivity(intent);
            }
        });

        cancel3=findViewById(R.id.button_cancel3);
        //Close this activity
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        payNow=findViewById(R.id.button_paynow);
        //Dewakar. Navigate to pay option page
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),PaymentOptionsActivity.class);
                startActivity(intent);
            }
        });
    }
    public static double getResult(double total, int number){
        return Math.round(total/number);
    }


}