/*
* Author: Xinglong Lu. Last modified: 25, Nov,2020. This class is for creating an adapter for expenses activity.
* */
package com.example.Simplitter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.R;
import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ExpensesActivityHolder> {

    //Instance
    private List<ExpensesActivity> expensesActivities = new ArrayList<>();
    private OnItemClickListener listener;
    private LayoutInflater mInflater;
    private Context mContext;
    //private int[] expensesActivitiesID;

    public ActivityAdapter(Context context){
        this.mContext=context;
        mInflater=LayoutInflater.from(context);
    }

    //onCreateViewHolder
    @NonNull
    @Override
    public ExpensesActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_cardview, parent, false);
        //Create new expensesActivityHolder instance
        ExpensesActivityHolder expensesActivityHolder=new ExpensesActivityHolder(itemView);
        return expensesActivityHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesActivityHolder holder, int position) {
        ExpensesActivity currentExpensesActivity = expensesActivities.get(position);
        //Set attribute value for holder
        holder.activityName.setText(String.valueOf(currentExpensesActivity.getActivityName()));
        holder.contributors.setText("Contributors: "+currentExpensesActivity.getNumberOfContributors());
        holder.totalAmount.setText("Total Amount: "+currentExpensesActivity.getTotalAmount());
        holder.needToPay.setText("Need to pay: " + currentExpensesActivity.getResult());

        if(listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    //Get item count
    @Override
    public int getItemCount() {
        return expensesActivities.size();
    }

    public void setExpensesActivities(List<ExpensesActivity> expensesActivities) {
        this.expensesActivities = expensesActivities;
        notifyDataSetChanged();
    }
    //Item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //Get activity from position of adapter
    public ExpensesActivity getActivityAct(int position) {
        return expensesActivities.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View v,int position);
    }

    //Expenses activity holder class
    class ExpensesActivityHolder extends RecyclerView.ViewHolder {
        //Variables
        private TextView  activityName;
        private TextView contributors;
        private TextView totalAmount;
        private TextView needToPay;

        public ExpensesActivityHolder(@NonNull View itemView) {
            super(itemView);
            //Get UI control from card view
            activityName = itemView.findViewById(R.id.cardViewActivityName);
            contributors = itemView.findViewById(R.id.cardViewContributors);
            totalAmount = itemView.findViewById(R.id.cardViewTotalAmount);
            needToPay = itemView.findViewById(R.id.cardViewNeedToPay);

        }
    }




}