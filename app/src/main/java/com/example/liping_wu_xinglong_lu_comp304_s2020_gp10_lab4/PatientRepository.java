package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Activity.PatientUpdateActivity;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao.PatientDao;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;

import java.util.List;

public class PatientRepository {
    //variables
    private final PatientDao patientDao;
    private LiveData<List<Patient>> patientsLiveData;

    //constructor
    public PatientRepository(Application application) {
        NurseDatabase db = NurseDatabase.getDatabase(application);
        patientDao = db.patientDao();
        patientsLiveData =  patientDao.selectAll();
    }

    public void insert(Patient patient){
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public void update(Patient patient){
        new UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    public void delete(Patient patient){
        new DeletePatientAsyncTask(patientDao).execute(patient);
    }

    public void deletePatientById(int id){
       new DeletePatientbyIDAsyncTask(patientDao).execute(id);
    }


    public void deleteAllPatients(){
        new DeleteAllPatientAsyncTask(patientDao).execute();
    }

    public LiveData<List<Patient>> getAllPatients(){
        return patientsLiveData;
    }

    public void loadPatient(int patientId, PatientUpdateActivity updateActivity) {
        new GetPatientAsyncTask(patientDao, updateActivity).execute(patientId);
    }

    private static class InsertPatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDao patientDAO;

        //constructor of InsertPatientAsyncTask class
        private InsertPatientAsyncTask(PatientDao patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Patient... patients){
            patientDAO.insert(patients);
            return null;
        }
    } //end of InsertPatientAsyncTask

    private static class UpdatePatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDao patientDAO;

        //constructor of UpdatePatientAsyncTask class
        private UpdatePatientAsyncTask(PatientDao patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Patient... patients){
            patientDAO.update(patients);
            return null;
        }
    } //end of UpdatePatientAsyncTask

    private static class DeletePatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDao patientDAO;

        //constructor of DeletePatientAsyncTask class
        private DeletePatientAsyncTask(PatientDao patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Patient... patients){
            patientDAO.delete(patients);
            return null;
        }
    } //end of DeletePatientAsyncTask

    private static class DeletePatientbyIDAsyncTask
            extends AsyncTask<Integer,Void,Void>{
        private  PatientDao patientDao;

        private DeletePatientbyIDAsyncTask(PatientDao patientDao){
            this.patientDao=patientDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            patientDao.deletePatient(integers[0]);
            return null;
        }
    }

    private static class DeleteAllPatientAsyncTask
            extends AsyncTask<Void, Void, Void>{
        private PatientDao patientDAO;

        //constructor of DeleteAllPatientAsyncTask class
        private DeleteAllPatientAsyncTask(PatientDao patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Void... voids){
            patientDAO.deleteAll();
            return null;
        }
    } //end of DeleteAllPatientAsyncTask


    private static class GetPatientAsyncTask
            extends AsyncTask<Integer, Void, Patient>{
        private PatientDao patientDAO;
        private PatientUpdateActivity updateActivity;

        //constructor of GetPatientAsyncTask class
        private GetPatientAsyncTask(PatientDao patientDAO, PatientUpdateActivity updateActivity){
            this.patientDAO = patientDAO;
            this.updateActivity = updateActivity;
        }

        @Override
        protected Patient doInBackground(Integer... patientId){
            return patientDAO.select(patientId[0]);
        }
        @Override
        protected void onPostExecute(Patient patient) {
        // call the callback function here
            this.updateActivity.loadInputText(patient);
        }
    } //end of GetPatientAsyncTask class

}
