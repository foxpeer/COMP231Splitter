/*
 * Author: Xinglong Lu. Last modified: 20, Nov,2020. This class is for creating an adapter for detail expenses activity.
 * */
package com.example.Simplitter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Simplitter.Model.DetailExpenses;
import com.example.Simplitter.R;
import java.util.ArrayList;
import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.DetailExpensesHolder> {
    //Instance
    private OnItemClickListener listener;
    private List<DetailExpenses> detailExpenses = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ExpensesAdapter(Context context){
        this.mContext=context;
        mInflater=LayoutInflater.from(context);
    }

    //onCreateViewHolder
    @NonNull
    @Override
    public DetailExpensesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.detail_cardview, parent, false);
        //Create new expensesActivityHolder instance
        DetailExpensesHolder detailExpensesHolder=new DetailExpensesHolder(itemView);
        return detailExpensesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailExpensesHolder holder, int position) {
        DetailExpenses currentDetailExpenses = detailExpenses.get(position);
        //Set attribute value for holder
        holder.expenseName.setText(String.valueOf(currentDetailExpenses.getDetailExpensesName()));
        holder.expensesAmount.setText("Expenses Amount: "+currentDetailExpenses.getDetailExpensesAmount());

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
        return detailExpenses.size();
    }

    public void setDetailExpensesActivities(List<DetailExpenses> detailExpenses) {
        this.detailExpenses = detailExpenses;
        notifyDataSetChanged();
    }
    //Item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
    //Get detail activity from position of adapter
    public DetailExpenses getDetailExpenses(int position) {
        return detailExpenses.get(position);
    }

    // detail expenses activity holder class
    class DetailExpensesHolder extends RecyclerView.ViewHolder {
        //Variables
        private TextView  expenseName;
        private TextView  expensesAmount;

        public DetailExpensesHolder(@NonNull View itemView) {
            super(itemView);
            //Get UI control from card view
            expenseName = itemView.findViewById(R.id.cardViewExpenseName);
            expensesAmount = itemView.findViewById(R.id.cardViewExpensesAmount);
        }
    }




}