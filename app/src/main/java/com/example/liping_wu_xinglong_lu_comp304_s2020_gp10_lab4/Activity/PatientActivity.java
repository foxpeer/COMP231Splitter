package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.PatientViewModel;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

//allow the nurse to enter and view patient information
public class PatientActivity extends AppCompatActivity {
    public static final String EXTRA_PATIENTID =
            "com.comp304.Lab04.views.EXTRA_PATIENTID";
    public static final String EXTRA_FIRSTNAME =
            "com.comp304.Lab04.views.EXTRA_FIRSTNAME";
    public static final String EXTRA_LASTNAME =
            "com.comp304.Lab04.views.EXTRA_LASTNAME";
    public static final String EXTRA_DEPARTMENT =
            "com.comp304.Lab04.views.EXTRA_DEPARTMENT";
    public static final String EXTRA_ROOM =
            "com.comp304.Lab04.views.EXTRA_ROOM";
    public static final String EXTRA_NURSEID =
            "com.comp304.Lab04.views.EXTRA_NURSEID";

    private PatientViewModel patientViewModel;

    private TextView tvPatientId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvDepartment;
    private TextView tvRoom;
    private TextView tvNurseId;


    int patientId;
    String firstName;
    String lastName;
    String department;
    String room;
    int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        tvPatientId = findViewById(R.id.tvPatientId);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvRoom = findViewById(R.id.tvRoom);
        tvNurseId = findViewById(R.id.tvNurseId);

        Intent intent = getIntent();
        nurseId = intent.getIntExtra("nurseId", 0);
        tvNurseId.setText(String.valueOf(nurseId));
        tvPatientId.setText("0");

        //initialize patientViewModel
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Patient");
    }
    //method validate input
    private boolean validate(){
         firstName = tvFirstName.getText().toString();
         lastName = tvLastName.getText().toString();
         department = tvDepartment.getText().toString();
         room = tvRoom.getText().toString();

        if (firstName.trim().isEmpty()
                || lastName.trim().isEmpty()
                || department.trim().isEmpty()
                || room.trim().isEmpty()
                ||tvNurseId.getText().toString().trim().isEmpty()
                ||tvPatientId.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
            return false; //? Stay in the activity
        } else {
            return true;
        }

    }

    //method savePatient()
    private void savePatient() {
       if(!validate()) {
           Toast.makeText(this, "Please fill all fields of the form", Toast.LENGTH_SHORT).show();
       } else {
           Log.d("PatientActivity:", "tvPatientId: "+tvPatientId.getText().toString());
           patientId = Integer.parseInt(tvPatientId.getText().toString());
           Log.d("PatientActivity:", "patientId: "+ patientId);
           firstName = tvFirstName.getText().toString();
           lastName = tvLastName.getText().toString();
           department = tvDepartment.getText().toString();
           room = tvRoom.getText().toString();
           nurseId = Integer.parseInt(tvNurseId.getText().toString());
           patientViewModel.insert(new Patient(0, firstName, lastName, department, nurseId, room));
           Toast.makeText(this, "Patient saved.", Toast.LENGTH_SHORT).show();

           Log.d("PatientActivity:", "saved patientId: "+ patientId);
           finish();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_patient_menu, menu);
        return true;
    }

    //menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_patient:
                savePatient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //button add new patient event handler
    public void SubmitNewPatient(View view) {
        savePatient();

    }

    public void CancelAddPatient(View view) {
        finish();
    }

}
