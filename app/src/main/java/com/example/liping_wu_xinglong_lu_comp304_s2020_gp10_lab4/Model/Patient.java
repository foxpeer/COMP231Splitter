package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_patient")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "patient_id")
    private int patientId;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name="nurseId")
    private int nurseId;

    @ColumnInfo(name="room")
    private String room;

    //constructor

    //constructor with PatientId
    public Patient(int patientId, String firstName, String lastName, String department, int nurseId, String room) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.nurseId = nurseId;
        this.room = room;
    }


    //patient toString()
    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", nurseId=" + nurseId +
                ", room='" + room + '\'' +
                '}';
    }

    //patient getters and setters


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
