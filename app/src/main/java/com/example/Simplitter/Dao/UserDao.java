package com.example.Simplitter.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.Simplitter.Model.User;

import java.util.Optional;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... nurses);

   /* @Query("SELECT * FROM tb_user WHERE user_id=:userId")
     LiveData<Optional<User>> getUserbyID(int userId);*/

    @Query("SELECT * FROM tb_user WHERE email=:email")
    LiveData<Optional<User>> getUserbyEmail(String email);
}
