/*
 * Author: Xinglong Lu. Last modified: 17, Nov, 2020
 * */
package com.example.Simplitter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Simplitter.Activity.UpdateDetailExpensesActivity;
import com.example.Simplitter.Model.DetailExpenses;

import java.util.List;
import java.util.Optional;

public class DetailExpensesViewModel extends AndroidViewModel {
    //Detail expenses repo instance
    private DetailExpensesRepo detailExpensesRepo;
    //Constructor
    public DetailExpensesViewModel(Application app) {
        super(app);
        this.detailExpensesRepo = new DetailExpensesRepo(app);
    }
    //Get detail expenses activity by activity ID
    public LiveData<List<DetailExpenses>> getExpensesByActivityID(int activityID) { return detailExpensesRepo.getAllExpensesByActivityID(activityID); }
    //Load detail expenses
    public void loadDetailExpenses(int detailExpensesID, UpdateDetailExpensesActivity updateDetailExpensesActivity){
        detailExpensesRepo.loadExpensesDetail(detailExpensesID,updateDetailExpensesActivity);
    }
    //CRUD methods
    public void insertExpenses(DetailExpenses detailExpenses) {detailExpensesRepo.insertExpenses(detailExpenses); }

    public void updateExpenses(DetailExpenses detailExpenses){detailExpensesRepo.updateExpenses(detailExpenses);}

    public void deleteExpenses(DetailExpenses detailExpenses){detailExpensesRepo.deleteExpenses(detailExpenses);}
}
