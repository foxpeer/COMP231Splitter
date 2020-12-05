/*
 * Author:Xinglong Lu. Last modified 17, Nov, 2020
 * */
package com.example.Simplitter.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="tb_expenseActivity")
public class ExpensesActivity {

    //Columns
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "activity_id")
    private int activityID;

    @ColumnInfo(name = "userID")
    private  int userID;

    @ColumnInfo(name = "totalAmount")
    private  double totalAmount;

    @ColumnInfo(name = "activityName")
    private  String activityName;

    @ColumnInfo(name = "contributorNumber")
    private  int numberOfContributors;

    @ColumnInfo(name = "splitResult")
    private double result;

    //Getters and setters
    public int getActivityID() {
        return activityID;
    }
    public void setActivityID(int activityID) {
        this.activityID=activityID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public double getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount=totalAmount;
    }
    public String getActivityName(){
        return activityName;
    }
    public void setActivityName(String activityName){
        this.activityName=activityName;
    }
    public int getNumberOfContributors(){
        return  numberOfContributors;
    }
    public void setNumberOfContributors(int numberOfContributors){ this.numberOfContributors=numberOfContributors; }
    public double getResult(){
        return result;
    }
    public void setResult(double result){
        this.result=result;
    }

    //Constructor
    public ExpensesActivity(int userID, int activityID,String activityName, int numberOfContributors,
                            double totalAmount, double result) {
        this.userID=userID;
        this.activityID=activityID;
        this.activityName=activityName;
        this.numberOfContributors=numberOfContributors;
        this.totalAmount=totalAmount;
        this.result=result;
    }


}
