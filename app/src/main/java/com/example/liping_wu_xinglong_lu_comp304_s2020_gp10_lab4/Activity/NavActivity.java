package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

public class NavActivity extends AppCompatActivity {
    TextView welcomeMsg;
    String firstName;
    String lastName;
    int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        welcomeMsg = findViewById(R.id.tvWelcomeMsg);
        Intent intent = getIntent();
        nurseId = intent.getIntExtra("nurseId", 0);
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        welcomeMsg.setText("Welcome, Nurse: " + firstName + " "+lastName);



    }
    //to add a new Patient
    public void AddPatientClick(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        intent.putExtra("nurseId", nurseId);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You are going to add a new patient", Toast.LENGTH_SHORT).show();
    }

    //to display all the patients
    public void DisplayPatientClick(View view) {
        Intent intent = new Intent(this, PatientListActivity.class);
        intent.putExtra("nurseId", nurseId);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You are going to View All Patients", Toast.LENGTH_SHORT).show();
    }
    //to add a new Test
    public void AddNewTestClick(View view) {
        Intent intent = new Intent(this, AddTestActivity.class);
        intent.putExtra("nurseId", nurseId);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You are going to add a new test", Toast.LENGTH_SHORT).show();
    }



}
