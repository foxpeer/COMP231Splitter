package com.example.Simplitter.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Simplitter.Model.ExpensesActivity;

import java.util.List;
import java.util.Optional;

@Dao
public interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertActivity(ExpensesActivity... expensesActivities);

    @Query("SELECT * FROM tb_expenseActivity WHERE userID=:userID")
    LiveData<List<ExpensesActivity>> getAllActivityByUserID(int userID);
    @Query("SELECT * FROM tb_expenseActivity WHERE activity_id=:activityID")
    LiveData<List<ExpensesActivity>> getActivityByActivityID(int activityID);

    @Query("SELECT * FROM tb_expenseActivity")
    LiveData<Optional<ExpensesActivity>> getAllActivity();

    @Update
    void Update(ExpensesActivity... expensesActivities);

    @Delete
    void Delete(ExpensesActivity... expensesActivities);
}
