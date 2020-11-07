package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao.NurseDao;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao.PatientDao;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao.TestDAO;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Nurse;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;

@Database(entities = {Nurse.class,Patient.class, Test.class},version=3,exportSchema = false)
public abstract class NurseDatabase extends RoomDatabase {
    public abstract NurseDao nurseDao();
    public abstract PatientDao patientDao();
    public abstract TestDAO testDAO();

    private static volatile NurseDatabase instance;

    static NurseDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (NurseDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            NurseDatabase.class, "NursePatientTestDatabase")
                            .fallbackToDestructiveMigration()   //if exist, destroy the old database
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
       /* @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateTestDbAsyncTask(instance).execute();
        }*/
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NurseDao nurseDAO;
        private PatientDao patientDao;
        private TestDAO testDAO;
        private PopulateDbAsyncTask(NurseDatabase db){
            nurseDAO = db.nurseDao();
            patientDao=db.patientDao();
            testDAO=db.testDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            nurseDAO.insert(new Nurse(9001,"Abby","Taylor","Emergency","123456"));
            nurseDAO.insert(new Nurse(9002,"Belle","Harris","Pediatrics","22222"));
            nurseDAO.insert(new Nurse(9003,"Coral","Young","Laboratory","33333"));
            patientDao.insert(new Patient(1001, "Nancy", "White", "WOMEN",
                                            9001, "A-301" ));
            patientDao.insert(new Patient(1002, "Lily", "Lee", "WOMEN",
                                            9002, "B-122" ));
            patientDao.insert(new Patient(1003, "May", "Hart", "CHILDREN",
                                            9003, "E-722" ));
            testDAO.insertTest(new Test(2001,1003,9001,70,120,
                    36.5,60,170,"Blood Routine"));
            testDAO.insertTest(new Test(2002,1001,9003,80,125,
                    37.5,70,180,"CEA"));
            testDAO.insertTest(new Test(2003,1002,9007,75,130,
                    36.8,60,170,"Chest"));
            return null;
        }
    }

}
