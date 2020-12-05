/*
 * Author: Dewaker. Last modified: 17,Nov,2020. This activity implement the function that allows user choose different payment
 * option
 * User story: As an register user, I want to view all my payment options to select the on to best fit me
 * */
package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.Simplitter.R;

public class PaymentOptionsActivity extends AppCompatActivity {

    //ImageView instance
    ImageView iv_applepay, iv_mastercard, iv_visa, iv_paypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        //Get UI control from layout
        iv_applepay = findViewById(R.id.iv_applepay);
        iv_visa = findViewById(R.id.iv_visa);
        iv_paypal = findViewById(R.id.iv_paypal);
        iv_mastercard = findViewById(R.id.iv_mastercard);

        //Set onclick listener for each of image. Make a toast for now, will change in Iteration 2
        iv_applepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"ApplePay selected",Toast.LENGTH_SHORT).show();
            }
        });
        iv_visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Visa selected",Toast.LENGTH_SHORT).show();
            }
        });
        iv_paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"PayPal selected",Toast.LENGTH_SHORT).show();
            }
        });
        iv_mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Mastercard selected",Toast.LENGTH_SHORT).show();
            }
        });
    }
}