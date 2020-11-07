
package com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.Model.Patient;
import com.example.liping_wu_xinglong_lu_comp304_s2020_gp10_lab4.R;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<Patient> patients = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_cardview, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        Log.d("PatientList:", "currentPatient.getPatientId(): " + currentPatient.getPatientId());
        Log.d("PatientList:", "tvPatientId: " + String.valueOf(currentPatient.getPatientId()));
        holder.tvPatientId.setText("Patient Id: " + String.valueOf(currentPatient.getPatientId())); //
        holder.tvFirstName.setText(currentPatient.getFirstName());
        holder.tvLastName.setText(currentPatient.getLastName());
        holder.tvDepartment.setText("Dept: " + currentPatient.getDepartment());
        holder.tvRoom.setText("Room: " + currentPatient.getRoom());
        holder.tvNurseId.setText("Nurse Id: " + String.valueOf(currentPatient.getNurseId()));
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    public Patient getPatientAct(int position) {
        return patients.get(position);
    }


    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView tvPatientId;
        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvDepartment;
        private TextView tvRoom;
        private TextView tvNurseId;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);

            tvPatientId = itemView.findViewById(R.id.tvPatientId);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            tvRoom = itemView.findViewById(R.id.tvRoom);
            tvNurseId = itemView.findViewById(R.id.tvNurseId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(patients.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
