/*
 * Author:Liping Wu. Last modified 11, Nov, 2020
 * */
package com.example.Simplitter;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.Simplitter.Activity.UpdateUserActivity;
import com.example.Simplitter.Dao.UserDao;
import com.example.Simplitter.Model.User;
import java.util.List;
import java.util.Optional;

public class UserRepo {
    //Instance
    private UserDao userDao;
    private LiveData<List<User>>  userLiveData;

    //Constructor
    UserRepo(Application app){
        SplitterDatabase db= SplitterDatabase.getDatabase(app);
        this.userDao=db.userDao();
    }
    //Get user by email
    LiveData<Optional<User>>  getUserByEmail(String email){return  userDao.getUserbyEmail(email);}
    //Load user
    public void loadUserDetail(int userID, UpdateUserActivity updateUserActivity){
       new GetUsersAsyncTask(userDao,updateUserActivity).execute(userID);
    }

    //CRUD methods
    public void insertUser(User... users){new InsertUserAsyncTask(userDao).execute(users);}
    public void updateUser(User... users){new UpdateUserAsyncTask(userDao).execute(users);}
    public void deleteUser(User... users){new DeleteUserAsyncTask(userDao).execute(users);}

    //CRUD async taks class
    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao userDao){this.userDao=userDao;}

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        private UpdateUserAsyncTask(UserDao userDao){this.userDao=userDao;}

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        private DeleteUserAsyncTask(UserDao userDao){this.userDao=userDao;}

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users);
            return null;
        }
    }
    private static class GetUsersAsyncTask extends AsyncTask<Integer,Void, User> {
        private UserDao userDao;
        private UpdateUserActivity updateUserActivity;
        private GetUsersAsyncTask(UserDao userDao, UpdateUserActivity updateUserActivity){
            this.userDao=userDao;
            this.updateUserActivity=updateUserActivity;
        }

        @Override
        protected User doInBackground(Integer... integers) {
           User user= userDao.getUserbyUserID(integers[0]);
            return user;
        }
        @Override
        protected void onPostExecute(User user) {
            this.updateUserActivity.loadUserText(user);
        }
    }

}
