package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity.NavActivity;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity.PatientListActivity;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity.TestListActivity;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.PatientViewModel;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

//allow the nurse to update patient information
public class PatientUpdateActivity extends AppCompatActivity {
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
    private int patientId;

    private TextView tvPatientId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvDepartment;
    private TextView tvRoom;
    private TextView tvNurseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientupdate);
        setTitle("Update Patient");

        tvPatientId = findViewById(R.id.tvPatientId);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvRoom = findViewById(R.id.tvRoom);
        tvNurseId = findViewById(R.id.tvNurseId);


        //initialize patientViewModel
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_PATIENTID)){
            //Log.d("PatientUpdateActivity:", "tvPatientId: "+intent.getIntExtra(EXTRA_PATIENTID,0));
            patientId = intent.getIntExtra(EXTRA_PATIENTID,0);
            patientViewModel.loadPatient(patientId, this);

            // Log.d("PatientUpdateActivity:", "patientId: "+ tvPatientId);

            setTitle("Update Patient");
        } else{
            setTitle("Add Patient");
        }
    }

    public void loadInputText(Patient currentPatient) {
        tvPatientId.setText(String.valueOf(currentPatient.getPatientId()));
        tvFirstName.setText(currentPatient.getFirstName());
        tvLastName.setText(currentPatient.getLastName());
        tvDepartment.setText(currentPatient.getDepartment());
        tvNurseId.setText(String.valueOf(currentPatient.getNurseId()));
        tvRoom.setText(currentPatient.getRoom());
    }

    private void updatePatient() {
        Log.d("PatientActivity:", "tvPatientId: "+tvPatientId.getText().toString());
        int patientId = Integer.parseInt(tvPatientId.getText().toString());
        Log.d("PatientActivity:", "patientId: "+ patientId);
        String firstName = tvFirstName.getText().toString();
        String lastName = tvLastName.getText().toString();
        String department = tvDepartment.getText().toString();
        String room = tvRoom.getText().toString();
        int nurseId = Integer.parseInt(tvNurseId.getText().toString());

        if (firstName.trim().isEmpty() || lastName.trim().isEmpty()
                || department.trim().isEmpty() || room.trim().isEmpty()
                ||tvNurseId.getText().toString().trim().isEmpty()
                ||tvPatientId.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
            return;
        }

        patientViewModel.update(new Patient(patientId, firstName, lastName, department, nurseId, room));

        Toast.makeText(this, "Patient saved)", Toast.LENGTH_SHORT).show();
        finish(); //back to previous activity
    }

    public void CancelUpdatePatient(View view) {
        finish();
    }

    public void UpdatePatientClick(View view){
        updatePatient();
    }

    public void DeletePatient(View view){
        int patientId = Integer.parseInt(tvPatientId.getText().toString());
        patientViewModel.deletePatientById(patientId);
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
    }
    public void CheckTest(View view){
        Intent intent = new Intent(this, TestListActivity.class);
        intent.putExtra("PATIENTID", Integer.parseInt(tvPatientId.getText().toString()));
        startActivity(intent);
        finish();
    }

}
