package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao.NurseDao;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Nurse;

import java.util.Optional;

public class NurseRepo {
    private  NurseDao nurseDao;

    NurseRepo(Application app){
        NurseDatabase db=NurseDatabase.getDatabase(app);
        this.nurseDao=db.nurseDao();
    }
    LiveData<Optional<Nurse>>  getNurse(int nurseID){return  nurseDao.getNursebyID(nurseID);}
    public void insert(Nurse... nurses){new InsertNurseAsyncTask(nurseDao).execute(nurses);}

    private static class InsertNurseAsyncTask extends AsyncTask<Nurse,Void,Void>{
        private NurseDao nurseDao;
        private InsertNurseAsyncTask(NurseDao nurseDao){this.nurseDao=nurseDao;}

        @Override
        protected Void doInBackground(Nurse... nurses) {
            nurseDao.insert(nurses);
            return null;
        }
    }

}
