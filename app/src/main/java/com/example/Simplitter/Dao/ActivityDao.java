/*
* Author: Xinglong Lu. Last modified: 25, Nov, 2020.
* */
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

@Dao
public interface ActivityDao {
    //Insert expenses activity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertActivity(ExpensesActivity... expensesActivities);
    //Get all expenses activity by user ID
    @Query("SELECT * FROM tb_expenseActivity WHERE userID=:userID")
    LiveData<List<ExpensesActivity>> getAllActivityByUserID(int userID);
    //Get all expenses activity by activity ID
    @Query("SELECT * FROM tb_expenseActivity WHERE activity_id=:activityID")
    LiveData<List<ExpensesActivity>> getActivityByActivityID(int activityID);
    //Get all expenses activity
//    @Query("SELECT * FROM tb_expenseActivity")
//    LiveData<Optional<ExpensesActivity>> getAllActivity();

    //Update expenses activity
    @Update
    void Update(ExpensesActivity... expensesActivities);

    //Delete expenses activity
    @Delete
    void Delete(ExpensesActivity... expensesActivities);
}
