package com.example.Simplitter;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.Simplitter.Dao.UserDao;
import com.example.Simplitter.Model.User;

import java.util.Optional;

public class UserRepo {
    private UserDao userDao;

    //constructor1;
    UserRepo(Application app){
        UserDatabase db=UserDatabase.getDatabase(app);
        this.userDao=db.userDao();
    }
  //  in case search by userId
  //  LiveData<Optional<User>>  getUser(int userID){return  userDao.getUserbyID(userID);}
  LiveData<Optional<User>>  getUserByEmail(String email){return  userDao.getUserbyEmail(email);}
    public void insert(User... users){new InsertUserAsyncTask(userDao).execute(users);}

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao nurseDao){this.userDao=userDao;}

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users);
            return null;
        }
    }

}
