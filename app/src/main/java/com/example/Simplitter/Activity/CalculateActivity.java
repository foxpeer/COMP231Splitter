package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.Simplitter.UserViewModel;
import com.example.Simplitter.R;

import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);


    }

    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }


    // Calculate
    public void CalculateClick(View view) {
        TextView totalAmount = (TextView) findViewById(R.id.textView_totalAmount);
        EditText editTextAmount = (EditText) findViewById(R.id.editText_amount);
        EditText editTextHowMany = (EditText) findViewById(R.id.editText_howMany);
        Double editTextAmountValue = Double.parseDouble(editTextAmount.getText().toString());
        Double editTextHowManyValue =  Double.parseDouble(editTextHowMany.getText().toString());
        Double result = round(editTextAmountValue / editTextHowManyValue ,2);
        totalAmount.setText("$" + result.toString());
    }

    // Go to Main
    public void MainClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}