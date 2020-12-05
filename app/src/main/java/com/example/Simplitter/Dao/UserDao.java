/*
* Author: Liping Wu. Last Modified: 11, Nov, 2020
* */
package com.example.Simplitter.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.Simplitter.Model.User;
import java.util.Optional;

@Dao
public interface UserDao {
    //Insert user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    //Update user
    @Update
    void update(User... users);

    //Delete user
    @Delete
    void delete(User... users);

    //Get user by email
    @Query("SELECT * FROM tb_user WHERE email=:email")
    LiveData<Optional<User>> getUserbyEmail(String email);

    //Get user by user ID
    @Query("SELECT * FROM tb_user WHERE user_id=:userID")
    User getUserbyUserID(int userID);


}
