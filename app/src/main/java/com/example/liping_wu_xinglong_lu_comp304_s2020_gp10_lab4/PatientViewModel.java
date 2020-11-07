package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity.PatientUpdateActivity;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;

    //constructor
    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
    }

    public void insert(Patient patient){
        patientRepository.insert(patient);
    }

    public void update(Patient patient){
        patientRepository.update(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }
    public void deletePatientById(int id){
        patientRepository.deletePatientById(id);
    }
    public void deleteAllPatients(){
        patientRepository.deleteAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients(){
       // addPatientSeedData();
        return allPatients;
    }
    public void loadPatient(int patientId, PatientUpdateActivity updateActivity) {
        patientRepository.loadPatient(patientId, updateActivity);
    }
    private void addPatientSeedData(){
        //add some seed data to database
        insert(new Patient(1, "Lily", " Lee", "OB", 9001, "A123" ));
        insert(new Patient(2, "Cathy", " Care", "KIDS", 9002, "E123" ));
        insert(new Patient(3, "John", " Johns", "EMT", 9003, "F123" ));

    }
}
