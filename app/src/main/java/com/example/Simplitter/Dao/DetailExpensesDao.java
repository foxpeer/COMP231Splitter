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
import com.example.Simplitter.Model.DetailExpenses;
import java.util.List;

@Dao
public interface DetailExpensesDao {
    //Insert detail expenses
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExpenses(DetailExpenses... detailExpenses);
    //Get all detail expenses by activity ID
    @Query("SELECT * FROM tb_detailExpenses WHERE activityID=:activityID")
    LiveData<List<DetailExpenses>> getAllExpensesByActivityID(int activityID);
    //Get all detail expenses by expenses ID
    @Query("SELECT * FROM tb_detailExpenses WHERE detailExpenses_id=:expenseID")
    DetailExpenses getAllExpensesByExpenseID(int expenseID);
    //Update detail expenses
    @Update
    void Update(DetailExpenses... detailExpenses);
    //Delete detail expenses
    @Delete
    void Delete(DetailExpenses... detailExpenses);
}
