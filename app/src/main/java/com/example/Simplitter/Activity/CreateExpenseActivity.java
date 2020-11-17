package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.Simplitter.R;
import android.os.Bundle;
import android.widget.EditText;

public class CreateExpenseActivity extends AppCompatActivity {

    EditText expenseName, howmanyppl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);

        expenseName = findViewById(R.id.editText_expensename);
        howmanyppl = findViewById(R.id.editText_howMany2);
    }


}