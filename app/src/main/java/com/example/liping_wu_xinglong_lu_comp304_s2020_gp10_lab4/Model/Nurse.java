package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import  androidx.room.PrimaryKey;
@Entity(tableName="tb_nurse")
public class Nurse {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nurse_id")
    private int nurseID;

    @ColumnInfo(name = "firstname")
    private  String firstname;

    @ColumnInfo(name = "lastname")
    private  String lastname;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name = "password")
    private String password;

    public void setNurseID(int id){this.nurseID=id;}
    public int getNurseID(){return this.nurseID;}

    public void setFirstname(String newFirstname){this.firstname=newFirstname;}
    public String getFirstname(){return  this.firstname;}

    public void  setLastname(String newLastname){this.lastname=newLastname;}
    public String getLastname(){return  this.lastname;}

    public void  setDepartment(String newDepartment){this.department=newDepartment;}
    public String getDepartment(){return  this.department;}

    public void setPassword(String newPassword){this.password=newPassword;}
    public String getPassword(){return this.password;}

    public  Nurse(int id, String firstname, String lastname, String department, String password){
        this.nurseID=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.department=department;
        this.password=password;
    }
    public Nurse(){

    }
    //nurse toString
    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId=" + nurseID +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", department='" + department + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
