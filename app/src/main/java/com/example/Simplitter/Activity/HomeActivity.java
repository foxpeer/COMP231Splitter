/*
 * Author Yoonseop. Last modified on 23-Nov-2020. This activity implements the create detail expenses function of the app
 * User story: As an register user, I want to view my expenses activity
 * */
package com.example.Simplitter.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Simplitter.ActivityViewModel;
import com.example.Simplitter.Adapters.ActivityAdapter;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.R;
import com.google.gson.Gson;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //UI control instances
    Button btnCreateExpense;
    ImageView imageViewUser;
    ActivityViewModel activityViewModel;
    //Variables
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //If there is a extra when start this activity, getting the extra value
        Intent intent=getIntent();
        if(intent.hasExtra("userID")){
            userId=intent.getIntExtra("userID",0);
        }

        //Get UI control from layout
        imageViewUser=findViewById(R.id.userIcon);
        btnCreateExpense = findViewById(R.id.button_createExpense);
        btnCreateExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if user has login. If user login in then navigate to Create Expense Activity, otherwise go to NonRegisterCreateActivity
                if(LoginStatus.IsLogin){
                    Intent createExpIntent = new Intent(getApplicationContext(), CreateExpenseActivity.class);
                    createExpIntent.putExtra("userId", userId); //change the userId to database fetched userId
                    startActivity(createExpIntent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), NonRegisterCreateActivity.class);
                    startActivity(intent);
                }
            }
        });

        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if user has login. If user login in then navigate to Update User Activity, otherwise go to login page
                if(LoginStatus.IsLogin){
                    Intent intent=new Intent(getApplicationContext(),UpdateUserActivity.class);
                    intent.putExtra("userID",userId);
                    startActivity(intent);
                }
                else {
                    Intent createExpIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(createExpIntent);
                }
            }
        });

        //Set recycler view with activity adapter
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ActivityAdapter activityAdapter=new ActivityAdapter(this);
        recyclerView.setAdapter(activityAdapter);
        activityViewModel= ViewModelProviders.of(this).get(ActivityViewModel.class);

        //Get expenses activity by user ID
        activityViewModel.getActivityByUserID(userId).observe(this, new Observer<List<ExpensesActivity>>() {
            @Override
            public void onChanged(List<ExpensesActivity> expensesActivity) {
                activityAdapter.setExpensesActivities(expensesActivity);
            }

        });

        //Navigate to expenses detail page
        activityAdapter.setOnItemClickListener(new ActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent =new Intent(getApplicationContext(), ExpensesDetailActivity.class);
                intent.putExtra("ActivityID",activityAdapter.getActivityAct(position).getActivityID());
                intent.putExtra("Activity",new Gson().toJson(activityAdapter.getActivityAct(position)));
                intent.putExtra("Contributors",activityAdapter.getActivityAct(position).getNumberOfContributors());
                startActivity(intent);
            }
        });
        //swipe left or right to delete detail expenses
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                activityViewModel.deleteActivity(activityAdapter.getActivityAct(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Activity deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }


}