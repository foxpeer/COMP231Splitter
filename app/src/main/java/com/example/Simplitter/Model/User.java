package com.example.Simplitter.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import  androidx.room.PrimaryKey;

@Entity(tableName="tb_user")
public
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int userID;

    @ColumnInfo(name = "email")
    private  String email;

    @ColumnInfo(name = "firstname")
    private  String firstname;

    @ColumnInfo(name = "lastname")
    private  String lastname;

    @ColumnInfo(name = "password")
    private String password;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(int userID,String email, String firstname, String lastname, String password) {
        this.userID=userID;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
