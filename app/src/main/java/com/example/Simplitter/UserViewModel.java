package com.example.Simplitter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Simplitter.Activity.UpdateDetailExpensesActivity;
import com.example.Simplitter.Activity.UpdateUserActivity;
import com.example.Simplitter.Model.User;

import java.util.Optional;

public class UserViewModel extends AndroidViewModel {

    private UserRepo userRepository;

    public UserViewModel(Application app) {
        super(app);
        this.userRepository = new UserRepo(app);

    }

   // public LiveData<Optional<User>> getUser(int id) { return userRepository.getUser(id); }
    public LiveData<Optional<User>> getUserByEmail(String email) { return userRepository.getUserByEmail(email); }
    public void loadUser(int userID, UpdateUserActivity updateUserActivity){
        userRepository.loadUserDetail(userID,updateUserActivity);
    }


    public void insert(User newUser) { userRepository.insertUser(newUser); }
    public void update(User newUser) { userRepository.updateUser(newUser); }
    public void delete(User newUser) { userRepository.deleteUser(newUser); }

}
