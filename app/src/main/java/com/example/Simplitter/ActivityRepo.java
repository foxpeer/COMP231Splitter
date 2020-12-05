/*
* Author: Xinglong Lu. Last modified: 17, Nov, 2020
* */
package com.example.Simplitter;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.Simplitter.Dao.ActivityDao;
import com.example.Simplitter.Model.ExpensesActivity;
import java.util.List;


public class ActivityRepo {

    //Activity Dao instance
    private ActivityDao activityDao;

    //Constructor
    ActivityRepo(Application app){
        SplitterDatabase db= SplitterDatabase.getDatabase(app);
        this.activityDao=db.activityDao();
    }

    //Get expenses activity by user ID
    LiveData<List<ExpensesActivity>> getActivityByUserID(int userID){return  activityDao.getAllActivityByUserID(userID);}
    //Get expenses activity by activity ID
    public LiveData<List<ExpensesActivity>> getActivityByActivityID(int activityID){return activityDao.getActivityByActivityID(activityID); }

    //CRUD methods
    public void insertActivity(ExpensesActivity... expensesActivities){new ActivityRepo.InsertActivityAsyncTask(activityDao).execute(expensesActivities);}
    public void updateActivity(ExpensesActivity... expensesActivities){new ActivityRepo.UpdateActivityAsyncTask(activityDao).execute(expensesActivities);}
    public void deleteActivity(ExpensesActivity... expensesActivities){new ActivityRepo.DeleteActivityAsyncTask(activityDao).execute(expensesActivities);}

    //CRUD async task classes
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
