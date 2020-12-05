/*
* Author Yoonseop Lee. Last modified on 13-Nov-2020. This activity implements the calculation function in the start of the app
* User story: As an app user, I want to use a bill split calculator at the start of the app
* */
package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.Simplitter.R;

public class CalculateActivity extends AppCompatActivity {
    //onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);


    }
    //Round method. Round the value if the value is infinite decimal
    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }


    // Calculate method. Calculate the split result and display it
    public void CalculateClick(View view) {
        TextView totalAmount = (TextView) findViewById(R.id.textView_totalAmount);
        EditText editTextAmount = (EditText) findViewById(R.id.editText_amount);
        EditText editTextHowMany = (EditText) findViewById(R.id.editText_howMany);
        Double editTextAmountValue = Double.parseDouble(editTextAmount.getText().toString());
        Double editTextHowManyValue =  Double.parseDouble(editTextHowMany.getText().toString());
        Double result = round(editTextAmountValue / editTextHowManyValue ,2);
        totalAmount.setText("$" + result.toString());
    }

    // Navigate to main page
    public void MainClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}