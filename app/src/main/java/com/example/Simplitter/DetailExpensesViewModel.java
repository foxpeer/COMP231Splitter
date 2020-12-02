package com.example.Simplitter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Simplitter.Activity.UpdateDetailExpensesActivity;
import com.example.Simplitter.Model.DetailExpenses;

import java.util.List;
import java.util.Optional;

public class DetailExpensesViewModel extends AndroidViewModel {

    private DetailExpensesRepo detailExpensesRepo;

    public DetailExpensesViewModel(Application app) {
        super(app);
        this.detailExpensesRepo = new DetailExpensesRepo(app);
    }

    public LiveData<List<DetailExpenses>> getExpensesByActivityID(int activityID) { return detailExpensesRepo.getAllExpensesByActivityID(activityID); }
    public void loadDetailExpenses(int detailExpensesID, UpdateDetailExpensesActivity updateDetailExpensesActivity){
        detailExpensesRepo.loadExpensesDetail(detailExpensesID,updateDetailExpensesActivity);
    }

    public void insertExpenses(DetailExpenses detailExpenses) {detailExpensesRepo.insertExpenses(detailExpenses); }

    public void updateExpenses(DetailExpenses detailExpenses){detailExpensesRepo.updateExpenses(detailExpenses);}

    public void deleteExpenses(DetailExpenses detailExpenses){detailExpensesRepo.deleteExpenses(detailExpenses);}
}
