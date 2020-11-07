package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Nurse;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;

import java.util.Optional;

public class NurseViewModel extends AndroidViewModel {

    private NurseRepo nurseRepository;

    public NurseViewModel (Application app) {
        super(app);
        this.nurseRepository = new NurseRepo(app);
        //addNurseSeedData();
    }

    public LiveData<Optional<Nurse>> getNurse(int id) { return nurseRepository.getNurse(id); }

    public void insert(Nurse newNurse) { nurseRepository.insert(newNurse); }

    private void addNurseSeedData(){
        nurseRepository.insert(new Nurse(9001,"Abby","Taylor","Emergency","123456"));
        nurseRepository.insert(new Nurse(9003,"Belle","Harris","Pediatrics","22222"));
        nurseRepository.insert(new Nurse(9007,"Coral","Young","Laboratory","33333"));

    }
}
