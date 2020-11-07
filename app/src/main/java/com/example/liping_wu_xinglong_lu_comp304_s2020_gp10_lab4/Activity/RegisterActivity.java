package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Nurse;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.NurseViewModel;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

public class RegisterActivity extends AppCompatActivity {

    EditText nurseID, firstName, lastName, department,password;
    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);
        nurseID=(EditText)findViewById(R.id.editText_nurseId);
        firstName=(EditText)findViewById(R.id.editText_firstName);
        lastName=(EditText)findViewById(R.id.editText_lastName);
        department=(EditText)findViewById(R.id.editText_department);
        password=(EditText)findViewById(R.id.editText_password);
    }

    public void RegisterClick(View view) {
        int id=Integer.parseInt(nurseID.getText().toString());
        String firstname=firstName.getText().toString();
        String lastname=lastName.getText().toString();
        String dep=department.getText().toString();
        String passw=password.getText().toString();

        Nurse nurse=new Nurse(id,firstname,lastname,dep,passw);

        nurseViewModel.insert(nurse);
        Toast.makeText(getApplicationContext(),"Thanks for your registration, you are good to login now", Toast.LENGTH_SHORT).show();

        //after register, go to login page directly
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void LoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You already have an account, go to Login directly", Toast.LENGTH_SHORT).show();
    }
}