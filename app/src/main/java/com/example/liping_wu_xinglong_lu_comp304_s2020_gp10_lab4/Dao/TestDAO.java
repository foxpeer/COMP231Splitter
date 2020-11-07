package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;

import java.util.List;

@Dao
public interface TestDAO {
    //Insert a list of Test, replacing stored tests
    //using the same name
    //Insert one new test
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTest(Test... test);

    @Delete
    void delete(Test... test);

    @Query("DELETE FROM tb_test WHERE test_id=:testId")
    void deleteTest(int testId);

    @Query("SELECT * FROM tb_test WHERE patientId=:patientid ORDER BY test_id")
    LiveData<List<Test>> displayAllTests(int patientid);
}
