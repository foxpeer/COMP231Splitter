package com.example.Simplitter;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.Simplitter.Dao.UserDao;
import com.example.Simplitter.Model.User;

@Database(entities = {User.class},version=3,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static volatile UserDatabase instance;

    static UserDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (UserDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "UserDatabase")
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

        private PopulateDbAsyncTask(UserDatabase db){
            userDAO = db.userDao();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.insert(new User( "test1@test.com","Abby","Taylor","123456"));

          return null;
        }
    }

}
