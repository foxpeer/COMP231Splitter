package com.example.Simplitter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.Simplitter.Dao.DetailExpensesDao;
import com.example.Simplitter.Model.DetailExpenses;

import java.util.Optional;

public class DetailExpensesRepo {
    private DetailExpensesDao detailExpensesDao;

    DetailExpensesRepo(Application app){
        SplitterDatabase db= SplitterDatabase.getDatabase(app);
        this.detailExpensesDao=db.detailExpensesDao();
    }

    LiveData<Optional<DetailExpenses>> getAllExpensesByActivityID(int activityID){return  detailExpensesDao.getAllExpensesByActivityID(activityID);}

    public void insertExpenses(DetailExpenses... detailExpenses){new DetailExpensesRepo.InsertExpensesAsyncTask(detailExpensesDao).execute(detailExpenses);}
    public void updateExpenses(DetailExpenses... detailExpenses){new DetailExpensesRepo.UpdateExpensesAsyncTask(detailExpensesDao).execute(detailExpenses);}
    public void deleteExpenses(DetailExpenses... detailExpenses){new DetailExpensesRepo.DeleteExpensesAsyncTask(detailExpensesDao).execute(detailExpenses);}

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
            detailExpensesDao.insertExpenses(detailExpenses);
            return null;
        }
    }
}



