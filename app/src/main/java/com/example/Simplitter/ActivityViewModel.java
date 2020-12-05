/*
 * Author: Xinglong Lu. Last modified: 17, Nov, 2020
 * */
package com.example.Simplitter;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.Simplitter.Model.ExpensesActivity;
import java.util.List;


public class ActivityViewModel  extends AndroidViewModel {

    //Activity repo instance
    private ActivityRepo activityRepo;

    //Constructor
    public ActivityViewModel(Application app) {
        super(app);
        this.activityRepo = new ActivityRepo(app);
    }

    //Get expenses activity by user ID
    public LiveData<List<ExpensesActivity>> getActivityByUserID(int userID) { return activityRepo.getActivityByUserID(userID); }
    //Get expenses activity by activity ID
    public LiveData<List<ExpensesActivity>> getActivity(int activityID){return  activityRepo.getActivityByActivityID(activityID);}
    //CRUD methods
    public void insertActivity(ExpensesActivity newActivity) { activityRepo.insertActivity(newActivity); }

    public void updateActivity(ExpensesActivity activity){activityRepo.updateActivity(activity);}

    public void deleteActivity(ExpensesActivity activity){activityRepo.deleteActivity(activity);}
}
