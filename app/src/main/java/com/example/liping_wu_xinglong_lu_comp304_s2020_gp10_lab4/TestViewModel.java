package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepo testRepo;
    //constructor
    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepo = new TestRepo(application);
    }
    public void insert(Test test){
        testRepo.insert(test);
    }

    public LiveData<List<Test>> getAllTest(int patientID){return testRepo.getTest(patientID);}
    public void delete(Test test){
        testRepo.delete(test);
    }
}
