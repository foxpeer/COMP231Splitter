package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Adapter.PatientAdapter;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.PatientViewModel;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

import java.util.List;

//allow the nurse to enter and view patient information
public class PatientListActivity extends AppCompatActivity {
    public static final int ADD_PATIENT_REQUEST = 1;
    public static final int UPDATE_PATIENT_REQUEST =2;
    int nurseId;

 private PatientViewModel patientViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientlist);
        setTitle("All Patients");

        Intent intent = getIntent();
        nurseId = intent.getIntExtra("nurseId", 0);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PatientAdapter adapter = new PatientAdapter();
        recyclerView.setAdapter(adapter);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                //update RecycleView
                //Toast.makeText(PatientListActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            adapter.setPatients(patients);
            }
        });

        //swipe left or right to delete patient
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                patientViewModel.delete(adapter.getPatientAct(viewHolder.getAdapterPosition()));
                Toast.makeText(PatientListActivity.this, "Patient deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {
                Intent intent = new Intent(PatientListActivity.this, PatientUpdateActivity.class);
                // send patientId back to PatientUpdateActivity for update
                intent.putExtra(PatientUpdateActivity.EXTRA_PATIENTID, patient.getPatientId());
                intent.putExtra(PatientUpdateActivity.EXTRA_FIRSTNAME, patient.getFirstName());
                intent.putExtra(PatientUpdateActivity.EXTRA_LASTNAME, patient.getLastName());
                intent.putExtra(PatientUpdateActivity.EXTRA_DEPARTMENT, patient.getDepartment());
                intent.putExtra(PatientUpdateActivity.EXTRA_NURSEID, patient.getNurseId());
                intent.putExtra(PatientUpdateActivity.EXTRA_ROOM, patient.getRoom());

                startActivityForResult(intent, UPDATE_PATIENT_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_PATIENT_REQUEST && resultCode ==RESULT_OK){
            int patientId = Integer.parseInt(data.getStringExtra(PatientActivity.EXTRA_PATIENTID));
            String firstName = data.getStringExtra(PatientActivity.EXTRA_FIRSTNAME);
            String lastName = data.getStringExtra(PatientActivity.EXTRA_LASTNAME);
            String department = data.getStringExtra(PatientActivity.EXTRA_DEPARTMENT);
            String room = data.getStringExtra(PatientActivity.EXTRA_ROOM);
            int nurseId = Integer.parseInt(data.getStringExtra(PatientActivity.EXTRA_NURSEID));

            Patient patient = new Patient(patientId, firstName, lastName, department,nurseId, room);
            patientViewModel.insert(patient);

            Toast.makeText(this, "Patient saved)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Patient NOT saved)", Toast.LENGTH_SHORT).show();
        }
    } //end of onActivityResult

    //add delete menu at top of patientlist
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.patientlist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_patients:
                patientViewModel.deleteAllPatients();
                Toast.makeText(this, "All patients deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void FloatBtnAddPatientClick(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        intent.putExtra("nurseId", nurseId);
        startActivityForResult(intent, ADD_PATIENT_REQUEST );
    }

    public void BackToMain(View view){
        finish();
    }

}
