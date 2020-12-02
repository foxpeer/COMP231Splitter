package com.example.Simplitter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.Simplitter.Activity.UpdateDetailExpensesActivity;
import com.example.Simplitter.Dao.DetailExpensesDao;
import com.example.Simplitter.Model.DetailExpenses;

import java.util.List;
import java.util.Optional;

public class DetailExpensesRepo {
    private DetailExpensesDao detailExpensesDao;

    DetailExpensesRepo(Application app){
        SplitterDatabase db= SplitterDatabase.getDatabase(app);
        this.detailExpensesDao=db.detailExpensesDao();
    }

    LiveData<List<DetailExpenses>> getAllExpensesByActivityID(int activityID){return  detailExpensesDao.getAllExpensesByActivityID(activityID);}
    public void loadExpensesDetail(int detailExpensesID, UpdateDetailExpensesActivity updateDetailExpensesActivity){
        new GetExpensesAsyncTask(detailExpensesDao,updateDetailExpensesActivity).execute(detailExpensesID);
    }

    public void insertExpenses(DetailExpenses... detailExpenses){new DetailExpensesRepo.InsertExpensesAsyncTask(detailExpensesDao).execute(detailExpenses[0]);}
    public void updateExpenses(DetailExpenses... detailExpenses){new DetailExpensesRepo.UpdateExpensesAsyncTask(detailExpensesDao).execute(detailExpenses[0]);}
    public void deleteExpenses(DetailExpenses... detailExpenses){new DetailExpensesRepo.DeleteExpensesAsyncTask(detailExpensesDao).execute(detailExpenses[0]);}

    private static class InsertExpensesAsyncTask extends AsyncTask<DetailExpenses,Void,Void> {
        private DetailExpensesDao detailExpensesDao;
        private InsertExpensesAsyncTask(DetailExpensesDao detailExpensesDao){this.detailExpensesDao=detailExpensesDao;}

        @Override
        protected Void doInBackground(DetailExpenses... detailExpenses) {
            detailExpensesDao.insertExpenses(detailExpenses);
            return null;
        }
    }

    private static class UpdateExpensesAsyncTask extends AsyncTask<DetailExpenses,Void,Void> {
        private DetailExpensesDao detailExpensesDao;
        private UpdateExpensesAsyncTask(DetailExpensesDao detailExpensesDao){this.detailExpensesDao=detailExpensesDao;}

        @Override
        protected Void doInBackground(DetailExpenses... detailExpenses) {
            detailExpensesDao.insertExpenses(detailExpenses);
            return null;
        }
    }

    private static class DeleteExpensesAsyncTask extends AsyncTask<DetailExpenses,Void,Void> {
        private DetailExpensesDao detailExpensesDao;
        private DeleteExpensesAsyncTask(DetailExpensesDao detailExpensesDao){this.detailExpensesDao=detailExpensesDao;}

        @Override
        protected Void doInBackground(DetailExpenses... detailExpenses) {
            detailExpensesDao.Delete(detailExpenses);
            return null;
        }
    }

    private static class GetExpensesAsyncTask extends AsyncTask<Integer,Void,DetailExpenses> {
        private DetailExpensesDao detailExpensesDao;
        private UpdateDetailExpensesActivity updateDetailExpensesActivity;
        private GetExpensesAsyncTask(DetailExpensesDao detailExpensesDao, UpdateDetailExpensesActivity detailExpensesActivity){
            this.detailExpensesDao=detailExpensesDao;
            this.updateDetailExpensesActivity=detailExpensesActivity;
        }

        @Override
        protected DetailExpenses doInBackground(Integer... integers) {
            DetailExpenses detailExpenses= detailExpensesDao.getAllExpensesByExpenseID(integers[0]);
            return detailExpenses;
        }

        @Override
        protected void onPostExecute(DetailExpenses detailExpenses) {
            this.updateDetailExpensesActivity.loadInputText(detailExpenses);
        }
    }
}



