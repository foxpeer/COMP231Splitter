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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... nurses);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM tb_user WHERE email=:email")
    LiveData<Optional<User>> getUserbyEmail(String email);

    @Query("SELECT * FROM tb_user WHERE user_id=:userID")
    User getUserbyUserID(int userID);


}
