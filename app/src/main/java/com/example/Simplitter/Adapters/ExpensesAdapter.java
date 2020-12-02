package com.example.Simplitter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Simplitter.Model.DetailExpenses;
import com.example.Simplitter.Model.ExpensesActivity;
import com.example.Simplitter.R;

import java.util.ArrayList;
import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.DetailExpensesHolder> {
    private OnItemClickListener listener;
    private List<DetailExpenses> detailExpenses = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ExpensesAdapter(Context context){
        this.mContext=context;
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DetailExpensesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.detail_cardview, parent, false);
        DetailExpensesHolder detailExpensesHolder=new DetailExpensesHolder(itemView);
        return detailExpensesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailExpensesHolder holder, int position) {
        DetailExpenses currentDetailExpenses = detailExpenses.get(position);

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

    @Override
    public int getItemCount() {
        return detailExpenses.size();
    }

    public void setDetailExpensesActivities(List<DetailExpenses> detailExpenses) {
        this.detailExpenses = detailExpenses;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        //void onItemClick(DetailExpenses detailExpenses);
        void onItemClick(View v, int position);
    }

    public DetailExpenses getDetailExpenses(int position) {
        return detailExpenses.get(position);
    }


    class DetailExpensesHolder extends RecyclerView.ViewHolder {
        private TextView  expenseName;
        private TextView  expensesAmount;

        public DetailExpensesHolder(@NonNull View itemView) {
            super(itemView);

            expenseName = itemView.findViewById(R.id.cardViewExpenseName);
            expensesAmount = itemView.findViewById(R.id.cardViewExpensesAmount);
        }
    }




}