package com.example.Simplitter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.Simplitter.Dao.ActivityDao;
import com.example.Simplitter.Dao.DetailExpensesDao;
import com.example.Simplitter.Dao.UserDao;
import com.example.Simplitter.Model.DetailExpenses;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.Model.User;

@Database(entities = {User.class, ExpensesActivity.class, DetailExpenses.class},version=3,exportSchema = false)
public abstract class SplitterDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ActivityDao activityDao();
    public abstract DetailExpensesDao detailExpensesDao();
    private static volatile SplitterDatabase instance;

    static SplitterDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (SplitterDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            SplitterDatabase.class, "UserDatabase")
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
        private UserDao userDAO;
        private ActivityDao activityDao;
        private DetailExpensesDao detailExpensesDao;

        private PopulateDbAsyncTask(SplitterDatabase db){
            userDAO = db.userDao();
            userDAO = db.userDao();
            activityDao=db.activityDao();

        }
        @Override
        protected Void doInBackground(Void... voids) {


          return null;
        }
    }

}
