package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao.TestDAO;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;

import java.util.List;

public class TestRepo {
    //variables
    private final TestDAO testDAO;
    //constructor
    public TestRepo(Application application) {
        NurseDatabase db = NurseDatabase.getDatabase(application);
        testDAO = db.testDAO();

    }

    LiveData<List<Test>> getTest(int patientID){return testDAO.displayAllTests(patientID);}

    public void insert(Test test){
        new InsertTestAsyncTask(testDAO).execute(test);
    }

    public void delete(Test test){
        new DeleteTestAsyncTask(testDAO).execute(test);
    }

    private static class InsertTestAsyncTask
            extends AsyncTask<Test, Void, Void> {
        private TestDAO testDAO;

        //constructor of InsertTestAsyncTask class
        private InsertTestAsyncTask(TestDAO testDAO){
            this.testDAO = testDAO;
        }
        @Override
        protected Void doInBackground(Test... tests){
            testDAO.insertTest(tests);
            return null;
        }
    } //end of InsertTestAsyncTask

    private static class DeleteTestAsyncTask
            extends AsyncTask<Test, Void, Void>{
        private TestDAO testDAO;

        //constructor of DeleteTestAsyncTask class
        private DeleteTestAsyncTask(TestDAO testDAO){
            this.testDAO = testDAO;
        }
        @Override
        protected Void doInBackground(Test... tests){
            testDAO.delete(tests);
            return null;
        }
    } //end of DeleteTestAsyncTask

}
