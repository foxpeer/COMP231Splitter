package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.TestViewModel;

public class AddTestActivity extends AppCompatActivity{
    public static final String EXTRA_PATIENTID =
            "com.comp304.Lab04.views.EXTRA_PATIENTID";
    public static final String EXTRA_TESTNAME =
            "com.comp304.Lab04.views.EXTRA_TESTNAME";
    public static final String EXTRA_TESTID =
            "com.comp304.Lab04.views.EXTRA_TESTID";
    public static final String EXTRA_BPH =
            "com.comp304.Lab04.views.EXTRA_BPH";
    public static final String EXTRA_BPL=
            "com.comp304.Lab04.views.EXTRA_BPL";
    public static final String EXTRA_WEIGHT=
            "com.comp304.Lab04.views.EXTRA_WEIGHT";
    public static final String EXTRA_HEIGHT=
            "com.comp304.Lab04.views.EXTRA_HEIGHT";
    public static final String EXTRA_TEMPERATURE=
            "com.comp304.Lab04.views.EXTRA_TEMPERATURE";
    public static final String EXTRA_NURSEID =
            "com.comp304.Lab04.views.EXTRA_NURSEID";

    private TestViewModel testViewModel;

    private TextView tvTestId;
    private TextView tvTestName;
    private TextView tvPatientId;
    private TextView tvBPH;
    private TextView tvBPL;
    private TextView tvWeight;
    private TextView tvHeight;
    private TextView tvTemperature;
    private TextView tvNurseId;

    int patientId,nurseId,testId;
    double weight, height, temperature;
    int bph,bpl;
    String testName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtest);
        tvPatientId = findViewById(R.id.tvPatientId);
        tvNurseId=findViewById(R.id.tvnurseId);
        tvTestId=findViewById(R.id.tvTestId);
        tvTestName = findViewById(R.id.tvTestName);
        tvBPH = findViewById(R.id.tvBPH);
        tvBPL = findViewById(R.id.tvBPL);
        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
        tvTemperature= findViewById(R.id.tvTemperature);

        Intent intent = getIntent();
        nurseId = intent.getIntExtra("nurseId", 0);
        tvNurseId.setText(String.valueOf(nurseId));
        tvTestId.setText("0");
        tvTestId.setVisibility(View.GONE);

        //initialize patientViewModel
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Test");
    }
    //method validate input
    private boolean validate(){
        testName = tvTestName.getText().toString();

        if (testName.trim().isEmpty()
                || tvTestId.getText().toString().trim().isEmpty()
                ||tvNurseId.getText().toString().trim().isEmpty()
                ||tvPatientId.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
            return false; // Stay in the activity
        } else {
            return true;
        }

    }

    //method saveTest()
    private void saveTest() {
        if(!validate()) {
            Toast.makeText(this, "Please fill all fields of the form", Toast.LENGTH_SHORT).show();
        } else {
            testId=Integer.parseInt(tvTestId.getText().toString());
            patientId = Integer.parseInt(tvPatientId.getText().toString());
            testName = tvTestName.getText().toString();
            weight = Double.parseDouble(tvWeight.getText().toString());
            height = Double.parseDouble(tvHeight.getText().toString());
            nurseId = Integer.parseInt(tvNurseId.getText().toString());
            bph=Integer.parseInt(tvBPH.getText().toString());
            bpl=Integer.parseInt(tvBPL.getText().toString());
            temperature=Double.parseDouble(tvTemperature.getText().toString());
            testViewModel.insert(new Test(testId,patientId,nurseId, bpl, bph, temperature,weight,height,testName));
            Toast.makeText(this, "New test saved.", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    //button add new test event handler
    public void SubmitNewTest(View view) {
        saveTest();
        finish();
    }

    public void CancelAddTest(View view) {
       finish();
    }

}
