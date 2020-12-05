/*
 * Author:Liping Wu. Last modified 11, Nov, 2020
 * */
package com.example.Simplitter;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.Simplitter.Activity.UpdateUserActivity;
import com.example.Simplitter.Model.User;
import java.util.Optional;

public class UserViewModel extends AndroidViewModel {
    //Instance
    private UserRepo userRepository;

    //Constructor
    public UserViewModel(Application app) {
        super(app);
        this.userRepository = new UserRepo(app);

    }
    //Get user by email
    public LiveData<Optional<User>> getUserByEmail(String email) { return userRepository.getUserByEmail(email); }
    ////Load user
    public void loadUser(int userID, UpdateUserActivity updateUserActivity){
        userRepository.loadUserDetail(userID,updateUserActivity);
    }

    //CRUD methods
    public void insert(User newUser) { userRepository.insertUser(newUser); }
    public void update(User newUser) { userRepository.updateUser(newUser); }
    public void delete(User newUser) { userRepository.deleteUser(newUser); }

}
