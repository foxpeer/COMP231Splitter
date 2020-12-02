package com.example.Simplitter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Simplitter.Model.ExpensesActivity;

import java.util.List;
import java.util.Optional;

public class ActivityViewModel  extends AndroidViewModel {

    private ActivityRepo activityRepo;

    public ActivityViewModel(Application app) {
        super(app);
        this.activityRepo = new ActivityRepo(app);
    }

    public LiveData<List<ExpensesActivity>> getActivityByUserID(int userID) { return activityRepo.getActivityByUserID(userID); }

    public LiveData<List<ExpensesActivity>> getActivity(int activityID){return  activityRepo.getActivityByActivityID(activityID);}
    public void insertActivity(ExpensesActivity newActivity) { activityRepo.insertActivity(newActivity); }

    public void updateActivity(ExpensesActivity activity){activityRepo.updateActivity(activity);}

    public void deleteActivity(ExpensesActivity activity){activityRepo.deleteActivity(activity);}
}
