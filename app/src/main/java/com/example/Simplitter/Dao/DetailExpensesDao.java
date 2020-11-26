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
import java.util.Optional;

@Dao
public interface DetailExpensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExpenses(DetailExpenses... detailExpenses);

    @Query("SELECT * FROM tb_detailExpenses WHERE activityID=:activityID")
    LiveData<List<DetailExpenses>> getAllExpensesByActivityID(int activityID);
    @Query("SELECT * FROM tb_detailExpenses WHERE detailExpenses_id=:expenseID")
    DetailExpenses getAllExpensesByExpenseID(int expenseID);

    @Update
    void Update(DetailExpenses... detailExpenses);

    @Delete
    void Delete(DetailExpenses... detailExpenses);
}
