package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.NurseViewModel;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

public class MainActivity extends AppCompatActivity {

    private NurseViewModel nurseViewModel;
    private SharedPreferences nursePref;
    public SharedPreferences.Editor editor;
    EditText etNurseId;
    EditText etPassword;
    TextView validation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);

        etNurseId =(EditText)findViewById(R.id.editText_nurseId);
        etPassword =(EditText) findViewById(R.id.editText_password);
        validation=(TextView)findViewById(R.id.textViewValidation);
    }

    public void LoginClick(View view) {
        validation.setText("");
        try {
            int nurseId = Integer.parseInt(etNurseId.getText().toString());
            String password = etPassword.getText().toString();
            nurseViewModel.getNurse(nurseId).observe(MainActivity.this,optionalN->{
                optionalN.ifPresent(n->{
                    if(n.getPassword().equals(password)){
                        //validation.setText("Welcome "+n.getFirstname()+" "+n.getLastname());
                        nursePref = getSharedPreferences("nurse",MODE_PRIVATE);
                        editor=nursePref.edit();
                        editor.putInt("nurseid", nurseId);
                        editor.putString("firstName",n.getFirstname());
                        editor.putString("lastName",n.getLastname());
                        editor.commit();
                        Intent intent = new Intent(this, NavActivity.class);
                        intent.putExtra("nurseId", nurseId);
                        intent.putExtra("firstName", n.getFirstname());
                        intent.putExtra("lastName", n.getLastname());
                        startActivity(intent);
                    }
                    else {
                        validation.setText("Incorrect username or password, please reenter");
                    }
                });
            });
        }
        catch (Exception ex){
            validation.setText("Please enter correct username or password");
        }

    }
    public void RegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Register as a new nurse", Toast.LENGTH_SHORT).show();

    }

}