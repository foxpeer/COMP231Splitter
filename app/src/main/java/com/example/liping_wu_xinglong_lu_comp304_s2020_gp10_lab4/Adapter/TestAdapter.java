package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Test;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

import java.util.ArrayList;
import java.util.List;



public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<Test> tests = new ArrayList<>();
    //private OnItemClickListener listener;
    @NonNull
    @Override

    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.test_cardview,parent,false);
        return new TestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test currentTest = tests.get(position);
        holder.tvTestId.setText("Test Id: " + String.valueOf(currentTest.getTestId()));
        holder.tvPatientId.setText("Patient Id: "+String.valueOf(currentTest.getPatientId())); //
        holder.tvNurseId.setText("Nurse Id: " + String.valueOf(currentTest.getNurseId()));
        holder.tvTestName.setText("Test Id: " +currentTest.getTestName());
        holder.tvBPH.setText("BPH(mmHg): " + String.valueOf(currentTest.getBPH()));
        holder.tvBPL.setText("BPL(mmHg): " + String.valueOf(currentTest.getBPL()));
        holder.tvHeight.setText("Height(cm): " + String.valueOf(currentTest.getHeight()));
        holder.tvWeight.setText("Weigth(kg)：" + String.valueOf(currentTest.getWeight()));
        holder.tvTemperature.setText("Temperature(C)： " + String.valueOf(currentTest.getTemperature()));
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    public Test getTestAct(int position) {
        return tests.get(position);
    }


    class TestHolder extends RecyclerView.ViewHolder {
        private TextView tvTestId;
        private TextView tvTestName;
        private TextView tvBPH;
        private TextView tvBPL;
        private TextView tvWeight;
        private TextView tvHeight;
        private TextView tvTemperature;
        private TextView tvNurseId;
        private TextView tvPatientId;

        public TestHolder(@NonNull View itemView) {
            super(itemView);

            this.tvTestId=itemView.findViewById(R.id.tvTestId);
            this.tvPatientId = itemView.findViewById(R.id.tvPatientId);
            this.tvNurseId=itemView.findViewById(R.id.tvNurseId);
            this.tvTestName = itemView.findViewById(R.id.tvTestName);
            this.tvBPH = itemView.findViewById(R.id.tvBPH);
            this.tvBPL = itemView.findViewById(R.id.tvBPL);
            this.tvWeight = itemView.findViewById(R.id.tvWeight);
            this.tvHeight=itemView.findViewById(R.id.tvHeight);
            this.tvTemperature=itemView.findViewById(R.id.tvTemperature);


        }
    }


}
