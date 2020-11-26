package com.example.Simplitter.Adapters;

import android.content.Context;
import android.view.ContextMenu;
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
    private List<ExpensesActivity> expensesActivities = new ArrayList<>();
    private OnItemClickListener listener;
    private LayoutInflater mInflater;
    private Context mContext;
    private int[] expensesActivitiesID;

    public ActivityAdapter(Context context){
        this.mContext=context;
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ExpensesActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_cardview, parent, false);
        ExpensesActivityHolder expensesActivityHolder=new ExpensesActivityHolder(itemView);
        return expensesActivityHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesActivityHolder holder, int position) {
        ExpensesActivity currentExpensesActivity = expensesActivities.get(position);

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

    @Override
    public int getItemCount() {
        return expensesActivities.size();
    }

    public void setExpensesActivities(List<ExpensesActivity> expensesActivities) {
        this.expensesActivities = expensesActivities;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ExpensesActivity getActivityAct(int position) {
        return expensesActivities.get(position);
    }

    public interface OnItemClickListener {
        //void onItemClick(ExpensesActivity expensesActivity);
        void onItemClick(View v,int position);
    }

    class ExpensesActivityHolder extends RecyclerView.ViewHolder {
        private TextView  activityName;
        private TextView contributors;
        private TextView totalAmount;
        private TextView needToPay;

        public ExpensesActivityHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.cardViewActivityName);
            contributors = itemView.findViewById(R.id.cardViewContributors);
            totalAmount = itemView.findViewById(R.id.cardViewTotalAmount);
            needToPay = itemView.findViewById(R.id.cardViewNeedToPay);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (listener != null && position != RecyclerView.NO_POSITION)
//                        listener.onItemClick(expensesActivities.get(position));
//                }
//            });
        }
    }




}