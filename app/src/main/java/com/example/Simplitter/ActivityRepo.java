package com.example.Simplitter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.Simplitter.Dao.ActivityDao;
import com.example.Simplitter.Model.ExpensesActivity;

import java.util.Optional;

public class ActivityRepo {

    private ActivityDao activityDao;

    ActivityRepo(Application app){
        SplitterDatabase db= SplitterDatabase.getDatabase(app);
        this.activityDao=db.activityDao();
    }

    LiveData<Optional<ExpensesActivity>> getActivityByUserID(int userID){return  activityDao.getAllActivityByUserID(userID);}

    public void insertActivity(ExpensesActivity... expensesActivities){new ActivityRepo.InsertActivityAsyncTask(activityDao).execute(expensesActivities);}
    public void updateActivity(ExpensesActivity... expensesActivities){new ActivityRepo.UpdateActivityAsyncTask(activityDao).execute(expensesActivities);}
    public void deleteActivity(ExpensesActivity... expensesActivities){new ActivityRepo.DeleteActivityAsyncTask(activityDao).execute(expensesActivities);}

    private static class InsertActivityAsyncTask extends AsyncTask<ExpensesActivity,Void,Void> {
        private ActivityDao activityDao;
        private InsertActivityAsyncTask(ActivityDao activityDao){this.activityDao=activityDao;}

        @Override
        protected Void doInBackground(ExpensesActivity... expensesActivities) {
            activityDao.insertActivity(expensesActivities);
            return null;
        }
    }

    private static class UpdateActivityAsyncTask extends AsyncTask<ExpensesActivity,Void,Void> {
        private ActivityDao activityDao;
        private UpdateActivityAsyncTask(ActivityDao activityDao){this.activityDao=activityDao;}

        @Override
        protected Void doInBackground(ExpensesActivity... expensesActivities) {
            activityDao.Update(expensesActivities);
            return null;
        }
    }

    private static class DeleteActivityAsyncTask extends AsyncTask<ExpensesActivity,Void,Void> {
        private ActivityDao activityDao;
        private DeleteActivityAsyncTask(ActivityDao activityDao){this.activityDao=activityDao;}

        @Override
        protected Void doInBackground(ExpensesActivity... expensesActivities) {
            activityDao.Delete(expensesActivities);
            return null;
        }
    }
}
