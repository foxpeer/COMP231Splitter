package com.example.Simplitter.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="tb_detailExpenses")
public class DetailExpenses {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "detailExpenses_id")
    private int detailExpensesID;

    @ColumnInfo(name = "activityID")
    private  int activityID;

    @ColumnInfo(name = "detailExpensesName")
    private  String detailExpensesName;

    @ColumnInfo(name = "detailExpensesAmount")
    private  double detailExpensesAmount;

    public int getDetailExpensesID() { return detailExpensesID; }
    public void setDetailExpensesID(int detailExpensesID) { this.detailExpensesID = detailExpensesID; }

    public int getActivityID() {
        return activityID;
    }
    public void setActivityID(int activityID) {
        this.activityID=activityID;
    }

    public String getDetailExpensesName() { return detailExpensesName; }
    public void setDetailExpensesName(String detailExpensesName) { this.detailExpensesName = detailExpensesName; }

    public double getDetailExpensesAmount() { return detailExpensesAmount; }
    public void setDetailExpensesAmount(double detailExpensesAmount) { this.detailExpensesAmount = detailExpensesAmount; }



    public DetailExpenses(int activityID, int detailExpensesID,String detailExpensesName, double detailExpensesAmount) {
        this.activityID=activityID;
        this.detailExpensesID=detailExpensesID;
        this.detailExpensesName=detailExpensesName;
        this.detailExpensesAmount=detailExpensesAmount;
    }


}
