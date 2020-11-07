package com.example.Simplitter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public void insert(User newUser) { userRepository.insert(newUser); }

    private void addUserSeedData(){
        userRepository.insert(new User("test1@test.com", "Liping","Wu","123456"));
        userRepository.insert(new User("test2@test.com", "Xinglong","Lu","123456"));


    }
}
