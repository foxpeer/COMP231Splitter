package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Nurse;

import java.util.Optional;

@Dao
public interface NurseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Nurse... nurses);

    @Query("SELECT * FROM tb_nurse WHERE nurse_id=:nurseId")
     LiveData<Optional<Nurse>> getNursebyID(int nurseId);
}
