/*
 * Author:Liping Wu, Xinglong Lu. Last modified 17, Nov, 2020
 * */
package com.example.Simplitter;

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

//Introduce classes to database
@Database(entities = {User.class, ExpensesActivity.class, DetailExpenses.class},version=3,exportSchema = false)
public abstract class SplitterDatabase extends RoomDatabase {
    //Dao instances
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
    //Call back method
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
    //Populate Database Async Task
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
