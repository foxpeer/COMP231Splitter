/*
 * Author: Dewaker. Last modified: 17,Nov,2020. This activity implement the function that allows user choose different payment
 * option
 * User story: As an register user, I want to view all my payment options to select the on to best fit me
 * */
package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.Simplitter.R;

public class PaymentOptionsActivity extends AppCompatActivity {

    //ImageView instance
    ImageView iv_applepay, iv_mastercard, iv_visa, iv_paypal;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        //Get UI control from layout
        iv_visa = findViewById(R.id.iv_visa);
        iv_paypal = findViewById(R.id.iv_paypal);
        iv_mastercard = findViewById(R.id.iv_mastercard);

        iv_visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri=Uri.parse("https://secure.checkout.visa.com/");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        iv_paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri=Uri.parse("https://www.paypal.com/ca/business/get-paid/accept-online-payments/express-checkout");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        iv_mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri=Uri.parse("https://src.mastercard.com/profile/enroll?cmp=us.en-us.nam.mccom.getStarted&locale=en_US");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
}