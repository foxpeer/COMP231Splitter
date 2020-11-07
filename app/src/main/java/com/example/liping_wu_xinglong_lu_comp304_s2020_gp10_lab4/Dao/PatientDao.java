
package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;

import java.util.List;


@Dao
public interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(Patient... patient);

    @Update
     void update(Patient... patient);

    @Delete
     void delete(Patient... patient);

    @Query("SELECT * FROM tb_patient ORDER BY patient_id")
    LiveData<List<Patient>> selectAll();

    @Query("DELETE FROM tb_patient WHERE patient_id=:patientId")
    void deletePatient(int patientId);

    @Query("DELETE FROM tb_patient")
    void deleteAll();

    @Query("SELECT * FROM tb_patient WHERE patient_id = :patientId")
    Patient select(int patientId);

}
